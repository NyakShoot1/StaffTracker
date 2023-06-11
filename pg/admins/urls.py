from flask import request

from app import app
from .controllers import auth_admin_controller


@app.route("/login", methods=['GET'])
def login_admin():
    if request.method == 'GET':
        login = request.form.get('login')
        password = request.form.get('password')
        return auth_admin_controller(login, password)
    else:
        return 'Method is Not Allowed'
