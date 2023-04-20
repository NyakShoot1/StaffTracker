from sqlalchemy import inspect, Integer, Text, Date, Boolean
# from flask_validator import ValidateEmail, ValidateString, ValidateCountry
from sqlalchemy.orm import validates

from __init__ import db  # from __init__.py


class Employee_document(db.Model):
    employee_id = db.Column(db.Integer, db.ForeignKey('employee.employee_id'), primary_key=True, nullable=False)
    document_id = db.Column(db.Integer, db.ForeignKey('document.document_id'), nullable=False)
    # Relations
    employee = db.relationship("Employee", back_populates='employee_document')
    document = db.relationship("Document", back_populates='employee_document')

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}
