package com.chaosphoenix.kangurooguard.framework.ad;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.chaosphoenix.kangurooguard.framework.BasicEntity;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.entities.Usuario;
import com.chaosphoenix.kangurooguard.util.ParseAD;

/**
 * Created by usuario.apellido on 15/05/2015.
 *
 * @author david.sancho
 */
public final class ADUsuario extends AbstractAccesoADatos {

    public ADUsuario() {
    }

    public DataSource getUsuario(String usuarCod) throws ErrorGeneral {
        try {
            ParseQuery<ParseUser> pQuery = ParseUser.getQuery();
            pQuery.whereContains(Usuario.USERNAME, usuarCod);
            ParseUser pUser = pQuery.getFirst();
            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public DataSource getCurrentUser() throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.getCurrentUser();
            if (pUser != null)
                pUser = pUser.fetchIfNeeded();
            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public DataSource login(String username, String password) throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.logIn(username, password);

            ParseAD adatos = ParseAD.getInstance();
            adatos.updateInstallation();

            return getEstructuraUsuario(pUser);
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    private DataSource getEstructuraUsuario(ParseUser pUser) {
        if (pUser == null)
            return null;
        DataSource eDatos = new DataSource(Usuario.TABLE_NAME);
        eDatos.update(pUser, Usuario.FIELDS);
        return eDatos;
    }

    void createUser(DataSource eDatos) throws ErrorGeneral {
        try {
            ParseUser pUser = new ParseUser();
            String[] fields = eDatos.getFields();
            for (String field : fields) {
                pUser.put(field, eDatos.getString(field));
            }
            pUser.signUp();

            ParseAD adatos = ParseAD.getInstance();
            adatos.updateInstallation();
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    void updateUsuario(DataSource eDatos) throws ErrorGeneral {
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

    public boolean isUpdatableField(String field) {
        switch (field) {
            case BasicEntity.CREATED_AT:
                return false;
            case BasicEntity.UPDATED_AT:
                return false;
            case BasicEntity.ID:
                return false;
            case Usuario.PASSWORD:
                return false;
        }
        return true;
    }


}
