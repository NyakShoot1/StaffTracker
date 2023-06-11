from flask import request

from app import app
from .controllers import get_all_employees_controller, create_employee_controller, get_employee_controller, \
    update_employee_controller, delete_employee_controller, test_controller, get_all_active_employees_controller, \
    get_all_fired_employees_controller, fire_employee_controller


@app.route("/employees", methods=['GET', 'POST'])
def list_create_employees():
    if request.method == 'GET': return get_all_employees_controller()
    if request.method == 'POST':
        return create_employee_controller()
    else:
        return 'Method is Not Allowed'


@app.route("/employees/active", methods=['GET'])
def list_active_employees():
    if request.method == 'GET':
        return get_all_active_employees_controller()
    else:
        return 'Method is Not Allowed'


@app.route("/employees/fired", methods=['GET'])
def list_fired_employees():
    if request.method == 'GET':
        return get_all_fired_employees_controller()
    else:
        return 'Method is Not Allowed'


@app.route("/employees/<employee_id>", methods=['GET', 'PUT', 'DELETE'])
def retrieve_update_destroy_employees(employee_id):
    if request.method == 'GET': return get_employee_controller(employee_id)
    if request.method == 'PUT': return update_employee_controller(employee_id)
    if request.method == 'DELETE':
        return fire_employee_controller(employee_id)
    else:
        return 'Method is Not Allowed'


@app.route("/test", methods=['GET'])
def test():
    if request.method == 'GET': return test_controller()
