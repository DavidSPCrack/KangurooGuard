package com.chaosphoenix.kangurooguard.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.actions.ChangePasswordUsuario;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class PasswordActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

    }

    public void changePassword(View view) throws ErrorGeneral {
        final UtilActivity util = getUtil();
        String passwordOld = util.getEditTextValueAndValidate(R.id.passwordOld);
        if (!passwordOld.isEmpty()) {
            String password1 = util.getEditTextValueAndValidate(R.id.newPassword);
            if (!password1.isEmpty()) {
                String password2 = util.getEditTextValueAndValidate(R.id.newPasswordRepeat);
                if (!password2.isEmpty()) {
                    if (!password1.equals(password2)) {
                        util.doAlertDialog(R.string.new_password_doesnt_match);
                    } else {
                        boolean oldPasswordMatch = false;
                        try {
                            Usuario user = util.getUsuarioActual();
                            Usuario.login(user.getUsername(), passwordOld);
                            oldPasswordMatch = true;
                        } catch (ErrorGeneral eg) {
                            util.doAlertDialog(R.string.oldpassword_doesnt_match);
                        }
                        if (oldPasswordMatch) {
                            ChangePasswordUsuario changePwd = new ChangePasswordUsuario(this, password1);
                            changePwd.execute();
                        }
                    }
                }
            }
        }
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewHomeActivity(OptionActivity.class);
    }
}
