import os
import tempfile

from flask import request, jsonify, send_file
from datetime import timedelta, datetime

from __init__ import db, minio_client
from .models import Photo
from ..employees.models import Employee


# TO DO
# Сделать привязку документа сразу к нескольким сотрудникам

def create_photo_controller(employee_id):
    """
    Функция загружает файл в minio по id сотрудника, создает новую запись в табоице photo
    ДЛЯ ОДНОГО СОТРУДНИКА
    """
    request_form = request.form.to_dict()
    print(request_form)

    file = request.files['photo']

    try:
        with tempfile.NamedTemporaryFile(delete=False) as temp_file:
            file.save(temp_file.name)
            with open(temp_file.name, 'rb') as file_data:
                data = file_data
                data_len = len(data.read())
                data.seek(0)
                minio_client.put_object(
                    bucket_name="employees",
                    object_name=f"/{employee_id}/photos/" + file.filename,
                    data=data,
                    length=data_len,
                    content_type=file.content_type
                )
        os.unlink(temp_file.name)
    except Exception as e:
        return f'Error: {str(e)}'

    new_photo = Photo(
        employee_id=int(employee_id),
        photo_name=request_form['photo_name'],
        photo_date=datetime.now().strftime('%a, %d %b %Y %H:%M:%S GMT'),
        photo_url=f"employees/{employee_id}/photos/" + file.filename
    )
    db.session.add(new_photo)
    db.session.commit()

    response = Photo.query.get(new_photo.photo_id).toDict()
    return jsonify(response)


def get_employee_photos_controller(employee_id):
    employee = db.session.query(Employee).get(employee_id)
    response = []
    if employee:
        photos = employee.photo
        for photo in photos:
            response.append(photo.toDict())
    return jsonify(response)


def get_photo_controller(photo_url):
    """
    Функция генерирует и возвращает ссылку на скачивание объекта
    """
    expiry = timedelta(minutes=10)

    parts = photo_url.split('/')
    bucket_name = parts[0]
    file_path = '/' + '/'.join(parts[1:])

    file_data = minio_client.get_object(bucket_name=bucket_name, object_name=file_path)
    file_mimetype = minio_client.stat_object(bucket_name=bucket_name, object_name=file_path)

    mimetype = file_mimetype.metadata.get('content-type', 'application/octet-stream')

    # Возвращаем содержимое файла в ответе
    return send_file(file_data, mimetype=mimetype)
