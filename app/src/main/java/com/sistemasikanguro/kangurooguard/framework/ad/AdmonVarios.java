package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;

/**
 * Created by usuario.apellido on 06/06/2015.
 *
 * @author david.sancho
 */
public class AdmonVarios {

    public AdmonVarios() {

    }

    public void crearRuta(String name, String comments) throws ErrorGeneral {
        EstructuraDatos eDatos = new EstructuraDatos(Ruta.TABLE_NAME);
        eDatos.add(Ruta.NAME, name);
        eDatos.add(Ruta.COMMENTS, comments);

        ADVarios adatos = new ADVarios();
        adatos.createRuta(eDatos);
    }

    public void updateRuta(EstructuraDatos eDatos) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        adatos.updateRuta(eDatos);
    }
}
