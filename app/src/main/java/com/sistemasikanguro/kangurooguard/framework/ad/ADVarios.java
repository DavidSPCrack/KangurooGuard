package com.sistemasikanguro.kangurooguard.framework.ad;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario.apellido on 15/05/2015.
 *
 * @author david.sancho
 */
public final class ADVarios extends AbstractAccesoADatos {

    public ADVarios() {
    }


    public EstructuraDatos getRuta(String id) throws ErrorGeneral {
        try {
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(Ruta.TABLE_NAME);
            pQuery.whereContains(BasicEntity.ID, id);
            ParseObject pObject = pQuery.getFirst();
            return getEstructuraRuta(pObject);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public ArrayList<EstructuraDatos> getRutas() throws ErrorGeneral {
        try {
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(Ruta.TABLE_NAME);
            pQuery.orderByAscending(Ruta.NAME);
            List<ParseObject> lista = pQuery.find();
            return getEstructurasRuta(lista);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    private ArrayList<EstructuraDatos> getEstructurasRuta(List<ParseObject> lista) {
        if (lista == null)
            return new ArrayList<EstructuraDatos>();
        ArrayList<EstructuraDatos> edatos = new ArrayList<EstructuraDatos>();
        for (int i = 0; i < lista.size(); i++) {
            edatos.add(getEstructuraRuta(lista.get(i)));
        }
        return edatos;
    }

    private EstructuraDatos getEstructuraRuta(ParseObject pObject) {
        if (pObject == null)
            return null;
        EstructuraDatos eDatos = new EstructuraDatos(Ruta.TABLE_NAME);
        eDatos.update(pObject, Ruta.FIELDS);
        return eDatos;
    }

    void createRuta(EstructuraDatos eDatos) throws ErrorGeneral {
        try {
            ParseObject pObject = new ParseObject(Ruta.TABLE_NAME);
            String[] fields = eDatos.getFields();
            for (String field : fields) {
                pObject.put(field, eDatos.getString(field));
            }
            pObject.save();
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    void updateRuta(EstructuraDatos eDatos) throws ErrorGeneral {
        try {
            String id = eDatos.getString(BasicEntity.ID);
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(Ruta.TABLE_NAME);
            pQuery.whereContains(BasicEntity.ID, id);
            ParseObject pObject = pQuery.getFirst();
            if (pObject != null) {
                String[] fields = eDatos.getFields();
                for (String field : fields) {
                    if (isUpdatableField(field))
                        pObject.put(field, eDatos.getString(field));
                }
                pObject.save();
            }
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }


}
