package com.chaosphoenix.kangurooguard.framework.parameters;

import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.util.basic.Fecha;
import com.chaosphoenix.kangurooguard.util.basic.Hora;

/**
 * Created by David on 13/06/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractActionParameters implements IActionParameters {

    private static final String NAME = "ACTION_PARAMETERS";

    private DataSource eDatos;

    public AbstractActionParameters() {
        eDatos = new DataSource(NAME);
    }

    @Override
    public void add(String key, Object dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, String dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, long dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, Fecha dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, Hora dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, int dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, double dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, boolean dato) {
        eDatos.add(key, dato);
    }

    @Override
    public void add(String key, DataSource dato) {
        eDatos.add(key, dato);
    }

    @Override
    public String getString(String key) {
        return eDatos.getString(key);
    }

    @Override
    public String getString0ToSpace(String key) {
        return eDatos.getString0ToSpace(key);
    }

    @Override
    public long getLong(String key) {
        return eDatos.getLong(key);
    }

    @Override
    public String getDataForeignKey(String key) {
        return eDatos.getDataForeignKey(key);
    }

    @Override
    public int getInteger(String key) {
        return eDatos.getInteger(key);
    }

    @Override
    public double getDouble(String key) {
        return eDatos.getDouble(key);
    }

    @Override
    public boolean getBoolean(String key) {
        return eDatos.getBoolean(key);
    }

    @Override
    public Fecha getFecha(String key) {
        return eDatos.getHora(key);
    }

    @Override
    public Hora getHora(String key) {
        return eDatos.getHora(key);
    }

    @Override
    public Object getObject(String key) {
        return eDatos.getObject(key);
    }

    @Override
    public DataSource getEstructura(String key) {
        return eDatos.getEstructuraDatos(key);
    }

    @Override
    public Class<?> getClase(String key) {
        Object obj = eDatos.getObject(key);
        if (obj == null)
            return null;
        if (obj instanceof Class<?>)
            return (Class<?>) obj;
        return null;
    }
}
