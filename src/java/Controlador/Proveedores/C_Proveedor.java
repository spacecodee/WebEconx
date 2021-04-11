package Controlador.Proveedores;

import Modelo.CategoriaProducto.M_ComboCategoria;
import Modelo.Proveedores.M_Proveedor;
import SQL.Proveedores.S_Proveedores;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Proveedor extends HttpServlet {

    M_Proveedor mProveedor = new M_Proveedor();
    S_Proveedores sProveedores = new S_Proveedores();
    String dni, nombre, apellidos, correo, telefono, buscar;
    int id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuProveedores");
        String accionPro = request.getParameter("accionProveedores");

        if (menu.equalsIgnoreCase("menu")) {
            switch (accionPro) {
                case "Listar":
                    List lista = sProveedores.listar();
                    request.setAttribute("provee", lista);
                    break;

                case "Agregar":
                    dni = request.getParameter("txtDni");
                    nombre = request.getParameter("txtNom");
                    apellidos = request.getParameter("txtApe");
                    correo = request.getParameter("txtCorreo");
                    telefono = request.getParameter("txtTelefono");

                    mProveedor.setDni(dni.trim());
                    mProveedor.setNombre(nombre.trim());
                    mProveedor.setApellidos(apellidos);
                    mProveedor.setCorreoElectronico(correo);
                    mProveedor.setTelefono(telefono);

                    sProveedores.agregar(mProveedor);
                    request.getRequestDispatcher("C_Proveedor?menuProveedores=menu&accionProveedores=Listar").forward(request, response);
                    break;

                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));

                    mProveedor = sProveedores.listaId(id);
                    request.setAttribute("prov", mProveedor);
                    request.getRequestDispatcher("C_Proveedor?menuProveedores=menu&accionProveedores=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    dni = request.getParameter("txtDni");
                    nombre = request.getParameter("txtNom");
                    apellidos = request.getParameter("txtApe");
                    correo = request.getParameter("txtCorreo");
                    telefono = request.getParameter("txtTelefono");

                    mProveedor.setDni(dni.trim());
                    mProveedor.setNombre(nombre.trim());
                    mProveedor.setApellidos(apellidos);
                    mProveedor.setCorreoElectronico(correo);
                    mProveedor.setTelefono(telefono);
                    mProveedor.setId(id);

                    sProveedores.actualizar(mProveedor);
                    request.getRequestDispatcher("C_Proveedor?menuProveedores=menu&accionProveedores=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));

                    sProveedores.delete(id);
                    request.getRequestDispatcher("C_Proveedor?menuProveedores=menu&accionProveedores=Listar").forward(request, response);
                    break;

                case "Buscar":
                    buscar = request.getParameter("txtBuscar");

                    List list = sProveedores.listar(buscar.trim());
                    request.setAttribute("provee", list);
                    request.getRequestDispatcher("proveedores.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("proveedores.jsp").forward(request, response);
        }

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
