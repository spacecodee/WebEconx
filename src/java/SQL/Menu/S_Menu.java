package SQL.Menu;

import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class S_Menu extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int total = 0;

    public int totalEmpleados() {
        Con = getConexion();
        callSt = null;
        Res = null;

        try {
            SQL = "CALL spSeleccionarTotalEmpleados()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                total = Res.getInt(1);
            }
            return total;
        } catch (SQLException e) {
            System.err.println("Eror: " + e.toString());
            return 0;
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }

    public int totalProveedores() {
        Con = getConexion();
        callSt = null;
        Res = null;

        try {
            SQL = "CALL spSeleccionarTotalProveedores()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                total = Res.getInt(1);
            }
            return total;
        } catch (SQLException e) {
            System.err.println("Eror: " + e.toString());
            return 0;
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }

    public int totalProductos() {
        Con = getConexion();
        callSt = null;
        Res = null;

        try {
            SQL = "CALL spSeleccionarTotalProductos()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                total = Res.getInt(1);
            }
            return total;
        } catch (SQLException e) {
            System.err.println("Eror: " + e.toString());
            return 0;
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Erro: " + e.toString());
            }
        }
    }

}
