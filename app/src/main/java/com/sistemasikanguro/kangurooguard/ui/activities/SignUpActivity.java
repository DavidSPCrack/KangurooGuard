package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.actions.LoginUsuario;
import com.sistemasikanguro.kangurooguard.framework.actions.SignUpUsuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;


public final class SignUpActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    public void signUpClick(View view) {
        final UtilActivity util = getUtil();
        String username = util.getEditTextValueAndValidate(R.id.usernameField);
        if (!username.isEmpty()) {
            String password = util.getEditTextValueAndValidate(R.id.passwordField);
            if (!password.isEmpty()) {

                SignUpUsuario signUp = new SignUpUsuario(this, username, password, username);
                signUp.execute();
            }
        }
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(LoginActivity.class);
    }
}


