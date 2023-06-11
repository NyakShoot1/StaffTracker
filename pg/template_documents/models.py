from sqlalchemy import inspect, Integer, Text, Date, Boolean

from __init__ import db  # from __init__.py


class Template_document(db.Model):
    document_id = db.Column(db.Integer, primary_key=True, nullable=False, unique=True)
    document_name = db.Column(db.Text, nullable=False)
    document_date = db.Column(db.Date)
    document_url = db.Column(db.Text, nullable=False)

    def toDict(self):
        return {c.key: getattr(self, c.key) for c in inspect(self).mapper.column_attrs}
