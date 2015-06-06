package com.sistemasikanguro.kangurooguard.ui.activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.actions.LoginUsuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

public final class LoginActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }

    public void loginClick(View view) {
        final UtilActivity util = getUtil();
        String username = util.getEditTextValueAndValidate(R.id.usernameField);
        if (!username.isEmpty()) {
            String password = util.getEditTextValueAndValidate(R.id.passwordField);
            if (!password.isEmpty()) {

                LoginUsuario login = new LoginUsuario(this, username, password);
                login.execute();
            }
        }
    }

    public void openSignUp(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(SignUpActivity.class, false);
    }


}
