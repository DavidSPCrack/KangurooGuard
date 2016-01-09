package com.chaosphoenix.kangurooguard.framework.actions;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.BasicEntity;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.ad.AdmonVarios;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.util.UtilResource;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class DeleteEntity extends AbstractAction {

    private BasicEntity bEntity;

    public DeleteEntity(IActividad actividad, BasicEntity bEntity) {
        super(actividad);
        this.bEntity = bEntity;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            DataSource eDatos = bEntity.getEstructura();
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
            getActividad().refreshList();
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.deleting);
    }

}
