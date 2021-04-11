package Controlador.Productos;

import Modelo.CategoriaProducto.M_ComboCategoria;
import Modelo.Productos.M_AProducto;
import Modelo.Productos.M_Productos;
import Modelo.Roles.M_ComboRol;
import SQL.Productos.S_Productos;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Productos extends HttpServlet {

    M_Productos mProductos = new M_Productos();
    S_Productos sProductos = new S_Productos();
    M_AProducto mAProducto = new M_AProducto();

    String codigo, nombre, buscar;
    int id, categoria, stock;
    double precio;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuProductos");
        String accionPro = request.getParameter("accionProductos");

        if (menu.equalsIgnoreCase("menu")) {
            switch (accionPro) {
                case "Listar":
                    List lista = sProductos.listar();
                    ArrayList<M_ComboCategoria> mComboCate = sProductos.mComboCategoria();
                    request.setAttribute("producto", lista);
                    request.setAttribute("combo", mComboCate);
                    break;
                    
                case "Agregar":
                    codigo = request.getParameter("txtCodigo");
                    nombre = request.getParameter("txtNom");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    stock = Integer.parseInt(request.getParameter("txtStock"));
                    categoria = Integer.parseInt(request.getParameter("quantity"));

                    mAProducto.setCodigo(codigo.trim());
                    mAProducto.setNombre(nombre.trim());
                    mAProducto.setPrecio(precio);
                    mAProducto.setStock(stock);
                    mAProducto.setCategoria(categoria);
                    //txtNacimiento

                    sProductos.agregarProductos(mAProducto);
                    request.getRequestDispatcher("C_Productos?menuProductos=menu&accionProductos=Listar").forward(request, response);
                    break;

                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));

                    mAProducto = sProductos.listaId(id);
                    request.setAttribute("prod", mAProducto);
                    request.getRequestDispatcher("C_Productos?menuProductos=menu&accionProductos=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    codigo = request.getParameter("txtCodigo");
                    nombre = request.getParameter("txtNom");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    stock = Integer.parseInt(request.getParameter("txtStock"));
                    categoria = Integer.parseInt(request.getParameter("categoria"));
                    //id = Integer.parseInt(request.getParameter("id"));

                    mAProducto.setCodigo(codigo.trim());
                    mAProducto.setNombre(nombre.trim());
                    mAProducto.setPrecio(precio);
                    mAProducto.setStock(stock);
                    mAProducto.setCategoria(categoria);
                    //mAProducto.setId(id);

                    sProductos.actualizarProductos(mAProducto);
                    request.getRequestDispatcher("C_Productos?menuProductos=menu&accionProductos=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));

                    sProductos.delete(id);
                    request.getRequestDispatcher("C_Productos?menuProductos=menu&accionProductos=Listar").forward(request, response);
                    break;

                case "Buscar":
                    buscar = request.getParameter("txtBuscar");
                    
                    List list = sProductos.listar(buscar.trim());
                    ArrayList<M_ComboCategoria> mComboB = sProductos.mComboCategoria();
                    request.setAttribute("producto", list);
                    request.setAttribute("combo", mComboB);
                    request.getRequestDispatcher("productos.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("productos.jsp").forward(request, response);
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
