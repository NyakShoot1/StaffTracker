from flask import jsonify

from .models import Employee_document
from ..documents.models import Document


def get_employee_document_controller(employee_id):
    """
    Функция возвращает список всех документов сотрудника
    """
    documents = Document.query \
        .join(Employee_document) \
        .filter(Employee_document.employee_id == employee_id) \
        .all()
    response = []
    for document in documents: response.append(document.toDict())
    return jsonify(response)
