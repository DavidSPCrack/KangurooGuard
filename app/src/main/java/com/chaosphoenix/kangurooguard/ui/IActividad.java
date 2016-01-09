package com.chaosphoenix.kangurooguard.ui;

import android.view.View;

import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.thread.IThreadElement;

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
