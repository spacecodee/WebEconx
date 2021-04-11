package SQL.Productos;

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

public class S_Productos extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;

    M_Productos mProductos = new M_Productos();
    M_ComboCategoria mCategoria = new M_ComboCategoria();
    int r;
    String SQL;

    //spMostrarCategoriaP
    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_Productos> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarProductos()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProductos = new M_Productos();
                mProductos.setId(Res.getInt(1));
                mProductos.setCodigo(Res.getString(2));
                mProductos.setNombre(Res.getString(3));
                mProductos.setPrecio(Res.getDouble(4));
                mProductos.setStock(Res.getInt(5));
                mProductos.setCategoria(Res.getString(6));
                lista.add(mProductos);
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

        List<M_Productos> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarProductos(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProductos = new M_Productos();
                mProductos.setId(Res.getInt(1));
                mProductos.setCodigo(Res.getString(2));
                mProductos.setNombre(Res.getString(3));
                mProductos.setPrecio(Res.getDouble(4));
                mProductos.setStock(Res.getInt(5));
                mProductos.setCategoria(Res.getString(6));
                lista.add(mProductos);
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

    public ArrayList<M_ComboCategoria> mComboCategoria() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboCategoria> mComboCate = new ArrayList<>();

        try {
            SQL = "CALL spMostrarCategoriaP()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mCategoria = new M_ComboCategoria();

            mCategoria.setId(0);
            mCategoria.setNombre("Selecciona");
            mComboCate.add(mCategoria);

            while (Res.next()) {
                mCategoria = new M_ComboCategoria();
                mCategoria.setId((Res.getInt(1)));
                mCategoria.setNombre(Res.getString(2));

                mComboCate.add(mCategoria);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        } finally {
            try {
                Con.close();
            } catch (SQLException e) {
                System.err.println(e);
            }
        }
        return mComboCate;
    }

    public void agregarProductos(M_AProducto mAProducto) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarProductos(?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mAProducto.getCodigo());
            callSt.setString(2, mAProducto.getNombre());
            callSt.setDouble(3, mAProducto.getPrecio());
            callSt.setInt(4, mAProducto.getStock());
            callSt.setInt(5, mAProducto.getCategoria());
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

    public M_AProducto listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        M_AProducto mAProducto = new M_AProducto();

        try {
            SQL = "CALL spListarIdProducto(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mAProducto.setId(Res.getInt(1));
                mAProducto.setCodigo(Res.getString(2));
                mAProducto.setNombre(Res.getString(3));
                mAProducto.setPrecio(Res.getDouble(4));
                mAProducto.setStock(Res.getInt(5));
                mAProducto.setCategoria(Res.getInt(6));
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

        return mAProducto;
    }

    public boolean actualizarProductos(M_AProducto mAProducto) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarProducto(?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mAProducto.getCodigo());
            callSt.setString(2, mAProducto.getNombre());
            callSt.setDouble(3, mAProducto.getPrecio());
            callSt.setInt(4, mAProducto.getStock());
            callSt.setInt(5, mAProducto.getCategoria());
            callSt.setInt(6, mAProducto.getId());
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
            SQL = "CALL spEliminarProducto(?)";
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
