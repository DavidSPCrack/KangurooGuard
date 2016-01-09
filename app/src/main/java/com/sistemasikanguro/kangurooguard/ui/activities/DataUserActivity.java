package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.DataSource;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.UpdateEntity;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.framework.parameters.IActionParameters;
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
        UpdateEntity update = new UpdateEntity(this);
        update.execute();
    }

    @Override
    public IActionParameters getParameters() {
        return super.getParameters();
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewHomeActivity(OptionActivity.class);
    }

    @Override
    public DataSource getDataSource() {
        final UtilActivity util = getUtil();
        String name = util.getEditTextValue(R.id.etxtName);
        String surname = util.getEditTextValue(R.id.etxtSurname);
        String numedocu = util.getEditTextValue(R.id.etxtdni);
        String telephone = util.getEditTextValue(R.id.etxtTelephone);
        String comment = util.getEditTextValue(R.id.etxtCommentUser);

        DataSource ds = new DataSource(Usuario.TABLE_NAME);
        ds.add(Usuario.NAME, name);
        ds.add(Usuario.SURNAME, surname);
        ds.add(Usuario.NUMEDOCU, numedocu);
        ds.add(Usuario.TELEPHONE, telephone);
        ds.add(Usuario.COMMENTS, comment);
        return ds;
    }
}
