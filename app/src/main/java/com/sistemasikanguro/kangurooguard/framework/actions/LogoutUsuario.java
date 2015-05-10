package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LogoutUsuario extends AbstractAction {

    public LogoutUsuario(UtilActivity util) {
        super(util);
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        Usuario.logout();
    }

    @Override
    public void postExecute() {

    }

}
