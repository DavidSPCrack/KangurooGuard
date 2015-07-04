package com.sistemasikanguro.kangurooguard.ui;

import android.view.View;

import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.IThreadElement;

/**
 * Created by usuario.apellido on 06/06/2015.
 *
 * @author david.sancho
 */
public interface IActividad {

    UtilActivity getUtil();

    View getProgressBarView();

    IThreadElement getRefreshClass();

    IActionParameters getParameters();

    void refreshList();
}
