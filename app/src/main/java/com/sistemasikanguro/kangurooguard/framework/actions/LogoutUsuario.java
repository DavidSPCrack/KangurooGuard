package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.ui.activities.LoginActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

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
        util.openNewActivity(LoginActivity.class);
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.logging_out);
    }

}
