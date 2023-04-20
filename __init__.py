import os

from flask import Flask
from flask_sqlalchemy import SQLAlchemy
from flask_migrate import Migrate
from dotenv import load_dotenv
from minio import Minio

from config import config

load_dotenv()

db = SQLAlchemy()
migrate = Migrate()

minio_client = Minio(
    endpoint=os.getenv("MINIO_ENDPOINT"),
    access_key=os.getenv("MINIO_ACCESS_KEY"),
    secret_key=os.getenv("MINIO_SECRET_KEY"),
    secure=False
)


def create_app(config_mode):
    app = Flask(__name__)
    app.config.from_object(config[config_mode])

    db.init_app(app)
    migrate.init_app(app, db)

    return app
