package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.UpdateUsuario;
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

    public void saveUser(View view) throws ErrorGeneral {
        final UtilActivity util = getUtil();
        String name = util.getEditTextValue(R.id.etxtName);
        String surname = util.getEditTextValue(R.id.etxtSurname);
        String numedocu = util.getEditTextValue(R.id.etxtdni);
        String telephone = util.getEditTextValue(R.id.etxtTelephone);
        String comment = util.getEditTextValue(R.id.etxtCommentUser);
        UpdateUsuario update = new UpdateUsuario(this, name, surname, telephone, numedocu, comment);
        update.execute();
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(OptionActivity.class);
    }

}
