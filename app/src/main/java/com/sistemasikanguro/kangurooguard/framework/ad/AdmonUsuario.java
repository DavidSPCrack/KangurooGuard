package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
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
        EstructuraDatos eDatos = new EstructuraDatos(Usuario.TABLE_NAME);
        eDatos.add(Usuario.USERNAME, username);
        eDatos.add(Usuario.PASSWORD, password);
        eDatos.add(Usuario.EMAIL, email);

        ADUsuario adatos = new ADUsuario();
        adatos.createUser(eDatos);
        return Usuario.getInstance();
    }

    public Usuario updateUsuario(EstructuraDatos eDatos) throws ErrorGeneral {
        ADUsuario adatos = new ADUsuario();
        adatos.updateUsuario(eDatos);
        return Usuario.getInstance();
    }
}
