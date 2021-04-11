<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="img/logoeconx.png"/>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js"></script>
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.css" />
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <title>ECONX INVENTORY 1.0</title>
    </head>
    <body>

        <div class="container">
            <div class="row">

                <div class="col-md-4">
                    <div class="card-counter info">
                        <i class="fa fa-users"></i>
                        <span class="count-numbers">
                            ${emple}
                        </span>
                        <span class="count-name">
                            Empleados
                        </span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter danger">
                        <i class="fa fa-ticket"></i>
                        <span class="count-numbers">
                            ${produc}
                        </span>
                        <span class="count-name">
                            Productos
                        </span>
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card-counter success">
                        <i class="fa fa-database"></i>
                        <span class="count-numbers">
                            ${provee}
                        </span>
                        <span class="count-name">
                            Proveedores
                        </span>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
