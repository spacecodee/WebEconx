package Controlador.Login;

import Modelo.Login.M_Login;
import Recursos.R_Texto;
import SQL.Login.S_Login;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class C_Login extends HttpServlet {

    M_Login mLogin = new M_Login();
    S_Login sLogin = new S_Login();
    C_CLogin cCLogin = new C_CLogin();
    R_Texto rText = new R_Texto();

    String msg, secretKey = "Hi_ThisIsAKey_Encritp";
    int id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet C_Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet C_Login at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accionLogin");

        if (accion.equalsIgnoreCase("loginUsuario")) {
            String user = request.getParameter("txtUsuario");
            String passw = request.getParameter("txtPass");
            
            String nuevoPass = rText.ecnode(secretKey, passw);
            
            mLogin = sLogin.login(user, nuevoPass);
            if (mLogin.getDni() != null) {
                request.setAttribute("usuario", mLogin);
                request.getRequestDispatcher("C_Principal?menuPrincipal=Principal").forward(request, response);
            } else {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            }

        } else {
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
