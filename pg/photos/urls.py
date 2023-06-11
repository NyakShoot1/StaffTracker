from flask import request

from app import app
from .controllers import create_photo_controller, get_photo_controller, get_employee_photos_controller


@app.route("/photos/<employee_id>", methods=['POST', 'GET'])
def create_photo(employee_id):
    if request.method == 'POST': return create_photo_controller(employee_id)
    if request.method == 'GET': return get_employee_photos_controller(employee_id)
    else: return 'Method is Not Allowed'


@app.route("/photos/<path:photo_url>", methods=['GET'])
def get_photo(photo_url):
    if request.method == 'GET': return get_photo_controller(photo_url)
    else: return 'Method is Not Allowed'
