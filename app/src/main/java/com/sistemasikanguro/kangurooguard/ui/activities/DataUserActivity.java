package com.sistemasikanguro.kangurooguard.ui.activities;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.sistemasikanguro.kangurooguard.R;

/**
 * Created by Andres on 18/05/2015.
 */
public class DataUserActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_user);

        ActionBar actionBar = getActionBar();
        actionBar.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_datauser, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                Log.i("ActionBar", "Settings!");
                return true;
            case R.id.menu_save:
                Log.i("ActionBar", "Guardar!");
                return true;
            case R.id.menu_new:
                Log.i("ActionBar", "Nuevo!");
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
