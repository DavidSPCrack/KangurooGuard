package com.sistemasikanguro.kangurooguard.framework.actions;

import android.support.v4.widget.SwipeRefreshLayout;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.RoutesAdapter;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Ruta;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

import java.util.List;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LoadRutas extends AbstractAction {

    private List<Ruta> lista;
    private RoutesAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    public LoadRutas(IActividad actividad, RoutesAdapter rAdapter, SwipeRefreshLayout swipe) {
        super(actividad);
        this.adapter = rAdapter;
        this.swipeRefresh = swipe;
    }

    public LoadRutas(IActividad actividad, RoutesAdapter rAdapter) {
        this(actividad, rAdapter, null);
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            this.lista = Ruta.getRutas();
        } catch (ErrorGeneral eg) {
            setErrorGeneral(eg);
            throw eg;
        }
    }

    @Override
    public void postExecute() {
        if (isOk()) {
            if (lista != null)
                this.adapter.refill(lista);
        }
        if (swipeRefresh != null) {
            if (swipeRefresh.isRefreshing())
                swipeRefresh.setRefreshing(false);
        }
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.loading);
    }

    @Override
    public boolean isShowLoad() {
        return false;
    }
}
