package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.ui.activities.AbstractAppCompatActivity;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;


public class SignUpActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    public void signUpClick(View view) {
    }

    public void cancelClick(View view) {
    }
}

