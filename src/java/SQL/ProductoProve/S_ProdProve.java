package SQL.ProductoProve;

import Modelo.ProducProve.M_ComboProduc;
import Modelo.ProducProve.M_ComboProv;
import Modelo.ProducProve.M_ProductoProve;
import Modelo.ProducProve.M_RProductoProve;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_ProdProve extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;
    int r;

    M_ProductoProve mProduP = new M_ProductoProve();
    M_ComboProv mComboProve = new M_ComboProv();
    M_ComboProduc mComboProd = new M_ComboProduc();

    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_ProductoProve> lista = new ArrayList<>();
        try {
            SQL = "CALL spMostrarTablaIntermedia()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProduP = new M_ProductoProve();
                mProduP.setId(Res.getInt(1));
                mProduP.setCodigo(Res.getString(2));
                mProduP.setIdProve(Res.getString(3));
                mProduP.setIdProd(Res.getString(4));
                mProduP.setFecha(Res.getString(5));
                lista.add(mProduP);
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

        List<M_ProductoProve> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarTablaIntermedia(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mProduP = new M_ProductoProve();
                mProduP.setId(Res.getInt(1));
                mProduP.setCodigo(Res.getString(2));
                mProduP.setIdProve(Res.getString(3));
                mProduP.setIdProd(Res.getString(4));
                mProduP.setFecha(Res.getString(5));
                lista.add(mProduP);
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

    public ArrayList<M_ComboProv> mComboProvee() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboProv> mComboProv = new ArrayList<>();

        try {
            SQL = "CALL spComboProveedor()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mComboProve = new M_ComboProv();

            mComboProve.setId(0);
            mComboProve.setNombre("Selecciona");
            mComboProv.add(mComboProve);

            while (Res.next()) {
                mComboProve = new M_ComboProv();
                mComboProve.setId((Res.getInt(1)));
                mComboProve.setNombre(Res.getString(2));

                mComboProv.add(mComboProve);
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
        return mComboProv;
    }

    public ArrayList<M_ComboProduc> mComboProd() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboProduc> mComboPro = new ArrayList<>();

        try {
            SQL = "CALL spComboProductos()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mComboProd = new M_ComboProduc();

            mComboProd.setId(0);
            mComboProd.setNombre("Selecciona");
            mComboPro.add(mComboProd);

            while (Res.next()) {
                mComboProd = new M_ComboProduc();
                mComboProd.setId((Res.getInt(1)));
                mComboProd.setNombre(Res.getString(2));

                mComboPro.add(mComboProd);
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
        return mComboPro;
    }

    public void agregar(M_RProductoProve mAProduP) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarTablaInermedia(?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mAProduP.getCodigo());
            callSt.setInt(2, mAProduP.getIdProve());
            callSt.setInt(3, mAProduP.getIdProd());
            callSt.setString(4, mAProduP.getFecha());
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

    public M_RProductoProve listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        M_RProductoProve mAProducto = new M_RProductoProve();

        try {
            SQL = "CALL spListarIdIntermedia(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mAProducto.setId(Res.getInt(1));
                mAProducto.setCodigo(Res.getString(2));
                mAProducto.setIdProve(Res.getInt(3));
                mAProducto.setIdProd(Res.getInt(4));
                mAProducto.setFecha(Res.getString(5));
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

    public boolean actualizar(M_RProductoProve mAProduP) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarTablaIntermedia(?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mAProduP.getCodigo());
            callSt.setInt(2, mAProduP.getIdProve());
            callSt.setInt(3, mAProduP.getIdProd());
            callSt.setString(4, mAProduP.getFecha());
            callSt.setInt(5, mAProduP.getId());
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
            SQL = "CALL spEliminarTablaIntermedia(?)";
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
