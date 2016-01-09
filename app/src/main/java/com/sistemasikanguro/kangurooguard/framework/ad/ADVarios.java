package com.sistemasikanguro.kangurooguard.framework.ad;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.DataSource;
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


    public DataSource getInstance(String tableName, String id) throws ErrorGeneral {
        try {
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(tableName);
            pQuery.whereContains(BasicEntity.ID, id);
            ParseObject pObject = pQuery.getFirst();
            return getEstructura(pObject, tableName);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public ArrayList<DataSource> getInstances(String tableName, String orderBy) throws ErrorGeneral {
        try {
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(tableName);
            pQuery.orderByAscending(orderBy);
            List<ParseObject> lista = pQuery.find();
            return getListaEstructuras(lista, tableName);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public ArrayList<DataSource> getInstances(String tableName, String fieldName, String fieldVal, String orderBy) throws ErrorGeneral {
        try {
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(tableName);
            pQuery.whereContains(fieldName, fieldVal);
            pQuery.orderByAscending(orderBy);
            List<ParseObject> lista = pQuery.find();
            return getListaEstructuras(lista, tableName);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    private ArrayList<DataSource> getListaEstructuras(List<ParseObject> lista, String tableName) {
        if (lista == null)
            return new ArrayList<DataSource>();
        ArrayList<DataSource> edatos = new ArrayList<DataSource>();
        for (int i = 0; i < lista.size(); i++) {
            edatos.add(getEstructura(lista.get(i), tableName));
        }
        return edatos;
    }

    private DataSource getEstructura(ParseObject pObject, String tableName) {
        if (pObject == null)
            return null;
        DataSource eDatos = new DataSource(tableName);
        eDatos.update(pObject, Ruta.FIELDS);
        return eDatos;
    }

    void createObject(String tableName, DataSource eDatos) throws ErrorGeneral {
        try {
            ParseObject pObject = new ParseObject(tableName);
            String[] fields = eDatos.getFields();
            for (String field : fields) {
                pObject.put(field, eDatos.getString(field));
            }
            pObject.save();
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    void updateObject(String tableName, DataSource eDatos) throws ErrorGeneral {
        try {
            String id = eDatos.getString(BasicEntity.ID);
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(tableName);
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

    void deleteObject(String tableName, DataSource eDatos) throws ErrorGeneral {
        try {
            String id = eDatos.getString(BasicEntity.ID);
            ParseQuery<ParseObject> pQuery = ParseQuery.getQuery(tableName);
            pQuery.whereContains(BasicEntity.ID, id);
            ParseObject pObject = pQuery.getFirst();
            if (pObject != null) {
                pObject.delete();
            }
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }


}
