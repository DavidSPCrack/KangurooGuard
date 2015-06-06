package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class DataUserActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        UtilActivity util = getUtil();
        try {
            Usuario user = Usuario.getInstance();
            if (user != null) {
                util.setEditTextValue(R.id.etxtName, user.getName());
                util.setEditTextValue(R.id.etxtSurname, user.getSurname());
                util.setEditTextValue(R.id.etxtdni, user.getNumeroDocumento());
                util.setEditTextValue(R.id.etxtTelephone, user.getTelephone());
                util.setEditTextValue(R.id.etxtEmail, user.getEmail());
                util.setEditTextValue(R.id.etxtCommentUser, user.getComments());
            }
        } catch (ErrorGeneral eg) {
            util.doAlertDialog(eg);
        }
    }
}
