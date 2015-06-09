package com.sistemasikanguro.kangurooguard.framework.actions;

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
    }

    @Override
    public String getTitle() {
        return UtilResource.getString(R.string.loading);
    }

}
