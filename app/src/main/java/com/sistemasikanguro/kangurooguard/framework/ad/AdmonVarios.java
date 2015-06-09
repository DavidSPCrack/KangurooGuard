package com.sistemasikanguro.kangurooguard.framework.ad;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.entities.Persona;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;

/**
 * Created by usuario.apellido on 06/06/2015.
 *
 * @author david.sancho
 */
public class AdmonVarios {

    public AdmonVarios() {

    }

    public void crearPersona(String name, String surname, String telephone, String email, String comments) throws ErrorGeneral {
        EstructuraDatos eDatos = new EstructuraDatos(Persona.TABLE_NAME);
        eDatos.add(Persona.NAME, name);
        eDatos.add(Persona.SURNAME, surname);
        eDatos.add(Persona.TELEPHONE, telephone);
        eDatos.add(Persona.EMAIL, email);
        eDatos.add(Persona.COMMENTS, comments);

        ADVarios adatos = new ADVarios();
        adatos.createObject(eDatos.getNombreEstructura(), eDatos);
    }

    public void crearRuta(String name, String comments) throws ErrorGeneral {
        EstructuraDatos eDatos = new EstructuraDatos(Ruta.TABLE_NAME);
        eDatos.add(Ruta.NAME, name);
        eDatos.add(Ruta.COMMENTS, comments);

        ADVarios adatos = new ADVarios();
        adatos.createObject(eDatos.getNombreEstructura(), eDatos);
    }

    public void updateObject(EstructuraDatos eDatos) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        adatos.updateObject(eDatos.getNombreEstructura(), eDatos);
    }
}
