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
public class UpdateUsuario extends AbstractAction {

    private String name;
    private String surname;
    private String telefono;
    private String email;
    private String dni;
    private String comments;

    private Usuario user;

    public UpdateUsuario(IActividad actividad, String name, String surname, String telefono, String email, String dni, String comments) {
        super(actividad);
        this.name = name;
        this.surname = surname;
        this.telefono = telefono;
        this.email = email;
        this.dni = dni;
        this.comments = comments;
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
