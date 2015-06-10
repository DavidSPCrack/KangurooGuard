package com.sistemasikanguro.kangurooguard.util.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class AsyncTaskStandard extends AsyncTask<ITheadElement, String, ThreadExecutor> {

    private UtilActivity util;
    private View progress;
    private ProgressDialog pd;
    private String initialLoadMsg;
    private boolean showLoading;

    private AsyncTaskStandard(UtilActivity util, String initialLoadMsg, View progress, boolean showLoading) {
        super();
        this.progress = progress;
        this.util = util;
        this.initialLoadMsg = initialLoadMsg;
        this.showLoading = showLoading;
    }

    public static void doTask(UtilActivity util, ITheadElement... params) {
        doTask(util, null, params);
    }

    public static void doTask(UtilActivity util, View progress, ITheadElement... params) {
        doTask(util, progress, true, params);
    }

    public static void doTask(UtilActivity util, View progress, boolean showLoading, ITheadElement... params) {

        if (params == null)
            params = new ITheadElement[0];

        String initialLoadMsg = params.length > 0 ? params[0].getTitle() : util.getResourceString(R.string.please_wait);

        AsyncTaskStandard asyncTask = new AsyncTaskStandard(util, initialLoadMsg, progress, showLoading);
        asyncTask.execute(params);
    }

    @Override
    protected ThreadExecutor doInBackground(ITheadElement... params) {
        ThreadExecutor te = new ThreadExecutor(this, params);
        te.execute();
        return te;
    }

    void publishUpdate(ITheadElement param) {
        publishProgress(param.getTitle());
    }

    @Override
    protected void onProgressUpdate(String... values) {
        if (this.pd != null && values != null && values.length > 0) {
            String value = values[0];
            this.pd.setMessage(value);
        }
    }

    @Override
    protected void onPreExecute() {
        if (progress == null && showLoading)
            this.pd = util.getProgressDialog(R.string.please_wait, initialLoadMsg);
    }

    @Override
    protected void onPostExecute(ThreadExecutor te) {
        if (te.isOk()) {
            te.postExecute();
            dismissProgress();
        } else {
            dismissProgress();
            util.doAlertDialog(te.getErrorGeneral());
        }
    }

    protected void dismissProgress() {
        if (this.pd != null)
            this.pd.dismiss();
        if (this.progress != null)
            util.hideView(this.progress);
    }


}
