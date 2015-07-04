package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.actions.LoginUsuario;
import com.sistemasikanguro.kangurooguard.framework.parameters.DefaultActionParameters;
import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
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

                LoginUsuario login = new LoginUsuario(this);
                login.execute();
            }
        }
    }

    public void openSignUp(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(SignUpActivity.class);
    }

    @Override
    public IActionParameters getParameters() {
        final UtilActivity util = getUtil();
        IActionParameters parameters = new DefaultActionParameters();
        parameters.add(LoginUsuario.Parameters.USERNAME, util.getEditTextValue(R.id.usernameField));
        parameters.add(LoginUsuario.Parameters.PASSWORD, util.getEditTextValue(R.id.passwordField));
        return parameters;
    }
}
