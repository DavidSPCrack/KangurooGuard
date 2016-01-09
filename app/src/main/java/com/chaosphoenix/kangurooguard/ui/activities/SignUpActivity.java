package com.chaosphoenix.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.actions.SignUpUsuario;
import com.chaosphoenix.kangurooguard.framework.parameters.DefaultActionParameters;
import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.util.UtilActivity;


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

                SignUpUsuario signUp = new SignUpUsuario(this);
                signUp.execute();
            }
        }
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewHomeActivity(LoginActivity.class);
    }

    @Override
    public IActionParameters getParameters() {
        final UtilActivity util = getUtil();
        IActionParameters parameters = new DefaultActionParameters();
        parameters.add(SignUpUsuario.Parameters.USERNAME, util.getEditTextValue(R.id.usernameField));
        parameters.add(SignUpUsuario.Parameters.PASSWORD, util.getEditTextValue(R.id.passwordField));
        parameters.add(SignUpUsuario.Parameters.EMAIL, util.getEditTextValue(R.id.usernameField));
        return parameters;
    }
}


