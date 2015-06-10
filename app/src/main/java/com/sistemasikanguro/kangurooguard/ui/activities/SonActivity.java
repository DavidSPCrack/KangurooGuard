package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.PersonsAdapter;
import com.sistemasikanguro.kangurooguard.framework.actions.LoadPersonas;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class SonActivity extends AbstractAppCompatActivity {

    private PersonsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }

        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        ListView listView = (ListView) findViewById(R.id.listView);
        this.adapter = new PersonsAdapter(this);
        listView.setAdapter(this.adapter);

        loadPersonas();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPersonas(swipeRefresh);
            }
        });
    }

    private void loadPersonas(SwipeRefreshLayout swipeRefreshLayout) {
        LoadPersonas load = swipeRefreshLayout == null ? new LoadPersonas(this, adapter, "") : new LoadPersonas(this, adapter, swipeRefreshLayout);
        load.execute();
    }

    private void loadPersonas() {
        loadPersonas(null);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_students, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_search) {
            Toast toast = Toast.makeText(getApplicationContext(), "boton de busqueda", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        if (id == R.id.add) {
            UtilActivity util = getUtil();
            util.openNewActivity(DataStudentActivity.class, false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View getProgressBarView() {
        return findViewById(R.id.progressBar);
    }
}
