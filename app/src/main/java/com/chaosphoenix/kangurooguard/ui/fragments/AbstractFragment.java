package com.chaosphoenix.kangurooguard.ui.fragments;

import android.support.v4.app.Fragment;
import android.view.View;

import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.ui.IActividad;
import com.chaosphoenix.kangurooguard.util.UtilActivity;
import com.chaosphoenix.kangurooguard.util.thread.IThreadElement;

/**
 * Created by usuario.apellido on 07/03/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractFragment extends Fragment implements IActividad {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    public UtilActivity getUtil() {
        return new UtilActivity(getActivity());
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
