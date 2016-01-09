package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.DataSource;
import com.sistemasikanguro.kangurooguard.framework.ad.AdmonVarios;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

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
