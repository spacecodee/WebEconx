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
                           data-target="#Actualizar" href="#">
                            Actualizar
                        </a>

                        <div class="ml-auto">
                            <form class="form-inline" action="C_CategoriaP?menuCategoriaP=menu" method="POST">
                                <input minlength="1" maxlength="4" type="search" name="txtBuscar" class="form-control" 
                                       required pattern="[0-9]+">
                                <input type="submit" name="accionCategoriaP" value="Buscar" class="btn btn-outline-success">
                            </form>
                        </div>

                    </div>
                    <br>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="visibility: hidden">#</th>
                                <th scope="col">C??digo</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="cat" items="${categoria}">
                                <tr>
                                    <th scope="row" style="visibility: hidden">${cat.getId()}</th>
                                    <td>${cat.getCodigo()}</td>
                                    <td>${cat.getCategoria()}</td>
                                    <td>
                                        <a class="btn btn-warning" 
                                           href="C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Editar&id=${cat.getId()}">
                                            Editar
                                        </a>

                                        <a class="btn btn-danger"
                                           href="C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Eliminar&id=${cat.getId()}">
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

                        <form action="C_CategoriaP?menuCategoriaP=menu" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">C??digo: </label>
                                    <input minlength="1" maxlength="4" type="text" name="txtCodigo" class="form-control" required 
                                           placeholder="C??digo" pattern="[0-9]+">

                                    <label class="col-form-label">Nombre:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtNom" class="form-control" required 
                                           placeholder="Nombre">
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionCategoriaP" value="Agregar"
                                        class="btn btn-primary">
                                    Guardar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="Actualizar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
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

                        <form action="C_CategoriaP?menuCategoriaP=menu" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">C??digo: </label>
                                    <input minlength="1" maxlength="4" type="text" name="txtCodigo" value="${cate.getCodigo()}"
                                           class="form-control" required 
                                           placeholder="C??digo" pattern="[0-9]+">

                                    <label class="col-form-label">Nombre:</label>
                                    <input minlength="1" maxlength="50" type="text" name="txtNom" value="${cate.getCategoria()}"
                                           class="form-control" required 
                                           placeholder="Nombre">
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionCategoriaP" value="Actualizar" 
                                        class="btn btn-primary">
                                    Guardar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
                                           
        <script src="js/main.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>
