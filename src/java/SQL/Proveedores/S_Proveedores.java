package SQL.Proveedores;

import Modelo.CategoriaProducto.M_ComboCategoria;
import Modelo.Productos.M_AProducto;
import Modelo.Productos.M_Productos;
import Modelo.Proveedores.M_Proveedor;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_Proveedores extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int r;
    
    M_Proveedor mProveedor = new M_Proveedor();
    
    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_Proveedor> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarProveedores()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProveedor = new M_Proveedor();
                mProveedor.setId(Res.getInt(1));
                mProveedor.setDni(Res.getString(2));
                mProveedor.setNombre(Res.getString(3));
                mProveedor.setApellidos(Res.getString(4));
                mProveedor.setCorreoElectronico(Res.getString(5));
                mProveedor.setTelefono(Res.getString(6));
                lista.add(mProveedor);
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

        List<M_Proveedor> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarProveedores(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProveedor = new M_Proveedor();
                mProveedor.setId(Res.getInt(1));
                mProveedor.setDni(Res.getString(2));
                mProveedor.setNombre(Res.getString(3));
                mProveedor.setApellidos(Res.getString(4));
                mProveedor.setCorreoElectronico(Res.getString(5));
                mProveedor.setTelefono(Res.getString(6));
                lista.add(mProveedor);
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
    
    public void agregar(M_Proveedor mProveedor) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarProveedores(?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mProveedor.getDni());
            callSt.setString(2, mProveedor.getNombre());
            callSt.setString(3, mProveedor.getApellidos());
            callSt.setString(4, mProveedor.getCorreoElectronico());
            callSt.setString(5, mProveedor.getTelefono());
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

    public M_Proveedor listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        mProveedor = new M_Proveedor();

        try {
            SQL = "CALL spListarIdProveedor(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProveedor.setId(Res.getInt(1));
                mProveedor.setDni(Res.getString(2));
                mProveedor.setNombre(Res.getString(3));
                mProveedor.setApellidos(Res.getString(4));
                mProveedor.setCorreoElectronico(Res.getString(5));
                mProveedor.setTelefono(Res.getString(6));
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

        return mProveedor;
    }

    public boolean actualizar(M_Proveedor mProveedor) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarProveedor(?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mProveedor.getDni());
            callSt.setString(2, mProveedor.getNombre());
            callSt.setString(3, mProveedor.getApellidos());
            callSt.setString(4, mProveedor.getCorreoElectronico());
            callSt.setString(5, mProveedor.getTelefono());
            callSt.setInt(6, mProveedor.getId());
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
            SQL = "CALL spEliminarProveedor(?)";
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
