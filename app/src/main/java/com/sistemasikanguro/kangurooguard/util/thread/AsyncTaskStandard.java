package com.sistemasikanguro.kangurooguard.util.thread;

import android.app.ProgressDialog;
import android.os.AsyncTask;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class AsyncTaskStandard extends AsyncTask<ITheadElement, Integer, ThreadExecutor> {

    private UtilActivity util;
    private ProgressDialog pd;

    private AsyncTaskStandard(UtilActivity util) {
        super();
        this.util = util;
    }

    public static void doTask(UtilActivity util, ITheadElement... params) {

        if (params == null)
            params = new ITheadElement[0];

        AsyncTaskStandard asyncTask = new AsyncTaskStandard(util);
        asyncTask.execute(params);
    }

    @Override
    protected ThreadExecutor doInBackground(ITheadElement... params) {
        ThreadExecutor te = new ThreadExecutor(params);
        te.execute();
        return te;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPreExecute() {
        this.pd = util.getProgressDialog(R.string.please_wait);
    }

    @Override
    protected void onPostExecute(ThreadExecutor te) {
        if (te.isOk()) {
            te.postExecute();
            this.pd.dismiss();
        } else {
            this.pd.dismiss();
            util.doAlertDialog(te.getErrorGeneral());
        }
    }
}
