package com.chaosphoenix.kangurooguard.framework;

/**
 * @author david.sancho
 */
public final class CodigoError {

    private String codigoError;
    private String descripcionPersonalizada = null;

    public CodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public CodigoError(String codigoError, String descripcionPersonalizada) {
        this.codigoError = codigoError;
        this.descripcionPersonalizada = descripcionPersonalizada;
    }

    public String getCodigo() {
        return codigoError;
    }

    public String toString() {
        return codigoError;
    }

    public static ErrorGeneral err(String codigo, String var1) throws ErrorGeneral {
        CodigoError codErr = new CodigoError(codigo);
        throw new ErrorGeneral(codErr, var1);
    }

    public static void err(String codigo) throws ErrorGeneral {
        CodigoError codErr = new CodigoError(codigo);
        throw new ErrorGeneral(codErr);
    }

    public static ErrorGeneral errGeneral(String codigo, String var1, String var2) {
        CodigoError codErr = new CodigoError(codigo);
        return new ErrorGeneral(codErr, var1, var2);
    }

    public static ErrorGeneral errGeneral(String codigo) {
        CodigoError codErr = new CodigoError(codigo);
        return new ErrorGeneral(codErr);
    }

    public String getDescripcionPersonalizada() {
        return descripcionPersonalizada == null ? "" : descripcionPersonalizada;
    }

    public void setDescripcionPersonalizada(String descripcion) {
        this.descripcionPersonalizada = descripcion;
    }

}
