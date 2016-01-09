package com.sistemasikanguro.kangurooguard.framework.entities;

import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.DataSource;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.ad.ADVarios;

import java.util.ArrayList;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public class Ruta extends BasicEntity {

    public static final String TABLE_NAME = "routes";

    public static final String NAME = "name";
    public static final String COMMENTS = "comments";

    public static final String[] FIELDS = {NAME, COMMENTS};

    public static final Ruta INSTANCE_VOID = new Ruta(new DataSource(TABLE_NAME));


    Ruta(DataSource eDatos) {
        super(eDatos);
    }

    public String getName() {
        return getDato(NAME);
    }

    public String getComments() {
        return getDato(COMMENTS);
    }


    public static Ruta getInstanceVoid() {
        return INSTANCE_VOID;
    }

    public static Ruta getInstance(String id) throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        DataSource eDatos = adatos.getInstance(TABLE_NAME, id);
        if (eDatos == null)
            return null;
        Ruta user = new Ruta(eDatos);
        return user;
    }

    public static ArrayList<Ruta> getRutas() throws ErrorGeneral {
        ADVarios adatos = new ADVarios();
        ArrayList<DataSource> eDatos = adatos.getInstances(TABLE_NAME, NAME);
        ArrayList<Ruta> lista = new ArrayList<>();
        for (int i = 0; i < eDatos.size(); i++) {
            lista.add(new Ruta(eDatos.get(i)));
        }
        return lista;
    }

    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }

    @Override
    protected String getStructureName() {
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
