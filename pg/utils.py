from datetime import datetime
import tempfile
import os
from xmlrpc.client import ResponseError

from __init__ import db, minio_client
from .employees.models import Employee
from .documents.models import Document
from .employee_documents.models import Employee_document


def save_new_employee_to_pg(employee_info):
    new_employee = Employee(
        full_name=employee_info['full_name'],
        birthday=employee_info['birthday'],
        post=employee_info['post'],
        department=employee_info['department'],
        hire_date=datetime.now(),
        fire_date=None,
        status=True
    )
    db.session.add(new_employee)
    db.session.commit()

    return new_employee


def save_new_document_to_pg(employee_id, filename):
    new_document = Document(
        document_name=filename,
        document_date=datetime.now(),
        document_url=f"employees/{employee_id}/documents/" + filename
    )
    db.session.add(new_document)
    db.session.commit()

    new_employee_document = Employee_document(
        employee_id=int(employee_id),
        document_id=new_document.document_id
    )

    db.session.add(new_employee_document)
    db.session.commit()


def save_files_to_minio(employee_id, file):
    with open(file.name, 'rb') as file_data:
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


def save_photos_to_minio(employee_id, photos, request_form):
    try:
        with tempfile.NamedTemporaryFile(delete=False) as temp_file:
            for photo in photos:
                photo.save(temp_file.name)
                with open(temp_file.name, 'rb') as file_data:
                    data = file_data
                    data_len = len(data.read())
                    data.seek(0)
                    minio_client.put_object(
                        bucket_name="employees",
                        object_name=f"/{employee_id}/photos/" + photo.filename,
                        data=data,
                        length=data_len,
                        content_type=photo.content_type
                    )
            os.unlink(temp_file.name)
    except Exception as e:
        return f'Error: {str(e)}'


def create_init_file_minio(employee_id):
    filename = 'init_file.txt'
    with open(filename, 'w') as f:
        f.write('init_file')

    try:
        minio_client.fput_object(bucket_name="employees",
                                 object_name=f"{employee_id}/documents/init_file.txt", file_path=filename)
        minio_client.fput_object(bucket_name="employees",
                                 object_name=f"{employee_id}/photos/init_file.txt", file_path=filename)
    except ResponseError as err:
        print(err)

    os.remove(filename)
