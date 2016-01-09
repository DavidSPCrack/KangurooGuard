package com.chaosphoenix.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.util.UtilActivity;

public final class SplashActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    @Override
    protected void onResume() {
        super.onResume();

        Usuario user = null;
        try {
            user = Usuario.getInstance();
        } catch (ErrorGeneral ignore) {
        }
        if (user == null) {
            UtilActivity util = getUtil();
            util.openNewHomeActivity(LoginActivity.class);
        } else {
            UtilActivity util = getUtil();
            util.openNewHomeActivity(OptionActivity.class);
        }
    }
}
