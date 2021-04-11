package SQL.CategoriaPro;

import Modelo.CategoriaProducto.M_CategoriaP;
import Modelo.CategoriaProducto.M_ComboCategoria;
import Modelo.Productos.M_AProducto;
import Modelo.Productos.M_Productos;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_CategoriaP extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int r;

    M_CategoriaP mCategoriaPro = new M_CategoriaP();

    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_CategoriaP> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarTablaCategoria()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mCategoriaPro = new M_CategoriaP();
                mCategoriaPro.setId(Res.getInt(1));
                mCategoriaPro.setCodigo(Res.getString(2));
                mCategoriaPro.setCategoria(Res.getString(3));
                lista.add(mCategoriaPro);
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

        List<M_CategoriaP> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarCategoriaProductos(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mCategoriaPro = new M_CategoriaP();
                mCategoriaPro.setId(Res.getInt(1));
                mCategoriaPro.setCodigo(Res.getString(2));
                mCategoriaPro.setCategoria(Res.getString(3));
                lista.add(mCategoriaPro);
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

    public void agregarCategoriasP(M_CategoriaP mCategoriaPro) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarCategoriaP(?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mCategoriaPro.getCodigo());
            callSt.setString(2, mCategoriaPro.getCategoria());
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

    public M_CategoriaP listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        mCategoriaPro = new M_CategoriaP();
        
        try {
            SQL = "CALL spListarIdCategoriaP(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mCategoriaPro.setId(Res.getInt(1));
                mCategoriaPro.setCodigo(Res.getString(2));
                mCategoriaPro.setCategoria(Res.getString(3));
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

        return mCategoriaPro;
    }

    public boolean actualizarCategoriaP(M_CategoriaP mCategoriaPro) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarCategorias(?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mCategoriaPro.getCodigo());
            callSt.setString(2, mCategoriaPro.getCategoria());
            callSt.setInt(3, mCategoriaPro.getId());
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
            SQL = "CALL spEliminarCategoriaProd(?)";
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
