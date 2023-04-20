from flask import request

from app import app
from .controllers import get_all_employees_controller, create_employee_controller, get_employee_controller, \
    update_employee_controller, delete_employee_controller


@app.route("/employees", methods=['GET', 'POST'])
def list_create_employees():
    if request.method == 'GET': return get_all_employees_controller()
    if request.method == 'POST':
        return create_employee_controller()
    else:
        return 'Method is Not Allowed'


@app.route("/employees/<employee_id>", methods=['GET', 'PUT', 'DELETE'])
def retrieve_update_destroy_employees(employee_id):
    if request.method == 'GET': return get_employee_controller(employee_id)
    if request.method == 'PUT': return update_employee_controller(employee_id)
    if request.method == 'DELETE':
        return delete_employee_controller(employee_id)
    else:
        return 'Method is Not Allowed'
