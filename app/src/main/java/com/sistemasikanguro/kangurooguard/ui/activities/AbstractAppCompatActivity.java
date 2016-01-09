package com.sistemasikanguro.kangurooguard.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.sistemasikanguro.kangurooguard.framework.DataSource;
import com.sistemasikanguro.kangurooguard.framework.actions.AbstractAction;
import com.sistemasikanguro.kangurooguard.framework.parameters.DefaultActionParameters;
import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.IThreadElement;

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
