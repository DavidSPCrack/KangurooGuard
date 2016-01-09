package com.chaosphoenix.kangurooguard.framework.actions;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.ad.AdmonVarios;
import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class CreateEntity extends AbstractAction {

    private DataSource eDatos;
    private Class<?> dstClass;

    public CreateEntity(IActividad actividad) {
        super(actividad);
        IActionParameters parameters = getParameters();
        this.eDatos = parameters.getEstructura(Parameters.ESTRUCTURA_DATOS);
        this.dstClass = parameters.getClase(Parameters.CLASS_DESTINO);
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            AdmonVarios admon = new AdmonVarios();
            admon.crearEntity(eDatos);
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
