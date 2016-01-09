package com.chaosphoenix.kangurooguard.framework.actions;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.ui.activities.LoginActivity;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LogoutUsuario extends AbstractAction {

    public LogoutUsuario(IActividad actividad) {
        super(actividad);
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        Usuario.logout();
    }

    @Override
    public void postExecute() {
        UtilActivity util = getUtil();
        util.openNewHomeActivity(LoginActivity.class);
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.logging_out);
    }

}
