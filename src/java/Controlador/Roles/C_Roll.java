package Controlador.Roles;

import Modelo.Roles.M_Roll;
import SQL.Roll.S_Roll;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Roll extends HttpServlet {

    M_Roll mRoll = new M_Roll();
    S_Roll sRoll = new S_Roll();

    String codigo, roll;
    int idRoll;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuRoll");
        String accionUs = request.getParameter("accionRoll");

        if (menu.equalsIgnoreCase("menuR")) {
            switch (accionUs) {
                case "Listar":
                    List lista = sRoll.listar();
                    request.setAttribute("roll", lista);
                    break;

                case "Agregar":
                    codigo = request.getParameter("txtCodigo");
                    roll = request.getParameter("txtNom");

                    mRoll.setCodigo(codigo.trim());
                    mRoll.setRoll(roll.trim());

                    sRoll.agregarRoles(mRoll);
                    request.getRequestDispatcher("C_Roll?menuRoll=menuR&accionRoll=Listar").forward(request, response);
                    break;

                case "Editar":
                    idRoll = Integer.parseInt(request.getParameter("idRoll"));

                    mRoll = sRoll.listaId(idRoll);
                    request.setAttribute("rol", mRoll);
                    request.getRequestDispatcher("C_Roll?menuRoll=menuR&accionRoll=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    codigo = request.getParameter("txtCod");
                    roll = request.getParameter("txtN");

                    mRoll.setCodigo(codigo.trim());
                    mRoll.setRoll(roll.trim());
                    mRoll.setId(idRoll);
                    sRoll.actualizarRoles(mRoll);
                    request.getRequestDispatcher("C_Roll?menuRoll=menuR&accionRoll=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idRoll = Integer.parseInt(request.getParameter("idRoll"));

                    sRoll.delete(idRoll);
                    request.getRequestDispatcher("C_Roll?menuRoll=menuR&accionRoll=Listar").forward(request, response);
                    break;

                case "Buscar":
                    String buscar = request.getParameter("txtBuscar");
                    List list = sRoll.listar(buscar.trim());
                    request.setAttribute("roll", list);
                    request.getRequestDispatcher("roles.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("roles.jsp").forward(request, response);
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
