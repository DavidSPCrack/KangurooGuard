package com.sistemasikanguro.kangurooguard.framework.entities;

import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.ad.ADVarios;

import java.util.ArrayList;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public class Persona extends BasicEntity {

    public static final String TABLE_NAME = "persons";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String TELEPHONE = "telephone";
    public static final String EMAIL = "email";
    public static final String ROUTES = "routes";
    public static final String COMMENTS = "comments";

    public static final String[] FIELDS = {NAME, SURNAME, TELEPHONE, EMAIL, COMMENTS};


    Persona(EstructuraDatos eDatos) {
        super(eDatos);
    }

    public String getName() {
        return getDato(NAME);
    }

    public String getSurname() {
        return getDato(SURNAME);
    }

    public String getTelephone() {
        return getDato(TELEPHONE);
    }

    public String getEmail() {
        return getDato(EMAIL);
    }

    public String getComments() {
        return getDato(COMMENTS);
    }

    public static Persona getInstance(String id) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        EstructuraDatos eDatos = adatos.getInstance(TABLE_NAME, id);
        if (eDatos == null)
            return null;
        Persona user = new Persona(eDatos);
        return user;
    }

    public static ArrayList<Persona> getPersonas(String idRuta) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        ArrayList<EstructuraDatos> eDatos = adatos.getInstances(TABLE_NAME, NAME);
        ArrayList<Persona> lista = new ArrayList<>();
        for (int i = 0; i < eDatos.size(); i++) {
            lista.add(new Persona(eDatos.get(i)));
        }
        return lista;
    }

    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }

    @Override
    protected String getNombreEstructura() {
        return TABLE_NAME;
    }

    @Override
    public String toString() {
        return getName();
    }

    @Override
    public String getKeyOrder() {
        return getName();
    }
}
