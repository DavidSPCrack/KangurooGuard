package com.sistemasikanguro.kangurooguard.ui.activities;

import android.app.ListActivity;

import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by usuario.apellido on 23/01/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractListActivity extends ListActivity implements IActividad {

    public UtilActivity getUtil() {
        return new UtilActivity(this);
    }

}
