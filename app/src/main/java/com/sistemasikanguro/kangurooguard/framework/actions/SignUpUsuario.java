package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonUsuario;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.ui.activities.OptionActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class SignUpUsuario extends AbstractAction {

    private String username;
    private String password;
    private String email;

    private Usuario user;

    public SignUpUsuario(IActividad actividad, String username, String password, String email) {
        super(actividad);
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            AdmonUsuario admon = new AdmonUsuario();
            user = admon.crearUsuario(this.username, this.password, this.email);
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

}
