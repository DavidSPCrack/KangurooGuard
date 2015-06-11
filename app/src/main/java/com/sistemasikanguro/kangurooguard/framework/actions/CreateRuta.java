package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonVarios;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.ui.activities.RoutesActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class CreateRuta extends AbstractAction {

    private String name;
    private String comments;


    public CreateRuta(IActividad actividad, String name, String comments) {
        super(actividad);
        this.name = name;
        this.comments = comments;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            AdmonVarios admon = new AdmonVarios();
            admon.crearRuta(name, comments);
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (isOk()) {
            UtilActivity util = getUtil();
            util.openNewActivity(RoutesActivity.class);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.creating_route);
    }

}
