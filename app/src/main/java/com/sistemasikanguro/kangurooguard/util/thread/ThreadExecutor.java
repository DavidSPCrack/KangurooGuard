package com.sistemasikanguro.kangurooguard.util.thread;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class ThreadExecutor {

    private AsyncTaskStandard asyncTask;
    private IThreadElement[] params;
    private ErrorGeneral eg;

    ThreadExecutor(AsyncTaskStandard asyncTask, IThreadElement... params) {
        this.asyncTask = asyncTask;
        this.params = params;
    }

    void execute() {
        try {
            for (IThreadElement param : params) {
                asyncTask.publishUpdate(param);
                param.doInBackground();
            }
        } catch (ErrorGeneral eg) {
            this.eg = eg;
        }
    }

    void postExecute() {
        for (IThreadElement param : params) {
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
