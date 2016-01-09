package com.chaosphoenix.kangurooguard.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.actions.AbstractAction;
import com.chaosphoenix.kangurooguard.framework.parameters.DefaultActionParameters;
import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.thread.IThreadElement;

/**
 * Created by usuario.apellido on 23/01/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractAppCompatActivity extends AppCompatActivity implements IActividad {

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
        IActionParameters parameters = new DefaultActionParameters();
        parameters.add(AbstractAction.Parameters.CLASS_DESTINO, getDestinationClass());
        parameters.add(AbstractAction.Parameters.ESTRUCTURA_DATOS, getDataSource());
        return parameters;
    }

    public Class<?> getDestinationClass() {
        return null;
    }

    public DataSource getDataSource() {
        return null;
    }
}
