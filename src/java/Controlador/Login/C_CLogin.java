package Controlador.Login;

import Modelo.Login.M_Login;
import Recursos.R_Texto;

public class C_CLogin {

    R_Texto rText = new R_Texto();

    String msg, secretKey = "Hi_ThisIsAKey_Encritp";

    public boolean ingresar(String dni, String passw) {
        M_Login mLogin = new M_Login();

        String Pass = new String(passw);
        String nuevoPass = rText.ecnode(secretKey, Pass);

        if (mLogin.getDni().equals(dni) || mLogin.getPassword().equals(nuevoPass)) {
            return true;
        } else {
            return false;
        }
    }

}
