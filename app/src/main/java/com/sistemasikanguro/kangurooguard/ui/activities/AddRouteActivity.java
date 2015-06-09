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

    public void changeRoute(View view) throws ErrorGeneral {
        Toast toast = Toast.makeText(getApplicationContext(), "Route Changed", Toast.LENGTH_SHORT);
        toast.show();
    }
    public void CancelRoute(View view) throws ErrorGeneral {
        Toast toast = Toast.makeText(getApplicationContext(), "Route Canceled", Toast.LENGTH_SHORT);
        toast.show();
    }
}
