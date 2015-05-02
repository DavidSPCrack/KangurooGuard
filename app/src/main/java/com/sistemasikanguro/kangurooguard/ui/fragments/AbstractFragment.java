package com.sistemasikanguro.kangurooguard.ui.fragments;

import android.support.v4.app.Fragment;

import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by usuario.apellido on 07/03/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractFragment extends Fragment {

    protected static final String ARG_SECTION_NUMBER = "section_number";

    protected UtilActivity getUtil() {
        return new UtilActivity(getActivity());
    }

}
