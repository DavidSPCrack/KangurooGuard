package com.sistemasikanguro.kangurooguard.framework.ad;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.EstructuraDatos;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;

/**
 * Created by usuario.apellido on 15/05/2015.
 *
 * @author david.sancho
 */
public final class ADUsuario extends AbstractAccesoADatos {

    public ADUsuario() {
    }

    public EstructuraDatos getUsuario(String usuarCod) throws ErrorGeneral {
        try {
            ParseQuery<ParseUser> pQuery = ParseUser.getQuery();
            pQuery.whereContains(Usuario.USERNAME, usuarCod);
            ParseUser pUser = pQuery.getFirst();
            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public EstructuraDatos getCurrentUser() throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.getCurrentUser();
            if (pUser != null)
                pUser = pUser.fetchIfNeeded();
            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public EstructuraDatos login(String username, String password) throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.logIn(username, password);
            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    private EstructuraDatos getEstructuraUsuario(ParseUser pUser) {
        if (pUser == null)
            return null;
        EstructuraDatos eDatos = new EstructuraDatos(Usuario.TABLE_NAME);
        eDatos.update(pUser, Usuario.FIELDS);
        return eDatos;
    }

    void createUser(EstructuraDatos eDatos) throws ErrorGeneral {
        try {
            ParseUser pUser = new ParseUser();
            String[] fields = eDatos.getFields();
            for (String field : fields) {
                pUser.put(field, eDatos.getString(field));
            }
            pUser.signUp();
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    void updateUsuario(EstructuraDatos eDatos) throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.getCurrentUser();
            if (pUser != null) {
                pUser = pUser.fetch();
                String[] fields = eDatos.getFields();
                for (String field : fields) {
                    if (isUpdatableField(field))
                        pUser.put(field, eDatos.getString(field));
                }
                pUser.save();
            }
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }


}
