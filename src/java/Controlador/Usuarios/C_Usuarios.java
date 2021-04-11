package Controlador.Usuarios;

import Modelo.Roles.M_ComboRol;
import Modelo.Usuario.M_Usuario;
import Modelo.Usuario.M_UsuarioRegis;
import Recursos.R_Texto;
import SQL.Usuarios.S_Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

public class C_Usuarios extends HttpServlet {

    S_Usuario sUsuario = new S_Usuario();
    M_UsuarioRegis mUsuAcc = new M_UsuarioRegis();
    M_Usuario mUsuario = new M_Usuario();
    R_Texto rText = new R_Texto();

    String msg, secretKey = "Hi_ThisIsAKey_Encritp";
    int idRoll = 1, idUsuario, ide;

    String dni, pass, nuevoPass, nombre, apellidos, telefono, mail, fecha;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String menu = request.getParameter("menuUsuario");
        String accionUs = request.getParameter("accionUsuario");

        if (menu.equalsIgnoreCase("menuU")) {
            switch (accionUs) {
                case "Listar":
                    List lista = sUsuario.listar();
                    ArrayList<M_ComboRol> mCombo = sUsuario.mComboRoll();
                    request.setAttribute("usuario", lista);
                    request.setAttribute("combo", mCombo);
                    break;

                case "Agregar":
                    dni = request.getParameter("txtDni");
                    pass = request.getParameter("txtPass");
                    nuevoPass = rText.ecnode(secretKey, pass);
                    nombre = request.getParameter("txtNom");
                    apellidos = request.getParameter("txtApe");
                    telefono = request.getParameter("txtTele");
                    mail = request.getParameter("txtMail");
                    fecha = request.getParameter("txtNacimiento");
                    idRoll = Integer.parseInt(request.getParameter("quantity"));

                    mUsuAcc.setDni(dni.trim());
                    mUsuAcc.setPassword(nuevoPass.trim());
                    mUsuAcc.setNombre(nombre.trim());
                    mUsuAcc.setApellidos(apellidos.trim());
                    mUsuAcc.setTelefono(telefono.trim());
                    mUsuAcc.setCorreoElectronico(mail.trim());
                    mUsuAcc.setFechaNacimiento(fecha.trim());
                    mUsuAcc.setRoll(idRoll);
                    //txtNacimiento

                    sUsuario.agregarUsuarios(mUsuAcc);
                    request.getRequestDispatcher("C_Usuarios?menuUsuario=menuU&accionUsuario=Listar").forward(request, response);
                    break;

                case "Editar":
                    idUsuario = Integer.parseInt(request.getParameter("idUsua"));
                    
                    mUsuAcc = sUsuario.listaId(idUsuario);
                    request.setAttribute("usua", mUsuAcc);
                    request.getRequestDispatcher("C_Usuarios?menuUsuario=menuU&accionUsuario=Listar").forward(request, response);
                    break;

                case "Actualizar":
                    dni = request.getParameter("txtDni");
                    pass = request.getParameter("txtPass");
                    nombre = request.getParameter("txtNom");
                    apellidos = request.getParameter("txtApe");
                    telefono = request.getParameter("txtTele");
                    idRoll = Integer.parseInt(request.getParameter("roll"));
                    
                    mUsuAcc.setDni(dni.trim());
                    mUsuAcc.setNombre(nombre.trim());
                    mUsuAcc.setApellidos(apellidos.trim());
                    mUsuAcc.setTelefono(telefono.trim());
                    mUsuAcc.setRoll(idRoll);
                    mUsuAcc.setId(idUsuario);
                    sUsuario.actualizarUsuarios(mUsuAcc);
                    request.getRequestDispatcher("C_Usuarios?menuUsuario=menuU&accionUsuario=Listar").forward(request, response);
                    break;

                case "Eliminar":
                    idUsuario = Integer.parseInt(request.getParameter("idUsua"));

                    sUsuario.delete(idUsuario);
                    request.getRequestDispatcher("C_Usuarios?menuUsuario=menuU&accionUsuario=Listar").forward(request, response);
                    break;

                case "Buscar":
                    String buscar = request.getParameter("txtBuscar");
                    List list = sUsuario.listar(buscar.trim());
                    ArrayList<M_ComboRol> mCombob = sUsuario.mComboRoll();
                    request.setAttribute("usuario", list);
                    request.setAttribute("combo", mCombob);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
