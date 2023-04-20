from flask import request

from app import app
from .controllers import get_employee_document_controller


@app.route("/employee_document/<employee_id>", methods=['GET'])
def list_employee_documents(employee_id):
    if request.method == 'GET': return get_employee_document_controller(employee_id)
    #if request.method == 'POST':
        #return create_employee_controller()
    else:
        return 'Method is Not Allowed'
