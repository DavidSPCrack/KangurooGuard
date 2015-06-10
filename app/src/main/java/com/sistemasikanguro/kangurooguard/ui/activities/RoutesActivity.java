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
import com.sistemasikanguro.kangurooguard.adapters.RoutesAdapter;
import com.sistemasikanguro.kangurooguard.framework.actions.LoadRutas;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class RoutesActivity extends AbstractAppCompatActivity {

    private RoutesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routes);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }

        final SwipeRefreshLayout swipeRefresh = (SwipeRefreshLayout) findViewById(R.id.swipeRefreshLayout);
        ListView listView = (ListView) findViewById(R.id.list_routers);
        this.adapter = new RoutesAdapter(this);
        listView.setAdapter(this.adapter);

        loadRutas();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRutas(swipeRefresh);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadRutas(SwipeRefreshLayout swipeRefreshLayout) {
        LoadRutas load = swipeRefreshLayout == null ? new LoadRutas(this, adapter) : new LoadRutas(this, adapter, swipeRefreshLayout);
        load.execute();
    }

    private void loadRutas() {
        loadRutas(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_routes, menu);
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
            util.openNewActivity(AddRouteActivity.class, false);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View getProgressBarView() {
        return findViewById(R.id.progressBar);
    }
}