package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

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

        UtilActivity util = getUtil();
        util.openNewActivity(LoginActivity.class);
    }
}
