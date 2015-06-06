package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.ui.activities.OptionActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LoginUsuario extends AbstractAction {

    private String username;
    private String password;

    private Usuario user;

    public LoginUsuario(IActividad actividad, String username, String password) {
        super(actividad);
        this.username = username;
        this.password = password;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            user = Usuario.login(username, password);
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (user != null) {
            UtilActivity util = getUtil();
            util.openNewActivity(OptionActivity.class);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.logging_in);
    }

}
