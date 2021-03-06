package com.chaosphoenix.kangurooguard.framework.parameters;

import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.util.basic.Fecha;
import com.chaosphoenix.kangurooguard.util.basic.Hora;

/**
 * Created by David on 13/06/2015.
 *
 * @author david.sancho
 */
public interface IActionParameters {

    void add(String key, Object dato);

    void add(String key, String dato);

    void add(String key, long dato);

    void add(String key, Fecha dato);

    void add(String key, Hora dato);

    void add(String key, int dato);

    void add(String key, double dato);

    void add(String key, boolean dato);

    void add(String key, DataSource dato);

    Object getObject(String key);

    DataSource getEstructura(String key);

    Class<?> getClase(String key);

    String getString(String key);

    String getString0ToSpace(String key);

    long getLong(String key);

    String getDataForeignKey(String key);

    int getInteger(String key);

    double getDouble(String key);

    boolean getBoolean(String key);

    Fecha getFecha(String key);

    Hora getHora(String key);

}
