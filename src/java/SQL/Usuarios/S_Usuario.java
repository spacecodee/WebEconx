package SQL.Usuarios;

import Modelo.Roles.M_ComboRol;
import Modelo.Usuario.M_Usuario;
import Modelo.Usuario.M_UsuarioRegis;
import Recursos.R_Texto;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class S_Usuario extends Conexion {

    //Variables
    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL, secretKey = "Hi_ThisIsAKey_Encritp", nuevoPass;
    R_Texto rText = new R_Texto();

    M_ComboRol mRoll;
    
    int r;
    
    public List listar() {
        Con = getConexion();
        callSt = null;
        Res = null;

        List<M_Usuario> lista = new ArrayList<>();
        try {
            SQL = "CALL spCargarTablaUsuarios()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();

            while (Res.next()) {
                M_Usuario mUsuario = new M_Usuario();
                mUsuario.setId(Res.getInt(1));
                mUsuario.setDni(Res.getString(2));
                mUsuario.setNombre(Res.getString(3));
                mUsuario.setApellidos(Res.getString(4));
                mUsuario.setTelefono(Res.getString(5));
                mUsuario.setRoll(Res.getString(6));
                lista.add(mUsuario);
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

        List<M_Usuario> lista = new ArrayList<>();
        try {
            SQL = "CALL spBuscarUsuario(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, txtBuscar);
            Res = callSt.executeQuery();

            while (Res.next()) {
                M_Usuario mUsuario = new M_Usuario();
                mUsuario.setId(Res.getInt(1));
                mUsuario.setDni(Res.getString(2));
                mUsuario.setNombre(Res.getString(3));
                mUsuario.setApellidos(Res.getString(4));
                mUsuario.setTelefono(Res.getString(5));
                mUsuario.setRoll(Res.getString(6));
                lista.add(mUsuario);
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

    public ArrayList<M_ComboRol> mComboRoll() {
        Con = getConexion();
        callSt = null;
        Res = null;

        ArrayList<M_ComboRol> mCombo = new ArrayList<>();

        try {
            SQL = "CALL spSeleccionarRoll()";
            callSt = Con.prepareCall(SQL);
            Res = callSt.executeQuery();
            mRoll = new M_ComboRol();

            mRoll.setId(0);
            mRoll.setRoll("Selecciona");
            mCombo.add(mRoll);

            while (Res.next()) {
                mRoll = new M_ComboRol();
                mRoll.setId((Res.getInt(1)));
                mRoll.setRoll(Res.getString(2));

                mCombo.add(mRoll);
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
        return mCombo;
    }

    public void agregarUsuarios(M_UsuarioRegis mUsuario) {
        Con = getConexion();
        callSt = null;
        Res = null;
        SQL = "CALL spInsertarUsuarios(?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mUsuario.getDni());
            callSt.setString(2, mUsuario.getPassword());
            callSt.setString(3, mUsuario.getNombre());
            callSt.setString(4, mUsuario.getApellidos());
            callSt.setString(5, mUsuario.getTelefono());
            callSt.setString(6, mUsuario.getCorreoElectronico());
            callSt.setString(7, mUsuario.getFechaNacimiento());
            callSt.setInt(8, mUsuario.getRoll());
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

    public M_UsuarioRegis listaId(int id) {
        Con = getConexion();
        callSt = null;
        Res = null;

        M_UsuarioRegis mUsuario = new M_UsuarioRegis();

        try {
            SQL = "CALL spListarIDUsuario(?)";
            callSt = Con.prepareCall(SQL);
            callSt.setInt(1, id);
            Res = callSt.executeQuery();

            while (Res.next()) {
                mUsuario.setId(Integer.parseInt(Res.getString(1)));
                mUsuario.setDni(Res.getString(2));

                nuevoPass = rText.deecnode(secretKey, Res.getString(3));
                mUsuario.setPassword(nuevoPass);

                mUsuario.setNombre(Res.getString(4));
                mUsuario.setApellidos(Res.getString(5));
                mUsuario.setTelefono(Res.getString(6));
                mUsuario.setCorreoElectronico(Res.getString(7));
                mUsuario.setFechaNacimiento(Res.getString(8));
                mUsuario.setRoll(Integer.parseInt(Res.getString(9)));
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

        return mUsuario;
    }

    public boolean actualizarUsuarios(M_UsuarioRegis mUsuario) {
        callSt = null;
        Con = getConexion();
        SQL = "CALL spActualizarUsuarios(?, ?, ?, ?, ?, ?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, mUsuario.getDni());
            callSt.setString(2, mUsuario.getNombre());
            callSt.setString(3, mUsuario.getApellidos());
            callSt.setString(4, mUsuario.getTelefono());
            callSt.setInt(5, mUsuario.getRoll());
            callSt.setInt(6, mUsuario.getId());
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
            SQL = "CALL spEliminarUsuarios(?)";
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
