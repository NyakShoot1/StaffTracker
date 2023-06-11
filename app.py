import os

# App Initialization
from __init__ import create_app  # from __init__ file

app = create_app(os.getenv("CONFIG_MODE"))


# Hello World!
@app.route('/', methods=['GET'])
def hello():
    return "Hello World!"


# Applications Routes
from _minio.documents.urls import *
from pg.employees.urls import *
from pg.documents.urls import *
from pg.employee_documents.urls import *
from pg.template_documents.urls import *
from pg.admins.urls import *
from pg.photos.urls import *

if __name__ == "__main__":
    # app.run()
    # app.run(host='10.147.19.177', port=5000, ssl_context="adhoc")
    app.run(host='10.147.19.177', port=5000)
