package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.LogoutUsuario;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

public final class OptionActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
            actionBar.setIcon(R.mipmap.ic_launcher);
        }

        try {
            Usuario user = Usuario.getInstance();
            if(user == null)
                throw new ErrorGeneral(getString(R.string.logon_exception));

            UtilActivity util = getUtil();
            if(user.isPadre()) {
                util.setButtonText(R.id.optionStudents, getString(R.string.sons_option));
            }
        } catch (ErrorGeneral eg) {
            UtilActivity util = getUtil();
            util.doAlertDialog(eg);

            util.openNewActivity(LoginActivity.class);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_options, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.option_logout) {
            LogoutUsuario logout = new LogoutUsuario(this);
            logout.execute();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openRoutes(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(RoutesActivity.class, false);
    }

    public void openYourData(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(DataUserActivity.class, false);
    }

    public void openPasswordChange(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(PasswordActivity.class, false);
    }

    public void openStudents(View view) {
        UtilActivity util = getUtil();
        util.openNewActivity(StudentsActivity.class, false);
    }

    public void openMessages(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Mensajes en la próxima versión", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void openQr(View view) {
        Toast toast = Toast.makeText(getApplicationContext(), "Lectura QR en la próxima versión", Toast.LENGTH_SHORT);
        toast.show();
    }
}