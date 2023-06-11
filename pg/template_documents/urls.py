from flask import request

from app import app
from .controllers import create_template_document_controller


@app.route("/template_document", methods=['POST'])
def create_template_document():
    if request.method == 'POST':
        return create_template_document_controller()
    else:
        return 'Method is Not Allowed'