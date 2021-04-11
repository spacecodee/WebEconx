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
                        
                        <a class="btn btn-outline-success" data-toggle="modal" 
                           data-target="#Boleta" href="#">
                            Realizar una boleta
                        </a>
                        
                        <a class="btn btn-outline-success" href="todo.jsp" target="_black">
                            Realizar boleta completa
                        </a>

                        <div class="ml-auto">
                            <form class="form-inline" action="C_Boleta?menuBoleta=menu" method="POST">
                                <input minlength="1" maxlength="4" type="search" name="txtBuscar" class="form-control" 
                                       required pattern="[0-9]+">
                                <input type="submit" name="accionBoleta" value="Buscar" class="btn btn-outline-success">
                            </form>
                        </div>

                    </div>
                    <br>
                    <table class="table table-hover">
                        <thead>
                            <tr class="text-center">
                                <th scope="col" style="visibility: hidden">#</th>
                                <th scope="col" style="visibility: hidden">Trae</th>
                                <th scope="col">Proveedor</th>
                                <th scope="col">Producto</th>
                                <th scope="col">DNI U.</th>
                                <th scope="col">Nombre</th>
                                <th scope="col">Código Bol.</th>
                                <th scope="col">Precio</th>
                                <th scope="col">Cantidad</th>
                                <th scope="col">Empresa</th>
                                <th scope="col">Acciones</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="bol" items="${boleta}">
                                <tr class="text-center">
                                    <th scope="row" style="visibility: hidden">${bol.getId()}</th>
                                    <td style="visibility: hidden">${bol.getCodigoTrae()}</td>
                                    <td>${bol.getNombreProveedor()}</td>
                                    <td>${bol.getNombreProducto()}</td>
                                    <td>${bol.getDnUsuario()}</td>
                                    <td>${bol.getNombreUsuario()}</td>
                                    <td>${bol.getCodigoBoleta()}</td>
                                    <td>${bol.getPrecio()}</td>
                                    <td>${bol.getCantidad()}</td>
                                    <td>${bol.getEmpresa()}</td>
                                    <td class="ml-auto">
                                        <a class="btn btn-warning" style="padding: 5px 22px; margin-bottom: 4px"
                                           href="C_Boleta?menuBoleta=menu&accionBoleta=Editar&id=${bol.getId()}">
                                            Editar
                                        </a>
                                            
                                        <a class="btn btn-danger" style="padding: 5px 15px"
                                           href="C_Boleta?menuBoleta=menu&accionBoleta=Eliminar&id=${bol.getId()}">
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

                        <form action="C_Boleta?menuBoleta=menu" method="POST">
                            <div class="form-group">
                                <div class="container">

                                    <div>
                                        <label class="col-form-label">Código del producto y proveedor: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProv(this.value);">
                                            <c:forEach var="comI" items="${comboIn}">
                                                <option value="${comI.getId()}">${comI.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="inte" id="inte" class="form-control" 
                                               value="1" style="visibility: collapse"/>
                                    </div>

                                    <div>
                                        <label class="col-form-label">DNI del usuario comprador: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProd(this.value);">
                                            <c:forEach var="comUs" items="${comboU}">
                                                <option value="${comUs.getId()}">${comUs.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="usu" id="usu" class="form-control" 
                                               value="1" style="visibility: collapse"/>
                                    </div>

                                    <label class="col-form-label">Código: </label>
                                    <input minlength="1" maxlength="4" type="text" name="txtCodigo" class="form-control" required 
                                           placeholder="Código" pattern="[0-9]+">

                                    <label class="col-form-label">Precio </label>
                                    <input minlength="1" maxlength="50" type="text" name="txtPrecio" class="form-control" required 
                                           placeholder="Precio">

                                    <label class="col-form-label">Cantidad </label>
                                    <input minlength="1" maxlength="50" type="number" name="txtCantidad" class="form-control" required 
                                           placeholder="Cantidad" pattern="[0-9]+">

                                    <label class="col-form-label">Empresa </label>
                                    <input minlength="1" maxlength="50" type="text" name="txtEmpresa" class="form-control" required 
                                           placeholder="Empresa">
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionBoleta" value="Agregar"
                                        class="btn btn-primary">
                                    Guardar
                                </button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="modal fade" id="Boleta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" 
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            Hacer una boleta
                        </h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>

                    <div class="modal-body">

                        <form action="reporte.jsp" target="_black">
                            <div class="form-group">
                                <div class="container">

                                    <label class="col-form-label">DNI del usuario comprador</label>
                                    <input minlength="1" maxlength="8" type="text" name="txtDni" class="form-control" required 
                                           placeholder="DNI" pattern="[0-9]+">

                                    <label class="col-form-label">Fecha de compra </label>
                                    <input type="date" name="txtFec" class="form-control" required 
                                           placeholder="Fecha de compra" >
                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit"
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

                        <form action="C_Boleta?menuBoleta=menu" method="POST">
                            <div class="form-group">
                                <div class="container">

                                    <div>
                                        <label class="col-form-label">Código del producto y proveedor: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProvC(this.value);">
                                            <c:forEach var="comI" items="${comboIn}">
                                                <option value="${comI.getId()}">${comI.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="intec" id="intec" class="form-control" 
                                               value="1" style="visibility: collapse"/>
                                    </div>

                                    <div>
                                        <label class="col-form-label">DNI del usuario comprador: </label>
                                        <select name="sel_quantity" id="sel_quantity" class="custom-select my-1 mr-sm-2"
                                                onChange="mostrarProdC(this.value);">
                                            <c:forEach var="comUs" items="${comboU}">
                                                <option value="${comUs.getId()}">${comUs.getNombre()}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="usuc" id="usuc" class="form-control" 
                                               value="1" style="visibility: collapse"/>
                                    </div>

                                    <label class="col-form-label">Código: </label>
                                    <input minlength="1" maxlength="8" type="number" name="txtCodigo" value="${mostrarBol.getCodigoBoleta()}"
                                           class="form-control" required 
                                           placeholder="Código" pattern="[0-9]+">

                                    <label class="col-form-label">Precio </label>
                                    <input minlength="1" maxlength="50" type="text" name="txtPrecio" value="${mostrarBol.getPrecio()}"
                                           class="form-control" required 
                                           placeholder="Precio" >

                                    <label class="col-form-label">Cantidad </label>
                                    <input minlength="1" maxlength="50" type="number" name="txtCantidad" value="${mostrarBol.getCantidad()}"
                                           class="form-control" required 
                                           placeholder="Cantidad" pattern="[0-9]+">

                                    <label class="col-form-label">Empresa </label>
                                    <input minlength="1" maxlength="50" type="text" name="txtEmpresa" value="${mostrarBol.getEmpresa()}"
                                           class="form-control" required 
                                           placeholder="Empresa">

                                </div>
                            </div>

                            <div class="modal-footer">                        
                                <button type="button" class="btn btn-secondary" data-dismiss="modal">
                                    Cerrar
                                </button>

                                <button type="submit" name="accionBoleta" value="Actualizar" 
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
                document.getElementById('inte').value = x;
            }

            var mostrarProd = function (x) {
                document.getElementById('usu').value = x;
            }

            var mostrarProvC = function (x) {
                document.getElementById('intec').value = x;
            }

            var mostrarProdC = function (x) {
                document.getElementById('usuc').value = x;
            }
        </script>
        <script src="js/main.js" type="text/javascript"></script>
        <script src="vendor/jquery/jquery.min.js" type="text/javascript"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js" type="text/javascript"></script>

    </body>
</html>
