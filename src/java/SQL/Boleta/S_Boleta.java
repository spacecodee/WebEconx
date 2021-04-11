package SQL.Boleta;

import Modelo.Boleta.M_Boleta;
import Modelo.Boleta.M_ComboIntermedia;
import Modelo.Boleta.M_ComboUsuario;
import Modelo.Boleta.M_MBoleta;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_Boleta extends Conexion {
    
    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int r;

    M_Boleta mBoleta = new M_Boleta();
    M_MBoleta mMostrarBoleta = new M_MBoleta();
    M_ComboIntermedia mComboInter = new M_ComboIntermedia();
    M_ComboUsuario mComboUsuario = new M_ComboUsuario();

    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_MBoleta> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarBoleta()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mMostrarBoleta = new M_MBoleta();
                mMostrarBoleta.setId(Res.getInt(1));
                mMostrarBoleta.setCodigoTrae(Res.getString(2));
                mMostrarBoleta.setNombreProveedor(Res.getString(3));
                mMostrarBoleta.setNombreProducto(Res.getString(4));
                mMostrarBoleta.setDnUsuario(Res.getString(5));
                mMostrarBoleta.setNombreUsuario(Res.getString(6));
                mMostrarBoleta.setCodigoBoleta(Res.getString(7));
                mMostrarBoleta.setPrecio(Res.getDouble(8));
                mMostrarBoleta.setCantidad(Res.getInt(9));
                mMostrarBoleta.setEmpresa(Res.getString(10));
                lista.add(mMostrarBoleta);
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

        List<M_MBoleta> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarBoleta(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mMostrarBoleta = new M_MBoleta();
                mMostrarBoleta.setId(Res.getInt(1));
                mMostrarBoleta.setCodigoTrae(Res.getString(2));
                mMostrarBoleta.setNombreProveedor(Res.getString(3));
                mMostrarBoleta.setNombreProducto(Res.getString(4));
                mMostrarBoleta.setDnUsuario(Res.getString(5));
                mMostrarBoleta.setNombreUsuario(Res.getString(6));
                mMostrarBoleta.setCodigoBoleta(Res.getString(7));
                mMostrarBoleta.setPrecio(Res.getDouble(8));
                mMostrarBoleta.setCantidad(Res.getInt(9));
                mMostrarBoleta.setEmpresa(Res.getString(10));
                lista.add(mMostrarBoleta);
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

    public ArrayList<M_ComboIntermedia> mComboIntermedia() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboIntermedia> mComboIn = new ArrayList<>();

        try {
            SQL = "CALL spComboTraeProd()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mComboInter = new M_ComboIntermedia();

            mComboInter.setId(0);
            mComboInter.setNombre("Selecciona");
            mComboIn.add(mComboInter);

            while (Res.next()) {
                mComboInter = new M_ComboIntermedia();
                mComboInter.setId((Res.getInt(1)));
                mComboInter.setNombre(Res.getString(2));

                mComboIn.add(mComboInter);
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
        return mComboIn;
    }

    public ArrayList<M_ComboUsuario> mComboUsuario() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboUsuario> mComboUsu = new ArrayList<>();

        try {
            SQL = "CALL spComboComprador()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mComboUsuario = new M_ComboUsuario();

            mComboUsuario.setId(0);
            mComboUsuario.setNombre("Selecciona");
            mComboUsu.add(mComboUsuario);

            while (Res.next()) {
                mComboUsuario = new M_ComboUsuario();
                mComboUsuario.setId((Res.getInt(1)));
                mComboUsuario.setNombre(Res.getString(2));

                mComboUsu.add(mComboUsuario);
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
        return mComboUsu;
    }

    public void agregar(M_Boleta mBoleta) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarBoleta(?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, mBoleta.getIdIntermedia());
            callSt.setInt(2, mBoleta.getIdComprador());
            callSt.setString(3, mBoleta.getCodigo());
            callSt.setDouble(4, mBoleta.getPrecio());
            callSt.setInt(5, mBoleta.getCantidad());
            callSt.setString(6, mBoleta.getEmpresa());
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

    public M_MBoleta listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        mMostrarBoleta = new M_MBoleta();

        try {
            SQL = "CALL listrarIdBoleta(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mMostrarBoleta = new M_MBoleta();
                mMostrarBoleta.setId(Res.getInt(1));
                mMostrarBoleta.setCodigoTrae(Res.getString(2));
                mMostrarBoleta.setNombreProveedor(Res.getString(3));
                mMostrarBoleta.setNombreProducto(Res.getString(4));
                mMostrarBoleta.setDnUsuario(Res.getString(5));
                mMostrarBoleta.setNombreUsuario(Res.getString(6));
                mMostrarBoleta.setCodigoBoleta(Res.getString(7));
                mMostrarBoleta.setPrecio(Res.getDouble(8));
                mMostrarBoleta.setCantidad(Res.getInt(9));
                mMostrarBoleta.setEmpresa(Res.getString(10));
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

        return mMostrarBoleta;
    }
    
    public boolean actualizar(M_Boleta mBoleta) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarBoleta(?, ?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, mBoleta.getIdIntermedia());
            callSt.setInt(2, mBoleta.getIdComprador());
            callSt.setString(3, mBoleta.getCodigo());
            callSt.setDouble(4, mBoleta.getPrecio());
            callSt.setInt(5, mBoleta.getCantidad());
            callSt.setString(6, mBoleta.getEmpresa());
            callSt.setInt(7, mBoleta.getId());
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
            SQL = "CALL spEliminarBoleta(?)";
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
