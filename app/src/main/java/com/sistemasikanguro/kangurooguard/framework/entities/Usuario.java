package com.sistemasikanguro.kangurooguard.framework.entities;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.util.thread.ITheadElement;

import java.util.List;

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

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";
    public static final String TYPE = "type";

    public static final String[] FIELDS = {USERNAME, PASSWORD, EMAIL, TYPE};

    private ParseUser pUser;

    Usuario(ParseUser pUser) {
        super(pUser);
        this.pUser = pUser;
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

    public boolean isAdministrador() {
        return getTipo().equals(K.Tipos.ADMINISTRADOR);
    }

    public boolean isMonitor() {
        return getTipo().equals(K.Tipos.MONITOR);
    }

    public boolean isPadre() {
        return getTipo().equals(K.Tipos.PADRE);
    }

    public static Usuario getInstance(String usuarCod) throws ErrorGeneral {
        try {
            ParseQuery<ParseUser> pQuery = ParseUser.getQuery();
            pQuery.whereContains(USERNAME, usuarCod);
            List<ParseUser> lista = pQuery.find();
            if (lista.isEmpty())
                return null;

            ParseUser pUser = lista.get(0);
            Usuario user = new Usuario(pUser);
            return user;
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public static Usuario getInstance() {
        ParseUser pUser = ParseUser.getCurrentUser();
        if(pUser == null)
            return null;
        Usuario user = new Usuario(pUser);
        return user;
    }

    public static Usuario login(String username, String password) throws ErrorGeneral {
        try {
            ParseUser pUser = ParseUser.logIn(username, password);
            Usuario user = new Usuario(pUser);
            return user;
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public static void logout() {
        ParseUser.logOut();
    }


    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }
}
