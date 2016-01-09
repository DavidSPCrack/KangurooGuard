package com.chaosphoenix.kangurooguard.framework.actions;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.ad.AdmonUsuario;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.ui.activities.OptionActivity;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.UtilResource;

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
            user = Usuario.getInstance();
            if (user != null) {
                DataSource eDatos = user.getEstructura();
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
            util.openNewHomeActivity(OptionActivity.class);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.changing_password);
    }

}
