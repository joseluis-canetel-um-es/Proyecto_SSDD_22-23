from flask import Flask, render_template, send_from_directory, url_for, request, redirect
from flask_login import LoginManager, login_manager, current_user, login_user, login_required, logout_user
import requests
import os

# Usuarios
from models import users, User

# Login
from forms import LoginForm
# Signup
from forms import RegistrationForm

app = Flask(__name__, static_url_path='')
login_manager = LoginManager()
login_manager.init_app(app) # Para mantener la sesión

# Configurar el secret_key. OJO, no debe ir en un servidor git público.
# Python ofrece varias formas de almacenar esto de forma segura, que
# no cubriremos aquí.
app.config['SECRET_KEY'] = 'qH1vprMjavek52cv7Lmfe1FoCexrrV8egFnB21jHhkuOHm8hJUe1hwn7pKEZQ1fioUzDb3sWcNK1pJVVIhyrgvFiIrceXpKJBFIn_i9-LTLBCc4cqaI3gjJJHU6kxuT8bnC7Ng'

@app.route('/static/<path:path>')
def serve_static(path):
    return send_from_directory('static', path)

@app.route('/')
def index():
    return render_template('index.html')

@app.route('/login', methods=['GET', 'POST'])
def login():
    if current_user.is_authenticated:
        return redirect(url_for('index')) # le manda a la pagina de inicio (index.html)
    else:
        error = None
        form = LoginForm(None if request.method != 'POST' else request.form)
        if request.method == "POST" and form.validate():

            email = form.email.data
            password = form.password.data
            credenciales = { "email" : email, "password": password }

           
            # Llamar al backend para verificar las credenciales
            # Kholoud: el backend ya se encarga de incrementar numero de visitas
            # esto solo pasa si las credenciales son correctas           
            response = requests.post('http://localhost:8080/rest/checkLogin', json=credenciales)
            if response.status_code == 200: 
                user = User(int(response.json()['id']['string']), response.json()['name']['string'], form.email.data.encode('utf-8'), form.password.data.encode('utf-8'),response.json()['token']['string'], int(response.json()['visits']['string']), int(response.json()['videos']['string']))
                users.append(user)
                login_user(user, remember=form.remember_me.data)
                return redirect(url_for('profile', username=user.id))
            else:
                error = 'Email o contraseña incorrectos'    

            #if form.email.data != 'admin@um.es' or form.password.data != 'admin':
            #    error = 'Invalid Credentials. Please try again.'
            #else:
            #    user = User(1, 'admin', form.email.data.encode('utf-8'),
            #                form.password.data.encode('utf-8'))
            #    users.append(user)
            #    login_user(user, remember=form.remember_me.data)
            #    return redirect(url_for('index'))

        return render_template('login.html', form=form,  error=error)


# en esta funcion debe mostrar el perfil del usuario y sus datos almacenados 
@app.route('/u/<username>')
@login_required
def profile():
    return render_template('profile.html')

@app.route('/logout')
@login_required
def logout():
    logout_user()
    return redirect(url_for('index'))

@login_manager.user_loader
def load_user(user_id):
    for user in users:
        if user.id == int(user_id):
            return user
    return None

# nueva funcion para permitir registro de usuario -- KHOLOUD
@app.route('/signup', methods=['GET', 'POST'])
def signup():
    if current_user.is_authenticated:
        return redirect(url_for('index')) #si ya hay user 
    else:
        error = None
        form = RegistrationForm(None if request.method != 'POST' else request.form)
        if request.method == "POST" and form.validate():
            email = form.email.data
            name = form.name.data
            password = form.password.data
             # Aquí almacenar los datos del usuario en la base de datos
            # llamar a backend para peticion de almacenar
            credenciales_registro = {"email" : email,"name" : name,  "password" : password}
            response = requests.post('http://localhost:8080/checkSignup', json=credenciales_registro)
            if response.status_code == 200:
                # usuario ficticio
                user = User(email, name, password)
                users.append(user)

                login_user(user)  # Opcional: Inicia sesión automáticamente después del registro

                return redirect(url_for('index'))
        else:
            error = 'Validación de registro incorrecta'
    return render_template('signup.html', form=form, error=error)

# funcion para mostrar el numero de accesos


# funcion para mostrar bases de datos
@app.route('/u/<id>/db', methods=['GET', 'POST'])
@login_required
def userDatabases():
    
    url = 'http://localhost:8080/u/'+current_user.id +'/db'

    try:
        databasesResponse = requests.get(url)
    except:
        error = 'No se pudo obtener el contenido'
        return render_template('index.html', error)

    if databasesResponse.status_code == 200:

        databases = databasesResponse.json()
        return render_template('user_databases.html', databases = databases)

    else:

        error = 'No se pudo obtener contenido de bases de datos'
        return render_template('user_no_db.html', error = error)



if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
