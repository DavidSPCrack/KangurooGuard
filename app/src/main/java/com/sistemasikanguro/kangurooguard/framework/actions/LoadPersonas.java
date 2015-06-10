package com.sistemasikanguro.kangurooguard.framework.actions;

import android.support.v4.widget.SwipeRefreshLayout;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.PersonsAdapter;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Persona;
import com.sistemasikanguro.kangurooguard.ui.IActividad;
import com.sistemasikanguro.kangurooguard.util.UtilResource;

import java.util.List;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public class LoadPersonas extends AbstractAction {

    private List<Persona> lista;
    private String idRuta;
    private PersonsAdapter adapter;
    private SwipeRefreshLayout swipeRefresh;

    public LoadPersonas(IActividad actividad, PersonsAdapter rAdapter, SwipeRefreshLayout swipe) {
        super(actividad);
        this.adapter = rAdapter;
        this.swipeRefresh = swipe;
    }

    public LoadPersonas(IActividad actividad, PersonsAdapter rAdapter, String idRuta) {
        super(actividad);
        this.adapter = rAdapter;
        this.idRuta = idRuta;
    }

    @Override
    public void doInBackground() throws ErrorGeneral {
        try {
            this.lista = Persona.getPersonas(idRuta);

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
