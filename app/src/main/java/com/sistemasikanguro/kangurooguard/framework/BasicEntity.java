package com.sistemasikanguro.kangurooguard.framework;

import com.sistemasikanguro.kangurooguard.util.basic.Fecha;
import com.sistemasikanguro.kangurooguard.util.basic.Hora;
import com.sistemasikanguro.kangurooguard.util.basic.Transform;

import java.util.Date;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public abstract class BasicEntity {

    public static final String ID = "objectId";
    public static final String CREATED_AT = "createdAt";
    public static final String UPDATED_AT = "updatedAt";

    public static final int DEFAULT_MAX = 1000;

    private EstructuraDatos datos;

    protected BasicEntity() {
        update(new EstructuraDatos(getNombreEstructura()));
    }

    protected BasicEntity(EstructuraDatos eDatos) {
        update(eDatos);
    }

    protected void update(EstructuraDatos eDatos) {
        this.datos = eDatos;
    }

    protected void addDato(String nombDato, String valor) {
        this.datos.add(nombDato, valor == null ? "" : valor);
    }

    protected void addDato(String key, double d) {
        String value = Double.toString(d);
        addDato(key, value);
    }

    protected void addDato(String key, long d) {
        String value = Long.toString(d);
        addDato(key, value);
    }

    protected void addDato(String key, boolean d) {
        String value = d ? "S" : "N";
        addDato(key, value);
    }

    protected void addDato(String key, Fecha d) {
        String value = d == null ? "" : d.toChar();
        addDato(key, value);
    }

    protected void addDato(String key, Hora h) {
        String value = h == null ? "" : h.toChar();
        addDato(key, value);
    }

    protected void addDato(String key, Date d) {
        Hora h = new Hora(d);
        addDato(key, h);
    }

    public String getDato(String key) {
        Object aux = null;
        if (key != null)
            aux = this.datos.getString(key);
        return aux == null ? "" : aux.toString();
    }

    public String getDatoDefault(String key, String defaultDato) {
        String dato = getDato(key);
        return dato.trim().equals("") ? defaultDato : dato;
    }

    public double getDatoDouble(String key) {
        String aux = getDato(key);
        return Transform.toDouble(aux);
    }

    public int getDatoInteger(String key) {
        String aux = getDato(key);
        try {
            return Integer.parseInt(aux);
        } catch (NumberFormatException e) {
            if (aux.length() > 0)
                return (int) getDatoDouble(key);
            return 0;
        }
    }

    public long getDatoLong(String key) {
        String aux = getDato(key);
        try {
            return Long.parseLong(aux);
        } catch (NumberFormatException e) {
            if (aux.length() > 0)
                return (long) getDatoDouble(key);
            return 0;
        }
    }

    public boolean getDatoBoolean(String key) {
        String aux = getDato(key);
        return aux.startsWith("S");
    }

    public Fecha getDatoFecha(String key) {
        String aux = getDato(key);
        return new Fecha(aux);
    }

    public Hora getDatoHora(String key) {
        String aux = getDato(key);
        return new Hora(aux);
    }

    public String getId() {
        return getDato(ID);
    }

    public Hora getCreatedAt() {
        return getDatoHora(CREATED_AT);
    }

    public Hora getUpdatedAt() {
        return getDatoHora(UPDATED_AT);
    }

    protected abstract String[] getBasicFields();

    protected abstract String getNombreEstructura();

    public EstructuraDatos getEstructura() {
        return (EstructuraDatos) datos.clone();
    }
}
