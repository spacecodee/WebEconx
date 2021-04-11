<%@page import="SQL.Conexion"%>
<%@page import="net.sf.jasperreports.engine.JasperRunManager"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="java.io.File"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            /*Variable de tipo Connection*/
            Conexion con = new Conexion();
            Connection conexion;

            conexion = con.getConexion();
            /*Ruta de nuestra base de datos*/
            String dni, fecha;
            dni = request.getParameter("txtDni");
            fecha = request.getParameter("txtFec");

            File reportFile = new File(application.getRealPath("/reporte.jasper"));
            Map<String, Object> map = new HashMap<>();
            map.put("dn", dni);
            map.put("fe", fecha);

            byte[] bytes = JasperRunManager.runReportToPdf(reportFile.getPath(), map, conexion);

            response.setContentType("application/pdf");
            response.setContentLength(bytes.length);
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(bytes, 0, bytes.length);
            /*Convertimos y nos dara una respuesta en formato pdf*/

            ouputStream.flush();
            ouputStream.close();
            //Cerramos
        %>
    </body>
</html>
