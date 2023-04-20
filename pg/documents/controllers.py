import os
import tempfile

from flask import request, jsonify
from datetime import timedelta, datetime

from __init__ import db, minio_client
from .models import Document
from ..employee_documents.models import Employee_document


# TO DO
# Сделать привязку документа сразу к нескольким сотрудникам

def create_document_controller(employee_id):
    """
    Функция загружает файл в minio по id сотрудника, создает новую запись в табоице document и employee_document
    ДЛЯ ОДНОГО СОТРУДНИКА
    """
    request_form = request.form.to_dict()

    file = request.files['file']

    try:
        with tempfile.NamedTemporaryFile(delete=False) as temp_file:
            file.save(temp_file.name)
            with open(temp_file.name, 'rb') as file_data:
                data = file_data
                data_len = len(data.read())
                data.seek(0)
                minio_client.put_object(
                    bucket_name="employees",
                    object_name=f"/{employee_id}/documents/" + file.filename,
                    data=data,
                    length=data_len,
                    content_type=file.content_type
                )
        os.unlink(temp_file.name)
    except Exception as e:
        return f'Error: {str(e)}'

    new_document = Document(
        document_name=request_form['document_name'],
        # document_date=request_form['document_date'],
        document_date=datetime.now(),
        document_url=f"employees/{employee_id}/documents/" + file.filename
    )
    db.session.add(new_document)
    db.session.commit()

    new_employee_document = Employee_document(
        employee_id=int(employee_id),
        document_id=new_document.document_id
    )

    db.session.add(new_employee_document)
    db.session.commit()

    response = Document.query.get(new_document.document_id).toDict()
    return jsonify(response)


def get_document_controller(document_url):
    """
    Функция генерирует и возвращает ссылку на скачивание объекта
    """
    expiry = timedelta(minutes=10)

    parts = document_url.split('/')
    bucket_name = parts[0]
    file_path = '/'+'/'.join(parts[1:])

    return minio_client.presigned_get_object(bucket_name=bucket_name, object_name=file_path, expires=expiry)
