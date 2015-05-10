package com.sistemasikanguro.kangurooguard.util.thread;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class ThreadExecutor {

    private ITheadElement[] params;
    private ErrorGeneral eg;

    ThreadExecutor(ITheadElement... params) {
        this.params = params;
    }

    void execute() {
        try {
            for (ITheadElement param : params) {
                param.doInBackground();
            }
        } catch(ErrorGeneral eg) {
            this.eg = eg;
        }
    }

    void postExecute() {
        for (ITheadElement param : params) {
            param.postExecute();
        }
    }

    boolean isOk() {
        return eg == null;
    }

    ErrorGeneral getErrorGeneral() {
        return eg;
    }
}
