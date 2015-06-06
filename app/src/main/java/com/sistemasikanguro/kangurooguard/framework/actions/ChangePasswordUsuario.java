package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
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
public class ChangePasswordUsuario extends AbstractAction {

    private String newPassword;

    private Usuario user;

    public ChangePasswordUsuario(IActividad actividad, String newPassword) {
        super(actividad);
        this.newPassword = newPassword;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            Usuario user = Usuario.getInstance();
            if (user != null) {
                EstructuraDatos eDatos = user.getEstructura();
                eDatos.add(Usuario.PASSWORD, newPassword);
                AdmonUsuario admon = new AdmonUsuario();
                user = admon.updateUsuario(eDatos);
            }
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (isOk()) {
            UtilActivity util = getUtil();
            util.openNewActivity(OptionActivity.class);
        }
    }

}
