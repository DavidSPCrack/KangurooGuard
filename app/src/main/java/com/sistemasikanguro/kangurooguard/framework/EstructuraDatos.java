package com.sistemasikanguro.kangurooguard.framework;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.sistemasikanguro.kangurooguard.util.basic.Fecha;
import com.sistemasikanguro.kangurooguard.util.basic.Hora;
import com.sistemasikanguro.kangurooguard.util.basic.IOrdenacion;
import com.sistemasikanguro.kangurooguard.util.basic.Transform;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author david.sancho
 */
public final class EstructuraDatos implements Cloneable, IOrdenacion {

    private String nombreEstructura;
    private int identacion = 0;
    private HashMap<String, Object> datos;

    public EstructuraDatos(String nombreEstructura) {
        this.nombreEstructura = nombreEstructura;
        datos = new HashMap<String, Object>();
    }

    public void update(ParseObject pObject, String[] fields) {
        for (int i = 0; i < fields.length; i++) {
            add(fields[i], pObject.getString(fields[i]));
        }
        add(BasicEntity.ID, pObject.getObjectId());
        add(BasicEntity.CREATED_AT, pObject.getCreatedAt());
        add(BasicEntity.UPDATED_AT, pObject.getUpdatedAt());
    }

    public void add(String key, Object dato) {
        datos.put(key, dato);
    }

    public void add(String key, String dato) {
        datos.put(key, dato == null ? "" : dato);
    }

    public void add(String key, long dato) {
        add(key, Long.toString(dato));
    }

    public void add(String key, Fecha dato) {
        if (dato == null)
            dato = Fecha.getFechaNull();
        add(key, dato.toChar());
    }

    public void add(String key, Hora dato) {
        if (dato == null)
            dato = Hora.getHoraNull();
        add(key, dato.toChar());
    }

    public void add(String key, int dato) {
        add(key, Integer.toString(dato));
    }

    public void add(String key, double dato) {
        add(key, Double.toString(dato));
    }

    public void add(String key, boolean dato) {
        add(key, dato ? "S" : "N");
    }

    public void add(String key, EstructuraDatos dato) {
        datos.put(key, dato);
    }

    public void remove(String key) {
        datos.remove(key);
    }

    public void vaciar() {
        datos.clear();
    }

    public String getString(String key) {
        Object aux = getObject(key);
        return aux == null ? "" : aux.toString();
    }

    public String getString0ToSpace(String key) {
        String aux = getString(key);
        if (aux.equals("0"))
            return "";
        return aux;
    }

    public long getLong(String key) {
        Object aux = getObject(key);
        try {
            return aux == null ? 0 : Long.parseLong(aux.toString());
        } catch (NumberFormatException nfe) {
            return new Double(convert(aux.toString())).longValue();
        }
    }

    public String getDataForeignKey(String key) {
        long aux = getLong(key);
        if (aux == 0) {
            return "null";
        } else {
            return Long.toString(aux);
        }
    }

    public int getInteger(String key) {
        Object aux = getObject(key);
        try {
            return aux == null ? 0 : Integer.parseInt(aux.toString());
        } catch (NumberFormatException nfe) {
            return new Double(convert(aux.toString())).intValue();
        }
    }

    public double getDouble(String key) {
        Object aux = getObject(key);
        if (aux == null)
            return 0;
        String cadNum = null;
        if (aux instanceof String) {
            cadNum = (String) aux;
            if (cadNum.length() == 0) {
                return 0;
            }
        }
        return Transform.toDouble(aux);
    }

    public boolean getBoolean(String key) {
        Object aux = getObject(key);
        return aux != null && aux.toString().equalsIgnoreCase("S");
    }

    public Fecha getFecha(String key) {
        Object aux = getObject(key);
        return aux == null ? new Fecha(1900, 1, 1) : new Fecha(aux.toString());
    }

    public Hora getHora(String key) {
        Object aux = getObject(key);
        return aux == null ? new Hora(1900, 1, 1, 0, 0, 0) : new Hora(aux.toString());
    }

    public EstructuraDatos getEstructuraDatos(String key) {
        Object aux = getObject(key);
        return (EstructuraDatos) (aux == null ? new EstructuraDatos(key) : aux);
    }

    public Object getObject(String key) {
        Object obj = datos.get(key);
        return obj;
    }

    public String[] getFields() {
        if (datos == null)
            return new String[0];
        Object[] keys = datos.keySet().toArray();
        ArrayList<String> fields = new ArrayList<String>();
        for (Object key : keys) {
            if (key instanceof String) {
                fields.add(key.toString());
            }
        }
        return fields.toArray(new String[fields.size()]);
    }

    @Override
    public Object clone() {
        EstructuraDatos eDatos = new EstructuraDatos(nombreEstructura);
        eDatos.datos = (HashMap<String, Object>) this.datos.clone();
        return eDatos;
    }

    public void setEstructuraDatos(EstructuraDatos eDatos) {
        datos = eDatos.datos;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(nombreEstructura);
        String[] keys = (String[]) datos.keySet().toArray(new String[datos.size()]);
        for (int i = 0; i < keys.length; i++) {
            sb.append('\n');
            Object aux = getObject(keys[i]);
            if (aux instanceof EstructuraDatos) {
                sb.append(((EstructuraDatos) aux).toString());
                sb.append('\n');
            }
            sb.append(keys[i] + " " + aux.toString());
        }
        return sb.toString();
    }

    public void print() {
        identar(0);
        System.out.println(nombreEstructura);
        identar(3);
        String[] keys = (String[]) datos.keySet().toArray(new String[datos.size()]);
        for (int i = 0; i < keys.length; i++) {
            Object aux = getObject(keys[i]);
            if (aux instanceof EstructuraDatos)
                ((EstructuraDatos) aux).print();
            System.out.println(keys[i] + " " + aux.toString());
        }
    }


    private void identar(int aumentar) {
        identacion = identacion + aumentar;
        for (int i = 0; i < identacion; i++) {
            System.out.println(" ");
        }
    }

    private double convert(String dato) {
        return Transform.toDouble(dato);
    }

    public void setOrden(String valor) {
        add("ORDEN", valor);
    }

    public String getKeyOrder(int orden) {
        return getString("ORDEN");
    }

    public void toUpper(String key) {
        add(key, getString(key).toUpperCase());
    }

    public int getSize() {
        return datos.size();
    }

    public boolean esIgual(EstructuraDatos eDatos, String field) {
        return eDatos.getString(field).equals(getString(field));
    }

    public boolean esIgualDouble(EstructuraDatos eDatos, String field) {
        return eDatos.getDouble(field) == getDouble(field);
    }

}