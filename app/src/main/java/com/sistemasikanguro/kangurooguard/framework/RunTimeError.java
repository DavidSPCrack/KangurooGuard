package com.sistemasikanguro.kangurooguard.framework;

/**
 * @author david.sancho
 */
public class RunTimeError extends RuntimeException {

    private static final long serialVersionUID = -6590627617036841949L;
    private CodigoError codigoError;

    public RunTimeError(CodigoError codigoError) {
        super(codigoError.toString());
        this.codigoError = codigoError;
    }

    public RunTimeError(Exception e) {
        super(e);
    }

    public RunTimeError(String msg) {
        super(msg);
    }

}
