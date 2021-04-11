<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="img/logoeconx.png"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css?family=Poppins:600&display=swap" rel="stylesheet">
        <script src="https://kit.fontawesome.com/a81368914c.js"></script>
        <link href="css/styles.css" rel="stylesheet" type="text/css"/>
        <title>ECONX INVENTORY 1.0</title>
    </head>
    <body>

        <img class="wave transp" src="img/draw.png">

        <div class="container">
            <div class="img">
                <img src="img/invent.svg">
            </div>
            <div class="login-content">
                <form action="C_Login" method="POST">
                    <img src="img/people.svg">
                    <h2 class="title">Bienvenido</h2>
                    <div class="input-div one">
                        <div class="i">
                            <i class="fas fa-user"></i>
                        </div>
                        <div class="div">
                            <h5>Usuario</h5>
                            <input maxlength="8" type="text" class="input" name="txtUsuario" required pattern="[0-9]+">
                        </div>
                    </div>
                    <div class="input-div pass">
                        <div class="i">
                            <i class="fas fa-lock"></i>
                        </div>
                        <div class="div">
                            <h5>Contraseña</h5>
                            <input type="password" class="input" name="txtPass" required>
                        </div>
                    </div>
                    <a href="#">¿Olvidaste tu contraseña?</a>

                    <button type="submit" name="accionLogin" value="loginUsuario" class="btn">
                        Iniciar Sesion
                    </button>
                </form>
            </div>
        </div>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>
    </body>
</html>
