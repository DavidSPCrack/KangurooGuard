package com.chaosphoenix.kangurooguard.framework.entities;

import com.parse.ParseUser;
import com.chaosphoenix.kangurooguard.framework.BasicEntity;
import com.chaosphoenix.kangurooguard.framework.DataSource;
import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;
import com.chaosphoenix.kangurooguard.framework.ad.ADUsuario;
import com.chaosphoenix.kangurooguard.util.ParseAD;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public class Usuario extends BasicEntity {

    public static final class K {
        public static final class Tipos {
            public static final String ADMINISTRADOR = "ADMIN";
            public static final String MONITOR = "MONITOR";
            public static final String PADRE = "PADRE";

        }
    }

    public static final String TABLE_NAME = "_User";

    public static final String NAME = "name";
    public static final String SURNAME = "surname";
    public static final String TELEPHONE = "telephone";
    public static final String NUMEDOCU = "numedocu";
    public static final String COMMENTS = "comments";

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TYPE = "type";

    public static final String[] FIELDS = {NAME, SURNAME, TELEPHONE, NUMEDOCU, COMMENTS, USERNAME, PASSWORD, EMAIL, TYPE};

    public static final Usuario INSTANCE_VOID = new Usuario(new DataSource(TABLE_NAME));

    private ParseUser pUser;

    Usuario(DataSource eDatos) {
        super(eDatos);
    }

    public String getUsername() {
        return getDato(USERNAME);
    }

    public String getPassword() {
        return getDato(PASSWORD);
    }

    public String getEmail() {
        return getDato(EMAIL);
    }

    public String getTipo() {
        return getDato(TYPE);
    }

    public String getName() {
        return getDato(NAME);
    }

    public String getSurname() {
        return getDato(SURNAME);
    }

    public String getTelephone() {
        return getDato(TELEPHONE);
    }

    public String getNumeroDocumento() {
        return getDato(NUMEDOCU);
    }

    public String getComments() {
        return getDato(COMMENTS);
    }

    public boolean isAdministrador() {
        return getTipo().equals(K.Tipos.ADMINISTRADOR);
    }

    public boolean isMonitor() {
        return getTipo().equals(K.Tipos.MONITOR);
    }

    public boolean isPadre() {
        return getTipo().equals(K.Tipos.PADRE);
    }


    public static Usuario getInstanceVoid() {
        return INSTANCE_VOID;
    }

    public static Usuario getInstance(String usuarCod) throws ErrorGeneral {
        ADUsuario adatos = new ADUsuario();
        DataSource eDatos = adatos.getUsuario(usuarCod);
        if (eDatos == null)
            return null;
        Usuario user = new Usuario(eDatos);
        return user;
    }

    public static Usuario getInstance() throws ErrorGeneral {
        ADUsuario adatos = new ADUsuario();
        DataSource eDatos = adatos.getCurrentUser();
        if (eDatos == null)
            return null;
        Usuario user = new Usuario(eDatos);
        return user;
    }

    public static Usuario login(String username, String password) throws ErrorGeneral {
        try {
            ADUsuario adatos = new ADUsuario();
            DataSource eDatos = adatos.login(username, password);
            Usuario user = new Usuario(eDatos);
            return user;
        } catch (ErrorGeneral e) {
            throw new ErrorGeneral(e);
        }
    }

    public static void logout() {
        ParseUser.logOut();

        ParseAD adatos = ParseAD.getInstance();
        adatos.updateInstallation();
    }


    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }

    @Override
    protected String getStructureName() {
        return TABLE_NAME;
    }

    @Override
    public String toString() {
        return getUsername();
    }

    @Override
    public String getKeyOrder() {
        return getUsername();
    }
}
