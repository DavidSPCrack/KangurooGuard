package com.chaosphoenix.kangurooguard.framework;

import android.util.Log;

import com.chaosphoenix.kangurooguard.util.basic.Util;

/**
 * @author david.sancho
 */
public class ErrorGeneral extends Exception {

    public static final String TAG = ErrorGeneral.class.getSimpleName();

    public static final String GENERIC_MESSAGE = "MENSASIS";

    private static final long serialVersionUID = -2365458199381237151L;
    private CodigoError codigoError;
    private Throwable exception;
    private int numVariables = 0;
    private String[] variables;
    private StringBuilder addTexto = null;

    private static boolean baseDatosActiva = false;

    private ErrorGeneral(int numVariables) {
        this.numVariables = numVariables;
        variables = new String[numVariables];
    }

    public ErrorGeneral(CodigoError codigoError) {
        this(0);
        this.codigoError = codigoError;
    }

    public ErrorGeneral(String mensaje) {
        this(new CodigoError(GENERIC_MESSAGE), mensaje);
    }

    public ErrorGeneral(CodigoError codigoError, String variable) {
        this(1);
        this.codigoError = codigoError;
        variables[0] = variable == null ? "" : variable;
    }

    public ErrorGeneral(CodigoError codigoError, String var1, String var2) {
        this(2);
        this.codigoError = codigoError;
        variables[0] = var1 == null ? "" : var1;
        variables[1] = var2 == null ? "" : var2;
    }

    public ErrorGeneral(CodigoError codigoError, String var1, String var2, String var3) {
        this(3);
        this.codigoError = codigoError;
        variables[0] = var1 == null ? "" : var1;
        variables[1] = var2 == null ? "" : var2;
        variables[2] = var3 == null ? "" : var3;
    }

    public ErrorGeneral(Throwable e) {
        this(new CodigoError("EXCE0001"), e.getMessage());
        this.exception = e;
        this.setStackTrace(e.getStackTrace());
        if (e.getMessage() == null) {
            setVariable(0, Util.getStringStackTrace(e));
        }
        Log.e(TAG, "(Constructor ErrorGeneral(Exception e)", e);
    }

    public ErrorGeneral(CodigoError codigoError, String... variables) {
        super();
        setCodigoError(codigoError, variables);
    }

    public void setCodigoError(CodigoError codigoError, String... variables) {
        this.codigoError = codigoError;
        this.numVariables = variables.length;
        this.variables = variables;
        for (int i = 0; i < numVariables; i++) {
            if (this.variables[i] == null) {
                this.variables[i] = "";
            }
        }
    }

    public void setVariable(int index, String variable) {
        this.variables[index] = variable == null ? "" : variable;
    }

    public ErrorGeneral(Throwable e, String mensajeAdicional) {
        this(new CodigoError("EXCE0001"), e.getMessage(), mensajeAdicional);
        this.exception = e;
        Log.e(TAG, mensajeAdicional, e);
    }

    public ErrorGeneral(ErrorGeneral e) {
        this.codigoError = e.codigoError;
        this.numVariables = e.numVariables;
        this.variables = e.variables;
        this.exception = e.exception;
        setStackTrace(e.getStackTrace());
    }

    public String getCodigo() {
        return codigoError.getCodigo();
    }

    public String getDescripcion() {
        if (codigoError != null) {
            String descPersonalizada = codigoError.getDescripcionPersonalizada();
            if (descPersonalizada.length() > 0)
                return descPersonalizada;
        }

        if (exception != null)
            return exception.getMessage();
        else {
            StringBuilder sb = new StringBuilder();
            if (codigoError != null) {
                sb.append(codigoError.getCodigo());
                sb.append(" ");
            }
            if (variables != null) {
                if (variables.length > 0) {
                    for (String var : variables) {
                        sb.append(var);
                        sb.append(" ");
                    }
                }
            }
            return sb.toString();
        }

    }

    public void addTextoMensaje(String texto) {
        if (addTexto == null) {
            addTexto = new StringBuilder();
        }
        addTexto.append(texto);
    }

    public String getMessage() {
        if (exception != null)
            return exception.getMessage();
        return getDescripcion();
    }

    private String mensajeVariable(String mensaje) {
        for (int i = 0; i < variables.length; i++) {
            String variable = "&VAR" + (i + 1);
            boolean exists = mensaje.contains(variable);
            if (exists)
                mensaje = mensaje.replace("&VAR" + (i + 1), variables[i]);
            else
                mensaje += "<br>" + variables[i] + " ";
        }
        return mensaje;
    }

    public static void error(String mensaje) throws ErrorGeneral {
        CodigoError codigo = new CodigoError("MENSASIS");
        throw new ErrorGeneral(codigo, mensaje);
    }
}