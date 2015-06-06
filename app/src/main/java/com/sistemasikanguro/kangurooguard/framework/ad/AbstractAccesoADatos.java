package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.BasicEntity;

/**
 * Created by usuario.apellido on 15/05/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractAccesoADatos {

    protected AbstractAccesoADatos() {
    }

    public boolean isUpdatableField(String field) {
        switch (field) {
            case BasicEntity.CREATED_AT:
                return false;
            case BasicEntity.UPDATED_AT:
                return false;
            case BasicEntity.ID:
                return false;
        }
        return true;
    }


}
