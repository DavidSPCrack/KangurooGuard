package com.sistemasikanguro.kangurooguard.util;

import android.content.res.Resources;

import com.sistemasikanguro.kangurooguard.KanApplication;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public class UtilResource {

    public static Resources getResources() {
        return KanApplication.getInstance().getResources();
    }

    public static String getString(int id) {
        return getResources().getString(id);
    }

    public static String[] getStringArray(int id) {
        return getResources().getStringArray(id);
    }
}
