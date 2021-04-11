package SQL.Login;

import Modelo.Login.M_Login;
import SQL.Conexion;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class S_Login extends Conexion {

    private Connection Con = null;
    private CallableStatement callSt = null;
    private ResultSet Res = null;
    String SQL;

    public M_Login login(String usuario, String pass) {
        M_Login mLogin = new M_Login();
        callSt = null;
        Con = getConexion();
        SQL = "CALL spLogin(?)";

        try {
            callSt = Con.prepareCall(SQL);
            callSt.setString(1, usuario);
            Res = callSt.executeQuery();

            if (Res.next()) {

                if (pass.equals(Res.getString(3))) {
                    
                    mLogin.setId(Integer.parseInt(Res.getString(1)));
                    mLogin.setDni(Res.getString(2));
                    mLogin.setNombre(Res.getString(4));
                    mLogin.setApellidos(Res.getString(5));
                    mLogin.setTelefono(Res.getString(6));
                    mLogin.setCorreoElectronico(Res.getString(7));
                    mLogin.setFechaNacimiento(Res.getString(8));
                    mLogin.setRoll(Res.getString(9));
                }
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

        return mLogin;
    }

}
