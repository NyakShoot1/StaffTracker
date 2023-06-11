from sqlalchemy import inspect, Integer, Text, Date, Boolean

from __init__ import db  # from __init__.py


class Photo(db.Model):
    photo_id = db.Column(db.Integer, primary_key=True, nullable=False, unique=True)
    photo_name = db.Column(db.Text, nullable=False)
    photo_date = db.Column(db.Date, nullable=False)
    photo_url = db.Column(db.Text, nullable=False)
    employee_id = db.Column(db.Integer, db.ForeignKey('employee.employee_id'), nullable=False)

    # Relations
    employee = db.relationship("Employee", back_populates='photo')

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}
