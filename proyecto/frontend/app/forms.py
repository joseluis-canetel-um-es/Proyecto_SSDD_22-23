from flask_wtf import FlaskForm
from wtforms import (StringField, PasswordField, BooleanField, FileField, SubmitField)
from wtforms.validators import InputRequired, Length, Email, EqualTo


class LoginForm(FlaskForm):
    email = StringField('email', validators=[Email()])
    password = PasswordField('password', validators=[InputRequired()])
    remember_me = BooleanField('remember_me')

# clase definida para el formulario de registro
class RegistrationForm(FlaskForm):
    email = StringField('email', validators=[Email()])
    name = StringField('name', validators=[InputRequired()])
    password = PasswordField('password', validators=[InputRequired()])
    confirm_password = PasswordField('confirm password', validators=[InputRequired(), EqualTo('password')])
    submit = SubmitField('Sign Up')


# clase definida para el formulario de la base de datos
class DatabaseForm(FlaskForm):
    name = StringField('name', validators=[InputRequired()])
    submit = SubmitField('Create database')

