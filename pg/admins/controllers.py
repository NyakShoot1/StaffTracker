from flask import jsonify

from .models import Admin


def auth_admin_controller(login, password):

    admin = Admin.query.filter_by(admin_login=login).first()

    if admin and admin.admin_pass == password:
        return jsonify({'message': 'Login successful'})
    else:
        return jsonify({'message': 'Invalid credentials'})

