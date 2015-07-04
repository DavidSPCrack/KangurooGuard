package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.internal.widget.AdapterViewCompat;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.PersonsAdapter;
import com.sistemasikanguro.kangurooguard.framework.actions.LoadPersonas;
import com.sistemasikanguro.kangurooguard.framework.entities.Persona;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.IThreadElement;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class StudentsActivity extends AbstractAppCompatActivity {

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
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Persona persona = adapter.get(position);
                if (persona != null) {
                    registerForContextMenu(view);
                }
                return false;
            }
        });

        loadPersonas();
        swipeRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadPersonas(swipeRefresh);
            }
        });
    }

    private void loadPersonas(SwipeRefreshLayout swipeRefreshLayout) {
        LoadPersonas load = getRefreshClass(swipeRefreshLayout);
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
            util.openNewTaskActivity(DataStudentActivity.class);
        }
        return super.onOptionsItemSelected(item);
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
        AdapterViewCompat.AdapterContextMenuInfo info = (AdapterViewCompat.AdapterContextMenuInfo) item.getMenuInfo();
        int pos = info.position;
        Persona persona = this.adapter.get(pos);
        if (persona != null)
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
    public View getProgressBarView() {
        return findViewById(R.id.progressBar);
    }


    public LoadPersonas getRefreshClass(SwipeRefreshLayout swipeRefreshLayout) {
        return swipeRefreshLayout == null ? new LoadPersonas(this, adapter, "") : new LoadPersonas(this, adapter, swipeRefreshLayout, "");
    }

    @Override
    public IThreadElement getRefreshClass() {
        return getRefreshClass(null);
    }

    @Override
    public void refreshList() {
        loadPersonas();
    }
}
