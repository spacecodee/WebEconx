package Controlador.ProducProve;

import Modelo.CategoriaProducto.M_ComboCategoria;
import Modelo.ProducProve.M_ComboProduc;
import Modelo.ProducProve.M_ComboProv;
import Modelo.ProducProve.M_ProductoProve;
import Modelo.ProducProve.M_RProductoProve;
import SQL.ProductoProve.S_ProdProve;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Intermedia extends HttpServlet {

    M_ProductoProve mIntermedia = new M_ProductoProve();
    M_RProductoProve mIntermediaR = new M_RProductoProve();
    M_ComboProv mComboProve = new M_ComboProv();
    M_ComboProduc mComboProd = new M_ComboProduc();
    S_ProdProve sIntermedia = new S_ProdProve();

    String codigo, fecha, buscar;
    int idProv, idProd, id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuIntermedia");
        String accion = request.getParameter("accionIntermedia");

        if (menu.equalsIgnoreCase("menu")) {
            switch (accion) {
                case "Listar":
                    List lista = sIntermedia.listar();
                    ArrayList<M_ComboProv> mComboProv = sIntermedia.mComboProvee();
                    ArrayList<M_ComboProduc> mComboPro = sIntermedia.mComboProd();
                    request.setAttribute("intermedia", lista);
                    request.setAttribute("comboProv", mComboProv);
                    request.setAttribute("comboProd", mComboPro);
                    break;

                case "Agregar":
                    codigo = request.getParameter("txtCodigo");
                    idProv = Integer.parseInt(request.getParameter("prov"));
                    idProd = Integer.parseInt(request.getParameter("prod"));
                    fecha = request.getParameter("txtFecha");

                    mIntermediaR.setCodigo(codigo.trim());
                    mIntermediaR.setIdProve(idProv);
                    mIntermediaR.setIdProd(idProd);
                    mIntermediaR.setFecha(fecha);

                    sIntermedia.agregar(mIntermediaR);
                    request.getRequestDispatcher("C_Intermedia?menuIntermedia=menu&accionIntermedia=Listar").forward(request, response);
                    break;

                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));

                    mIntermediaR = sIntermedia.listaId(id);
                    request.setAttribute("interme", mIntermediaR);
                    request.getRequestDispatcher("C_Intermedia?menuIntermedia=menu&accionIntermedia=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    codigo = request.getParameter("txtCodigo");
                    idProv = Integer.parseInt(request.getParameter("provc"));
                    idProd = Integer.parseInt(request.getParameter("prodc"));
                    fecha = request.getParameter("txtFecha");
                    //id = Integer.parseInt(request.getParameter("id"));
                    
                    mIntermediaR.setCodigo(codigo.trim());
                    mIntermediaR.setIdProve(idProv);
                    mIntermediaR.setIdProd(idProd);
                    mIntermediaR.setFecha(fecha);
                    mIntermediaR.setId(id);

                    sIntermedia.actualizar(mIntermediaR);
                    request.getRequestDispatcher("C_Intermedia?menuIntermedia=menu&accionIntermedia=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));

                    sIntermedia.delete(id);
                    request.getRequestDispatcher("C_Intermedia?menuIntermedia=menu&accionIntermedia=Listar").forward(request, response);
                    break;

                case "Buscar":
                    buscar = request.getParameter("txtBuscar");
                    
                    List list = sIntermedia.listar(buscar);
                    ArrayList<M_ComboProv> mComboProved = sIntermedia.mComboProvee();
                    ArrayList<M_ComboProduc> mComboProdc = sIntermedia.mComboProd();
                    request.setAttribute("intermedia", list);
                    request.setAttribute("comboProv", mComboProved);
                    request.setAttribute("comboProd", mComboProdc);
                    
                    request.getRequestDispatcher("intermedia.jsp").forward(request, response);
                    break;

                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("intermedia.jsp").forward(request, response);
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
