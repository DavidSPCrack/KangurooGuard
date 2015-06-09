package com.sistemasikanguro.kangurooguard.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.ChangePasswordUsuario;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class AddRouteActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroute);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_route, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.modify) {
            Toast toast = Toast.makeText(getApplicationContext(), "Modificar", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.add) {
            Toast toast = Toast.makeText(getApplicationContext(), "Añadir", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.delete) {
            Toast toast = Toast.makeText(getApplicationContext(), "Eliminar", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void changeRoute(View view) throws ErrorGeneral {
        Toast toast = Toast.makeText(getApplicationContext(), "Route Changed", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void CancelRoute(View view) throws ErrorGeneral {
        Toast toast = Toast.makeText(getApplicationContext(), "Route Canceled", Toast.LENGTH_SHORT);
        toast.show();
    }
}
