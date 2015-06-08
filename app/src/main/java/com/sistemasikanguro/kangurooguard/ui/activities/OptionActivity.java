package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.actions.LogoutUsuario;
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


    }

    public void openQr(View view) {

    }
}