package Controlador.Boleta;

import Modelo.Boleta.M_Boleta;
import Modelo.Boleta.M_ComboIntermedia;
import Modelo.Boleta.M_ComboUsuario;
import Modelo.Boleta.M_MBoleta;
import Modelo.ProducProve.M_ComboProduc;
import Modelo.ProducProve.M_ComboProv;
import SQL.Boleta.S_Boleta;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Boleta extends HttpServlet {

    S_Boleta sBoleta = new S_Boleta();
    M_Boleta mBoleta = new M_Boleta();
    M_MBoleta mMostrarBoleta = new M_MBoleta();
    M_ComboIntermedia mComboInter = new M_ComboIntermedia();
    M_ComboUsuario mComboUsuario = new M_ComboUsuario();

    String codigo, empresa, buscar;
    int idIntermedia, idUsuario, id, cantidad;
    double precio;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuBoleta");
        String accion = request.getParameter("accionBoleta");

        if (menu.equalsIgnoreCase("menu")) {
            switch (accion) {
                case "Listar":
                    List lista = sBoleta.listar();
                    ArrayList<M_ComboIntermedia> mComboIn = sBoleta.mComboIntermedia();
                    ArrayList<M_ComboUsuario> mComboUsu = sBoleta.mComboUsuario();
                    request.setAttribute("boleta", lista);
                    request.setAttribute("comboIn", mComboIn);
                    request.setAttribute("comboU", mComboUsu);
                    break;

                case "Agregar":
                    idIntermedia = Integer.parseInt(request.getParameter("inte"));
                    idUsuario = Integer.parseInt(request.getParameter("usu"));
                    codigo = request.getParameter("txtCodigo");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    empresa = request.getParameter("txtEmpresa");

                    mBoleta.setIdIntermedia(idIntermedia);
                    mBoleta.setIdComprador(idUsuario);
                    mBoleta.setCodigo(codigo.trim());
                    mBoleta.setPrecio(precio);
                    mBoleta.setCantidad(cantidad);
                    mBoleta.setEmpresa(empresa);

                    sBoleta.agregar(mBoleta);
                    request.getRequestDispatcher("C_Boleta?menuBoleta=menu&accionBoleta=Listar").forward(request, response);
                    break;

                case "Editar":
                    id = Integer.parseInt(request.getParameter("id"));

                    mMostrarBoleta = sBoleta.listaId(id);
                    request.setAttribute("mostrarBol", mMostrarBoleta);
                    request.getRequestDispatcher("C_Boleta?menuBoleta=menu&accionBoleta=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    idIntermedia = Integer.parseInt(request.getParameter("intec"));
                    idUsuario = Integer.parseInt(request.getParameter("usuc"));
                    codigo = request.getParameter("txtCodigo");
                    precio = Double.parseDouble(request.getParameter("txtPrecio"));
                    cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
                    empresa = request.getParameter("txtEmpresa");
                    //id = Integer.parseInt(request.getParameter("id"));

                    mBoleta.setIdIntermedia(idIntermedia);
                    mBoleta.setIdComprador(idUsuario);
                    mBoleta.setCodigo(codigo.trim());
                    mBoleta.setPrecio(precio);
                    mBoleta.setCantidad(cantidad);
                    mBoleta.setEmpresa(empresa);
                    mBoleta.setId(id);

                    sBoleta.actualizar(mBoleta);
                    request.getRequestDispatcher("C_Boleta?menuBoleta=menu&accionBoleta=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    id = Integer.parseInt(request.getParameter("id"));

                    sBoleta.delete(id);
                    request.getRequestDispatcher("C_Boleta?menuBoleta=menu&accionBoleta=Listar").forward(request, response);
                    break;

                case "Buscar":
                    buscar = request.getParameter("txtBuscar");

                    List list = sBoleta.listar(buscar);
                    ArrayList<M_ComboIntermedia> mComboI = sBoleta.mComboIntermedia();
                    ArrayList<M_ComboUsuario> mComboUs = sBoleta.mComboUsuario();
                    request.setAttribute("boleta", list);
                    request.setAttribute("comboIn", mComboI);
                    request.setAttribute("comboU", mComboUs);

                    request.getRequestDispatcher("boleta.jsp").forward(request, response);
                    break;

                case "Boleta":
                    String fecha,
                     nombre,
                     dni;

                    dni = request.getParameter("txtDni");
                    nombre = request.getParameter("txtNombreBol");
                    fecha = request.getParameter("txtFec");

                    request.getRequestDispatcher("C_Boleta?menuBoleta=menu&accionBoleta=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }

            request.getRequestDispatcher("boleta.jsp").forward(request, response);
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
