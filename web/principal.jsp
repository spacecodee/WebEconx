<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <link rel="shortcut icon" type="image/png" href="img/logoeconx.png"/>
        <!-- Bootstrap core CSS -->
        <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

        <!-- Custom styles for this template -->
        <link href="css/simple-sidebar.css" rel="stylesheet">
        <title>ECONX INVENTORY 1.0</title>
    </head>
    <body>

        <div class="d-flex" id="wrapper">

            <!-- Sidebar -->
            <div class="text-black bg-primary border-right" id="sidebar-wrapper">
                <div class="sidebar-heading" style="color: #FFF">
                    ECONX
                </div>
                <div class="list-group list-group-flush">
                    <a href="C_Usuarios?menuUsuario=menuU&accionUsuario=Listar" style="color: #FFF"
                       class="list-group-item list-group-item-action bg-primary" target="myFrame">
                        <img src="img/1.png" alt="" width="10%"/>
                        Usuarios
                    </a>
                    <a href="C_Roll?menuRoll=menuR&accionRoll=Listar" style="color: #FFF"
                       class="list-group-item list-group-item-action bg-primary" target="myFrame">
                        <img src="img/roles1.png" alt="" width="10%"/>
                        Roles
                    </a>
                    <a href="C_Productos?menuProductos=menu&accionProductos=Listar" target="myFrame"
                       class="list-group-item list-group-item-action bg-primary" style="color: #FFF">
                        <img src="img/producto.png" alt="" width="10%"/>
                        Productos
                    </a>
                    <a href="C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Listar" target="myFrame"
                       class="list-group-item list-group-item-action bg-primary" style="color: #FFF">
                        <img src="img/cate.png" alt="" width="10%"/>
                        Categorias
                    </a>
                    <a href="C_Proveedor?menuProveedores=menu&accionProveedores=Listar" target="myFrame"
                       class="list-group-item list-group-item-action bg-primary" style="color: #FFF">
                        <img src="img/provedor.png" alt="" width="10%"/>
                        Proveedores
                    </a>
                    <a href="C_Intermedia?menuIntermedia=menu&accionIntermedia=Listar" target="myFrame" 
                       class="list-group-item list-group-item-action bg-primary" style="color: #FFF">
                        <img src="img/provedorpro.png" alt="" width="10%"/>
                        Producto - Proveedor
                    </a>
                    <a href="C_Boleta?menuBoleta=menu&accionBoleta=Listar" target="myFrame" 
                       class="list-group-item list-group-item-action bg-primary" style="color: #FFF">
                        <img src="img/boleta.png" alt="" width="10%"/>
                        Boletas
                    </a>
                </div>
            </div>
            <!-- /#sidebar-wrapper -->

            <!-- Page Content -->
            <div id="page-content-wrapper">

                <nav class="navbar navbar-expand-lg navbar-light bg-light border-bottom">
                    <button class="btn btn-primary" id="menu-toggle">
                        Menú
                    </button>

                    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent"
                            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>

                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ml-auto mt-2 mt-lg-0">
                            <li class="nav-item active">
                                <a class="nav-link" href="C_Menu?menuMen=Menu&accionMenu=inicio" target="myFrame">
                                    Dashboard
                                    <span class="sr-only">(current)</span>
                                </a>
                            </li>

                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                                   aria-haspopup="true" aria-expanded="false">
                                    ${usuario.getNombre()}
                                </a>
                                <div class="dropdown-menu dropdown-menu-right text-center" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="#">
                                        <img src="img/usuario-de-perfil.png" alt="Usuario" width="60"/>
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        ${usuario.getDni()}
                                    </a>
                                    <a class="dropdown-item" href="#">
                                        ${usuario.getCorreoElectronico()}
                                    </a>
                                    <div class="dropdown-divider"></div>
                                    <form action="C_Login" method="POST">
                                        <button name="accionLogin" value="Salir" class="dropdown-item">
                                            Cerrar Sesión
                                        </button>
                                    </form>
                                </div>
                            </li>
                        </ul>
                    </div>
                </nav>

                <div class="m-4" style="height: 1000px">
                    <iframe name="myFrame" style="height: 100%; width: 100%; border: none">

                    </iframe>
                </div>
            </div>
            <!-- /#page-content-wrapper -->

        </div>
        <!-- /#wrapper -->

        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="js/main.js" type="text/javascript"></script>
    </body>
</html>
