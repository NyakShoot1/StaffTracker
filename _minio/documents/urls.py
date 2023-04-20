import os
import tempfile

from flask import request
from app import app
from __init__ import minio_client


@app.route('/upload', methods=['POST'])
def upload_file():
    if 'file' not in request.files:
        return 'No file part'

    file = request.files['file']
    with tempfile.NamedTemporaryFile(delete=False) as temp_file:
        file.save(temp_file.name)
        with open(temp_file.name, 'rb') as file_data:
            data = file_data
            data_len = len(data.read())
            data.seek(0)
            minio_client.put_object(
                bucket_name=os.getenv("MINIO_BUCKET_NAME"),
                object_name="test/" + file.filename,
                data=data,
                length=data_len,
                content_type=file.content_type
            )
    os.unlink(temp_file.name)
    # Загрузка файла на хранилище Minio

    return 'File uploaded successfully'


a = """ # работает с yaml
    file = request.files['file']
    file_content = file.stream.read().decode('utf-8')
    # Загрузка файла на хранилище Minio
    try:
        minio_client.put_object(
            bucket_name=os.getenv("MINIO_BUCKET_NAME"),
            object_name="test/"+file.filename,
            data=io.BytesIO(file_content.encode('utf-8')),
            length=len(file_content),
            content_type=file.content_type
        )
    except Exception as e:
        return f'Error: {str(e)}'
"""
