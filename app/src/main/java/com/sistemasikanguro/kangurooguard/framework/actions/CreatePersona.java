package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonVarios;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.ui.activities.SonActivity;
import com.sistemasikanguro.kangurooguard.ui.activities.StudentsActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class CreatePersona extends AbstractAction {

    private String name;
    private String surname;
    private String telefono;
    private String email;
    private String comments;

    public CreatePersona(IActividad actividad, String name, String surname, String telefono, String email, String comments) {
        super(actividad);
        this.name = name;
        this.surname = surname;
        this.telefono = telefono;
        this.email = email;
        this.comments = comments;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            AdmonVarios admon = new AdmonVarios();
            admon.crearPersona(name, surname, telefono, email, comments);
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (isOk()) {
            UtilActivity util = getUtil();
            if (util.isUsuarioPadre())
                util.openNewActivity(SonActivity.class);
            else
                util.openNewActivity(StudentsActivity.class);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.creating_person);
    }

}
