package com.chaosphoenix.kangurooguard.framework.actions;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.ad.AdmonUsuario;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.ui.activities.OptionActivity;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class SignUpUsuario extends AbstractAction {

    public class Parameters {
        public static final String USERNAME = "USERNAME";
        public static final String PASSWORD = "PASSWORD";
        public static final String EMAIL = "EMAIL";
    }

    private String username;
    private String password;
    private String email;

    private Usuario user;

    public SignUpUsuario(IActividad actividad) {
        super(actividad);
        IActionParameters parameters = getParameters();
        this.username = parameters.getString(Parameters.USERNAME);
        this.password = parameters.getString(Parameters.PASSWORD);
        this.email = parameters.getString(Parameters.EMAIL);
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
            util.openNewHomeActivity(OptionActivity.class);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.signing_up);
    }

}
