package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.AsyncTaskStandard;
import com.sistemasikanguro.kangurooguard.util.thread.ITheadElement;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractAction implements ITheadElement {

    private ErrorGeneral eg;
    private IActividad actividad;
    private UtilActivity util;

    protected AbstractAction(IActividad actividad) {
        this.util = actividad.getUtil();
        this.actividad = actividad;
    }

    public IActividad getActividad() {
        return actividad;
    }

    public UtilActivity getUtil() {
        return util;
    }

    @Override
    public void execute() {
        AsyncTaskStandard.doTask(getUtil(), actividad.getProgressBarView(), this);
    }

    protected ErrorGeneral getErrorGeneral() {
        return eg;
    }

    public void setErrorGeneral(ErrorGeneral eg) {
        this.eg = eg;
    }

    public boolean isOk() {
        return eg == null;
    }
}
