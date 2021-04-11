<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/png" href="img/logoeconx.png"/>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css" 
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2" crossorigin="anonymous">
        
        <link href="css/usu.css" rel="stylesheet" type="text/css"/>
        
        <title>ECONX INVENTORY 1.0</title>
    </head>
    <body>

        <div class="col-sm-12">
            <div class="card">
                <div class="card-body">
                    <br>
                    <div class="d-flex col-sm-12 ml-auto">
                        <a class="btn btn-primary" data-toggle="modal" 
                           data-target="#agregar" href="#">
                            Agregar
                        </a>
                        <a class="btn btn-success" data-toggle="modal" 
                           data-target="#actualizar" href="#">
                            Actualizar
                        </a>

                        <div class="ml-auto">
                            <form class="form-inline" action="C_Usuarios?menuUsuario=menuU" method="POST">
                                <input minlength="1" maxlength="8" type="search" name="txtBuscar" class="form-control" 
                                       required pattern="[0-9]+">
                                <input type="submit" name="accionUsuario" value="Buscar" class="btn btn-outline-success">
                            </form>
                        </div>

                    </div>
                    <br>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="visibility: hidden">#</th>
                                <th scope="col">DNI</th>
                                <th scope="col">Nombres</th>
                                <th scope="col">Apellidos</th>
                                <th scope="col">Teléfono</th>
                                <th scope="col">Roll</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="usu" items="${usuario}">
                                <tr>
                                    <th scope="row" style="visibility: hidden">${usu.getId()}</th>
                                    <td>${usu.getDni()}</td>
                                    <td>${usu.getNombre()}</td>
                                    <td>${usu.getApellidos()}</td>
                                    <td>${usu.getTelefono()}</td>
                                    <td>${usu.getRoll()}</td>
                                    <td>
                                        <a class="btn btn-warning" 
                                           href="C_Usuarios?menuUsuario=menuU&accionUsuario=Editar&idUsua=${usu.getId()}">
                                            Editar
                                        </a>

                                        <a class="btn btn-danger"
                                           href="C_Usuarios?menuUsuario=menuU&accionUsuario=Eliminar&idUsua=${usu.getId()}">
                                            Eliminar
                                        </a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>



        <div class="modal fade" id="agregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            Agregar
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">

                        <form action="C_Usuarios?menuUsuario=menuU" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">DNI:</label>
                                    <input minlength="8" maxlength="8" type="text" name="txtDni" class="form-control" required 
                                           placeholder="DNI" pattern="[0-9]+">

                                    <label class="col-form-label">Contraseña: </label>
                                    <input minlength="10" maxlength="50"  type="password" name="txtPass" class="form-control" 
                                           required placeholder="Contraseña">

                                    <label class="col-form-label">Nombres:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtNom" class="form-control" required 
                                           placeholder="Nombre" pattern="[a-zA-Z]+">

                                    <label class="col-form-label">Apellidos:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtApe" class="form-control" required 
                                           placeholder="Apellidos" pattern="[a-zA-Z]+">

                                    <label class="col-form-label">Teléfono:</label>
                                    <input minlength="9" maxlength="9" type="text" name="txtTele" class="form-control" required 
                                           placeholder="965555123" pattern="[0-9]+">

                                    <label class="col-form-label">Correo Electrónico: </label>
                                    <input type="email" name="txtMail" class="form-control" required placeholder="mail@dominio.com">

                                    <label class="col-form-label">Fecha de Nacimiento: </label>
                                    <input type="date" name="txtNacimiento" class="form-control" required placeholder="2002-01-01">

                                    <div>
                                        <label class="col-form-label">Roll: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarValor(this.value);">
                                            <c:forEach var="com" items="${combo}">
                                                <option value="${com.getId()}">${com.getRoll()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="quantity" id="quantity" class="form-control" 
                                               value="1" style="visibility: hidden"/>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionUsuario" value="Agregar" 
                                        class="btn btn-primary">
                                    Guardar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="actualizar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            Actualizar
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">

                        <form action="C_Usuarios?menuUsuario=menuU" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">DNI:</label>
                                    <input minlength="8" maxlength="8" type="text" name="txtDni" value="${usua.getDni()}" 
                                           class="form-control" required placeholder="DNI" pattern="[0-9]+">

                                    <label class="col-form-label">Nombres:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtNom" value="${usua.getNombre()}" 
                                           class="form-control" required placeholder="Nombre">

                                    <label class="col-form-label">Apellidos:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtApe" value="${usua.getApellidos()}" 
                                           class="form-control" 
                                           required placeholder="Apellidos">

                                    <label class="col-form-label">Teléfono:</label>
                                    <input minlength="9" maxlength="9" type="text" name="txtTele" value="${usua.getTelefono()}" class="form-control" 
                                           required placeholder="965555123" pattern="[0-9]+">

                                    <div>
                                        <label class="col-form-label">Roll: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarRoll(this.value);">
                                            <c:forEach var="com" items="${combo}">
                                                <option value="${com.getId()}">${com.getRoll()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="roll" id="roll" class="form-control" 
                                               value="1" style="visibility: collapse"/>
                                    </div>
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionUsuario" value="Actualizar" 
                                        class="btn btn-primary">
                                    Guardar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <script>
            var mostrarValor = function (x) {
                document.getElementById('quantity').value = x;
            }

            var mostrarRoll = function (x) {
                document.getElementById('roll').value = x;
            }
        </script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>