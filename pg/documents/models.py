from sqlalchemy import inspect, Integer, Text, Date, Boolean
#from flask_validator import ValidateEmail, ValidateString, ValidateCountry
from sqlalchemy.orm import validates

from __init__ import db  # from __init__.py


class Document(db.Model):
    document_id = db.Column(db.Integer, primary_key=True, nullable=False, unique=True)
    document_name = db.Column(db.Text, nullable=False)
    document_date = db.Column(db.Date)
    document_url = db.Column(db.Text, nullable=False)

    employee_document = db.relationship("Employee_document", back_populates="document")

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}
