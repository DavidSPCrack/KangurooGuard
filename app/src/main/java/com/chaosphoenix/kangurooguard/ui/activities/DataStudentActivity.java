package com.chaosphoenix.kangurooguard.ui.activities;

import android.os.Bundle;
import android.view.View;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.actions.CreateEntity;
import com.chaosphoenix.kangurooguard.framework.entities.Persona;
import com.chaosphoenix.kangurooguard.framework.parameters.IActionParameters;
import com.chaosphoenix.kangurooguard.util.UtilActivity;

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
        CreateEntity create = new CreateEntity(this);
        create.execute();
    }

    @Override
    public DataSource getDataSource() {
        final UtilActivity util = getUtil();
        String name = util.getEditTextValue(R.id.etxtName);
        String surname = util.getEditTextValue(R.id.etxtSurname);
        String email = util.getEditTextValue(R.id.etxtEmail);
        String telephone = util.getEditTextValue(R.id.etxtTelephone);
        String comment = util.getEditTextValue(R.id.etxtCommentStudents);

        DataSource ds = new DataSource(Persona.TABLE_NAME);
        ds.add(Persona.NAME, name);
        ds.add(Persona.SURNAME, surname);
        ds.add(Persona.EMAIL, email);
        ds.add(Persona.TELEPHONE, telephone);
        ds.add(Persona.COMMENTS, comment);
        return ds;
    }

    @Override
    public Class<?> getDestinationClass() {
        UtilActivity util = getUtil();
        if (util.isUsuarioPadre())
            return SonActivity.class;
        else
            return StudentsActivity.class;
    }

    @Override
    public IActionParameters getParameters() {
        return super.getParameters();
    }

    public void cancelClick(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(getDestinationClass());
    }


}
