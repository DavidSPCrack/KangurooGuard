package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonVarios;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;
import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class UpdateEntity extends AbstractAction {

    public class Parameters {
        public static final String ESTRUCTURA_DATOS = "ESTRUCTURA_DATOS";
        public static final String CLASS_DESTINO = "CLASS_DESTINO";
    }

    private EstructuraDatos eDatos;
    private Class<?> dstClass;


    public UpdateEntity(IActividad actividad) {
        super(actividad);
        IActionParameters parameters = getParameters();
        this.eDatos = parameters.getEstructura(Parameters.ESTRUCTURA_DATOS);
        this.dstClass = parameters.getClase(Parameters.CLASS_DESTINO);

    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            AdmonVarios admon = new AdmonVarios();
            admon.updateObject(eDatos);
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (isOk()) {
            UtilActivity util = getUtil();
            util.openNewActivity(dstClass);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.updating_info);
    }

}
