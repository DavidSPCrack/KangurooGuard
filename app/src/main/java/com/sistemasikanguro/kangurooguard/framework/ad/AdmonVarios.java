package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.DataSource;

/**
 * Created by usuario.apellido on 06/06/2015.
 *
 * @author david.sancho
 */
public class AdmonVarios {

    public AdmonVarios() {

    }

    public void crearEntity(DataSource eDatos) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        adatos.createObject(eDatos.getStructureName(), eDatos);
    }

    public void updateObject(DataSource eDatos) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        adatos.updateObject(eDatos.getStructureName(), eDatos);
    }

    public void deleteObject(DataSource eDatos) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        adatos.deleteObject(eDatos.getStructureName(), eDatos);
    }
}
