package com.chaosphoenix.kangurooguard.util.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.view.View;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class AsyncTaskStandard extends AsyncTask<IThreadElement, String, ThreadExecutor> {

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

    public static void doTask(UtilActivity util, IThreadElement... params) {
        doTask(util, null, params);
    }

    public static void doTask(UtilActivity util, View progress, IThreadElement... params) {
        doTask(util, progress, true, params);
    }

    public static void doTask(UtilActivity util, View progress, boolean showLoading, IThreadElement... params) {

        if (params == null)
            params = new IThreadElement[0];

        String initialLoadMsg = params.length > 0 ? params[0].getTitle() : util.getResourceString(R.string.please_wait);

        AsyncTaskStandard asyncTask = new AsyncTaskStandard(util, initialLoadMsg, progress, showLoading);
        asyncTask.execute(params);
    }

    @Override
    protected ThreadExecutor doInBackground(IThreadElement... params) {
        ThreadExecutor te = new ThreadExecutor(this, params);
        te.execute();
        return te;
    }

    void publishUpdate(IThreadElement param) {
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
