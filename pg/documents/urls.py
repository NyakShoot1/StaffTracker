from flask import request

from app import app
from .controllers import create_document_controller, get_document_controller


@app.route("/documents/<employee_id>", methods=['POST'])
def create_document(employee_id):
    if request.method == 'POST':
        return create_document_controller(employee_id)
    else:
        return 'Method is Not Allowed'


@app.route("/documents/<path:document_url>", methods=['GET'])
def get_document(document_url):
    if request.method == 'GET':
        return get_document_controller(document_url)
    else:
        return 'Method is Not Allowed'
