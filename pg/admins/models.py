from sqlalchemy import inspect, Integer, Text

from __init__ import db  # from __init__.py


class Admin(db.Model):
    admin_id = db.Column(db.Integer, primary_key=True, nullable=False, unique=True)
    admin_login = db.Column(db.Text, nullable=False)
    admin_pass = db.Column(db.Text, nullable=False)

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}
