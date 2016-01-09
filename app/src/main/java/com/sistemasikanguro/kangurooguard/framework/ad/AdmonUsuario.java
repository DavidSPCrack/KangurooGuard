package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.DataSource;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;

/**
 * Created by usuario.apellido on 06/06/2015.
 *
 * @author david.sancho
 */
public class AdmonUsuario {

    public AdmonUsuario() {

    }

    public Usuario crearUsuario(String username, String password, String email) throws ErrorGeneral {
        DataSource eDatos = new DataSource(Usuario.TABLE_NAME);
        eDatos.add(Usuario.USERNAME, username);
        eDatos.add(Usuario.PASSWORD, password);
        eDatos.add(Usuario.EMAIL, email);
        eDatos.add(Usuario.TYPE, Usuario.K.Tipos.MONITOR);

        ADUsuario adatos = new ADUsuario();
        adatos.createUser(eDatos);
        return Usuario.getInstance();
    }

    public Usuario updateUsuario(DataSource eDatos) throws ErrorGeneral {
        ADUsuario adatos = new ADUsuario();
        adatos.updateUsuario(eDatos);
        return Usuario.getInstance();
    }
}
