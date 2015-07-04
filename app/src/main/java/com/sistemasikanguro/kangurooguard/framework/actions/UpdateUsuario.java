package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonUsuario;
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
public class UpdateUsuario extends AbstractAction {

    private String name;
    private String surname;
    private String telefono;
    private String dni;
    private String comments;

    private Usuario user;

    public UpdateUsuario(IActividad actividad, String name, String surname, String telefono, String dni, String comments) {
        super(actividad);
        this.name = name;
        this.surname = surname;
        this.telefono = telefono;
        this.dni = dni;
        this.comments = comments;
    }

    public Usuario getUser() {
        return user;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            user = Usuario.getInstance();
            if (user != null) {
                EstructuraDatos eDatos = user.getEstructura();
                eDatos.add(Usuario.NAME, name);
                eDatos.add(Usuario.SURNAME, surname);
                eDatos.add(Usuario.TELEPHONE, telefono);
                eDatos.add(Usuario.NUMEDOCU, dni);
                eDatos.add(Usuario.COMMENTS, comments);
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
        return UtilResource.getString(R.string.updating_info);
    }

}
