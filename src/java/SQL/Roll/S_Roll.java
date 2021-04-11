package SQL.Roll;

import Modelo.Roles.M_Roll;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_Roll extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int r;

    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_Roll> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarRoles()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                M_Roll mRoll = new M_Roll();
                mRoll.setId(Res.getInt(1));
                mRoll.setCodigo(Res.getString(2));
                mRoll.setRoll(Res.getString(3));
                lista.add(mRoll);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.toString());
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.toString());
            }
        }

        return lista;
    }

    public List listar(String txtBuscar) {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_Roll> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarRoll(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                M_Roll mRoll = new M_Roll();
                mRoll.setId(Res.getInt(1));
                mRoll.setCodigo(Res.getString(2));
                mRoll.setRoll(Res.getString(3));
                lista.add(mRoll);
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.toString());
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.toString());
            }
        }

        return lista;
    }

    public M_Roll listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        M_Roll mRoll = new M_Roll();

        try {
            SQL = "CALL spListarIDRoll(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mRoll.setId(Res.getInt(1));
                mRoll.setCodigo(Res.getString(2));
                mRoll.setRoll(Res.getString(3));
            }

        } catch (SQLException e) {
            System.err.println("Error: " + e.toString());
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.toString());
            }
        }

        return mRoll;
    }

    public void agregarRoles(M_Roll mRoll) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarRoles(?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mRoll.getCodigo());
            callSt.setString(2, mRoll.getRoll());
            callSt.executeQuery();

        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }

    public boolean actualizarRoles(M_Roll mRoll) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarRoles(?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mRoll.getCodigo());
            callSt.setString(2, mRoll.getRoll());
            callSt.setInt(3, mRoll.getId());
            callSt.executeQuery();
            return true;

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
    }
    
    public int delete(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        try {
            SQL = "CALL spEliminarRoll(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            callSt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error: " + e.toString());
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println("Error: " + e.toString());
            }
        }
        return r;
    }
}
