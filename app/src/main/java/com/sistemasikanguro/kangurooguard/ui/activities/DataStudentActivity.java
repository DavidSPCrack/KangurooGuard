package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.CreatePersona;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class DataStudentActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_student);
    }

    public void savePerson(View view) throws ErrorGeneral {
        final UtilActivity util = getUtil();
        String name = util.getEditTextValue(R.id.etxtName);
        String surname = util.getEditTextValue(R.id.etxtSurname);
        String email = util.getEditTextValue(R.id.etxtEmail);
        String telephone = util.getEditTextValue(R.id.etxtTelephone);
        String comment = util.getEditTextValue(R.id.etxtCommentStudents);
        CreatePersona create = new CreatePersona(this, name, surname, telephone, email, comment);
        create.execute();
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        if (util.isUsuarioPadre())
            util.openNewActivity(SonActivity.class);
        else
            util.openNewActivity(StudentsActivity.class);
    }


}
