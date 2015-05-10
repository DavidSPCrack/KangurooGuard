package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LoginUsuario extends AbstractAction {

    private String username;
    private String password;

    private Usuario user;

    public LoginUsuario(UtilActivity util, String username, String password) {
        super(util);
        this.username = username;
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        user = Usuario.login(username, password);
    }

    @Override
    public void postExecute() {

    }

}
