from xmlrpc.client import ResponseError

from flask import request, jsonify
from datetime import timedelta, datetime

from __init__ import db, minio_client
from .models import Employee


# ----------------------------------------------- #

# Query Object Methods => https://docs.sqlalchemy.org/en/14/orm/query.html#sqlalchemy.orm.Query
# Session Object Methods => https://docs.sqlalchemy.org/en/14/orm/session_api.html#sqlalchemy.orm.Session
# How to serialize SqlAlchemy PostgreSQL Query to JSON => https://stackoverflow.com/a/46180522

def get_all_employees_controller():
    """
    Функция возвращает список всех сотрудников
    """
    employees = Employee.query.all()
    response = []
    for employee in employees: response.append(employee.toDict())
    return jsonify(response)


def create_employee_controller():
    """
    Функция добавляет сотрудника в базу данных, а также создаёт папки для документов
    и фото в _minio
    """
    request_form = request.form.to_dict()

    new_employee = Employee(
        full_name=request_form['full_name'],
        birthday=request_form['birthday'],
        post=request_form['post'],
        department=request_form['department'],
        hire_date=datetime.now(),
        fire_date=None,
        status=True
    )
    db.session.add(new_employee)
    db.session.commit()

    filename = 'init_file.txt'
    with open(filename, 'w') as f:
        f.write('init_file')

    try:
        minio_client.fput_object(bucket_name="employees",
                                 object_name=f"{new_employee.employee_id}/documents/init_file.txt", file_path=filename)
        minio_client.fput_object(bucket_name="employees",
                                 object_name=f"{new_employee.employee_id}/photos/init_file.txt", file_path=filename)
    except ResponseError as err:
        print(err)

    import os
    os.remove(filename)

    response = Employee.query.get(new_employee.employee_id).toDict()
    return jsonify(response)


def get_employee_controller(employee_id):
    """
    Функция получает данные сотрудника по employee_id
    """
    response = Employee.query.get(employee_id).toDict()
    return jsonify(response)


def update_employee_controller(employee_id):
    """
    Функция обновляет данные сотрудника
    """
    request_form = request.form.to_dict()
    employee = Employee.query.get(employee_id)

    # employee.employee_id = employee_id,
    employee.full_name = request_form['full_name'],
    employee.birthday = request_form['birthday'],
    employee.post = request_form['post'],
    employee.department = request_form['department'],
    employee.hire_date = request_form['hire_date'],
    employee.fire_date = request_form['fire_date'],
    employee.status = bool(request_form['status'])
    db.session.commit()

    response = Employee.query.get(employee_id).toDict()
    return jsonify(response)


def delete_employee_controller(employee_id):
    """
    Функциия удаляет сотрудника(увольняет??)
    """
    Employee.query.filter_by(id=employee_id).delete()
    db.session.commit()

    return 'Employee with Id "{}" deleted successfully!'.format(employee_id)
