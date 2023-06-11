import os
import tempfile

from flask import request, jsonify
from datetime import timedelta, datetime

from __init__ import db, minio_client
from .models import Template_document


def create_template_document_controller():
    """
    Функция загружает шаблон документа в postgres и minio
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
                    bucket_name="template-documents",
                    object_name=f"/documents/" + file.filename,
                    data=data,
                    length=data_len,
                    content_type=file.content_type
                )
        os.unlink(temp_file.name)
    except Exception as e:
        return f'Error: {str(e)}'

    new_document = Template_document(
        document_name=request_form['document_name'],
        document_date=datetime.now(),
        document_url=f"template-documents/documents/" + file.filename
    )
    db.session.add(new_document)
    db.session.commit()

    response = Template_document.query.get(new_document.document_id).toDict()
    return jsonify(response)
