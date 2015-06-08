package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class StudentsActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.settings) {
            Toast toast = Toast.makeText(getApplicationContext(), "boton de configuracion", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.action_search) {
            Toast toast = Toast.makeText(getApplicationContext(), "boton de busqueda", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.add) {
            Toast toast = Toast.makeText(getApplicationContext(), "Añade ruta", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
