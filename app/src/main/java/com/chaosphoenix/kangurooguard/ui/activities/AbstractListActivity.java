package com.chaosphoenix.kangurooguard.ui.activities;

import android.app.ListActivity;
import android.view.View;

import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.thread.IThreadElement;

/**
 * Created by usuario.apellido on 23/01/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractListActivity extends ListActivity implements IActividad {

    public UtilActivity getUtil() {
        return new UtilActivity(this);
    }

    @Override
    public View getProgressBarView() {
        return null;
    }


    @Override
    public IThreadElement getRefreshClass() {
        return null;
    }

    @Override
    public void refreshList() {
    }

    @Override
    public IActionParameters getParameters() {
        return null;
    }

}
