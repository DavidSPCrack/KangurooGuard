package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.parameters.DefaultActionParameters;
import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.AsyncTaskStandard;
import com.sistemasikanguro.kangurooguard.util.thread.IThreadElement;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractAction implements IThreadElement {
    
    public class Parameters {
        public static final String ESTRUCTURA_DATOS = "ESTRUCTURA_DATOS";
        public static final String CLASS_DESTINO = "CLASS_DESTINO";
    }

    private ErrorGeneral eg;
    private IActividad actividad;
    private IActionParameters parameters;
    private UtilActivity util;

    protected AbstractAction(IActividad actividad) {
        this.util = actividad.getUtil();
        this.actividad = actividad;
        this.parameters = actividad.getParameters();
    }

    public IActionParameters getParameters() {
        if(this.parameters == null)
            this.parameters = new DefaultActionParameters();
        return this.parameters;
    }

    public void setParameters(IActionParameters parameters) {
        this.parameters = parameters;
    }

    public IActividad getActividad() {
        return actividad;
    }

    public UtilActivity getUtil() {
        return util;
    }

    @Override
    public void execute(IThreadElement... elements) {
        ArrayList<IThreadElement> lista = new ArrayList<>();
        lista.add(this);
        IThreadElement refresh = actividad.getRefreshClass();
        if (refresh != null)
            lista.add(refresh);
        Collections.addAll(lista, elements);
        IThreadElement[] elementsFinal = lista.toArray(new IThreadElement[lista.size()]);
        AsyncTaskStandard.doTask(getUtil(), actividad.getProgressBarView(), isShowLoad(), elementsFinal);
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

    public boolean isShowLoad() {
        return true;
    }
}
