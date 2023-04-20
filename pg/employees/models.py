from sqlalchemy import inspect, Integer, Text, Date, Boolean, Sequence
#from flask_validator import ValidateEmail, ValidateString, ValidateCountry
from sqlalchemy.orm import validates

from __init__ import db  # from __init__.py


class Employee(db.Model):
    employee_id = db.Column(db.Integer, primary_key=True, nullable=False, unique=True)
    full_name = db.Column(db.Text, nullable=False)
    birthday = db.Column(db.Date)
    post = db.Column(db.Text, nullable=False)
    department = db.Column(db.Text, nullable=False)
    hire_date = db.Column(db.Date)
    fire_date = db.Column(db.Date, nullable=True)
    status = db.Column(db.Boolean)

    employee_document = db.relationship("Employee_document", back_populates="employee")

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}

    def __repr__(self):
        return "<%r>" % self.employee_id
