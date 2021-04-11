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
                            <form class="form-inline" action="C_Intermedia?menuIntermedia=menu" method="POST">
                                <input minlength="1" maxlength="4" type="search" name="txtBuscar" class="form-control" 
                                       required pattern="[0-9]+">
                                <input type="submit" name="accionIntermedia" value="Buscar" class="btn btn-outline-success">
                            </form>
                        </div>

                    </div>
                    <br>
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col" style="visibility: hidden">#</th>
                                <th scope="col">Código</th>
                                <th scope="col">Proveedor</th>
                                <th scope="col">Producto</th>
                                <th scope="col">Fecha</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="inter" items="${intermedia}">
                                <tr>
                                    <th scope="row" style="visibility: hidden">${inter.getId()}</th>
                                    <td>${inter.getCodigo()}</td>
                                    <td>${inter.getIdProve()}</td>
                                    <td>${inter.getIdProd()}</td>
                                    <td>${inter.getFecha()}</td>
                                    <td>
                                        <a class="btn btn-warning" 
                                           href="C_Intermedia?menuIntermedia=menu&accionIntermedia=Editar&id=${inter.getId()}">
                                            Editar
                                        </a>

                                        <a class="btn btn-danger"
                                           href="C_Intermedia?menuIntermedia=menu&accionIntermedia=Eliminar&id=${inter.getId()}">
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

                        <form action="C_Intermedia?menuIntermedia=menu" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">Código: </label>
                                    <input minlength="1" maxlength="4" type="text" name="txtCodigo" class="form-control" required 
                                           placeholder="Código" pattern="[0-9]+">

                                    <div>
                                        <label class="col-form-label">Proveedor: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProv(this.value);">
                                            <c:forEach var="comPv" items="${comboProv}">
                                                <option value="${comPv.getId()}">${comPv.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="prov" id="prov" class="form-control" 
                                               value="1" style="visibility: hidden"/>
                                    </div>

                                    <div>
                                        <label class="col-form-label">Producto: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProd(this.value);">
                                            <c:forEach var="comPd" items="${comboProd}">
                                                <option value="${comPd.getId()}">${comPd.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="prod" id="prod" class="form-control" 
                                               value="1" style="visibility: hidden"/>
                                    </div>

                                    <input type="date" name="txtFecha" class="form-control" required 
                                           placeholder="Fecha" >
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionIntermedia" value="Agregar"
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

                        <form action="C_Intermedia?menuIntermedia=menu" method="POST">
                            <div class="form-group">
                                <div class="container">
                                    <label class="col-form-label">Código: </label>
                                    <input minlength="1" maxlength="4" type="text" name="txtCodigo" value="${interme.getCodigo()}"
                                           class="form-control" required 
                                           placeholder="Código" pattern="[0-9]+">

                                    <div>
                                        <label class="col-form-label">Proveedor: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProvC(this.value);">
                                            <c:forEach var="comPv" items="${comboProv}">
                                                <option value="${comPv.getId()}">${comPv.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="provc" id="provc" class="form-control" 
                                               value="1" style="visibility: hidden"/>
                                    </div>

                                    <div>
                                        <label class="col-form-label">Producto: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProdC(this.value);">
                                            <c:forEach var="comPd" items="${comboProd}">
                                                <option value="${comPd.getId()}">${comPd.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="prodc" id="prodc" class="form-control" 
                                               value="1" style="visibility: hidden"/>
                                    </div>

                                    <input type="date" name="txtFecha" class="form-control" required value="${interme.getFecha()}"
                                           placeholder="Fecha">
                                    
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionIntermedia" value="Actualizar" 
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
            var mostrarProv = function (x) {
                document.getElementById('prov').value = x;
            }

            var mostrarProd = function (x) {
                document.getElementById('prod').value = x;
            }
            
            var mostrarProvC = function (x) {
                document.getElementById('provc').value = x;
            }

            var mostrarProdC = function (x) {
                document.getElementById('prodc').value = x;
            }
        </script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>
