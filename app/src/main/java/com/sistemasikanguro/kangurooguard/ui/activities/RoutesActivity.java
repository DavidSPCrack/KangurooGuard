package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.RoutesAdapter;
import com.sistemasikanguro.kangurooguard.framework.actions.LoadRutas;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.IThreadElement;

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Ruta ruta = adapter.get(position);
                if (ruta != null) {
                    registerForContextMenu(view);
                }
                return false;
            }
        });

        loadRutas();

        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadRutas(swipeRefresh);
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_context_basic, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        Ruta ruta = this.adapter.get(pos);
        if (ruta != null)
            switch (item.getItemId()) {
                case R.id.action_modify:
                    return true;
                case R.id.action_delete:
                    return true;
                default:
                    return super.onContextItemSelected(item);
            }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void loadRutas(SwipeRefreshLayout swipeRefreshLayout) {
        LoadRutas load = getRefreshClass(swipeRefreshLayout);
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
            util.openNewTaskActivity(AddRouteActivity.class);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public View getProgressBarView() {
        return findViewById(R.id.progressBar);
    }

    public LoadRutas getRefreshClass(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout == null ? new LoadRutas(this, adapter) : new LoadRutas(this, adapter, swipeRefreshLayout);
    }

    @Override
    public IThreadElement getRefreshClass() {
        return getRefreshClass(null);
    }

    @Override
    public void refreshList() {
        loadRutas();
    }
}