package com.sistemasikanguro.kangurooguard.ui.fragments;

import android.support.v4.app.ListFragment;

import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by usuario.apellido on 07/03/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractListFragment extends ListFragment implements IActividad {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    public UtilActivity getUtil() {
        return new UtilActivity(getActivity());
    }

}
