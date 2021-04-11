package Controlador.CategoriaPrd;

import Modelo.CategoriaProducto.M_CategoriaP;
import Modelo.CategoriaProducto.M_ComboCategoria;
import SQL.CategoriaPro.S_CategoriaP;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_CategoriaP extends HttpServlet {

    S_CategoriaP sCategoriaP = new S_CategoriaP();
    M_CategoriaP mCategoriaP = new M_CategoriaP();

    String codigo, nombre, buscar;
    int id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuCategoriaP");
        String accionPro = request.getParameter("accionCategoriaP");

        if (menu.equalsIgnoreCase("menu")) {
            switch (accionPro) {
                case "Listar":
                    List lista = sCategoriaP.listar();
                    request.setAttribute("categoria", lista);
                    break;

                case "Agregar":
                    codigo = request.getParameter("txtCodigo");
                    nombre = request.getParameter("txtNom");

                    mCategoriaP.setCodigo(codigo.trim());
                    mCategoriaP.setCategoria(nombre.trim());

                    sCategoriaP.agregarCategoriasP(mCategoriaP);
                    request.getRequestDispatcher("C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Listar").forward(request, response);
                    break;

                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));

                    mCategoriaP = sCategoriaP.listaId(id);
                    request.setAttribute("cate", mCategoriaP);
                    request.getRequestDispatcher("C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    codigo = request.getParameter("txtCodigo");
                    nombre = request.getParameter("txtNom");

                    mCategoriaP.setCodigo(codigo.trim());
                    mCategoriaP.setCategoria(nombre.trim());
                    mCategoriaP.setId(id);

                    sCategoriaP.actualizarCategoriaP(mCategoriaP);
                    request.getRequestDispatcher("C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));

                    sCategoriaP.delete(id);
                    request.getRequestDispatcher("C_CategoriaP?menuCategoriaP=menu&accionCategoriaP=Listar").forward(request, response);
                    break;

                case "Buscar":
                    buscar = request.getParameter("txtBuscar");

                    List list = sCategoriaP.listar(buscar.trim());
                    request.setAttribute("categoria", list);
                    request.getRequestDispatcher("categoriaPro.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("categoriaPro.jsp").forward(request, response);
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
