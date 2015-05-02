package com.sistemasikanguro.kangurooguard.framework.entities;

import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;

import java.util.List;

/**
 * Created by David on 26/04/2015.
 *
 * @author david.sancho
 */
public class Usuario extends BasicEntity {

    public static final String USERNAME = "username";
    public static final String PASSWORD = "password";
    public static final String EMAIL = "email";

    public static final String[] FIELDS = {USERNAME, PASSWORD, EMAIL};

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

    public static Usuario getInstance(String usuarCod) throws ErrorGeneral {
        try {
            ParseQuery<ParseUser> pQuery = ParseUser.getQuery();
            pQuery.whereContains(USERNAME, usuarCod);
            List<ParseUser> lista = pQuery.find();
            if (lista.isEmpty())
                return null;

            ParseUser pUser = lista.get(0);

            return null;
        } catch (ParseException e) {
            throw new ErrorGeneral(e);
        }
    }

    public static Usuario getInstance() {
        ParseUser pUser = ParseUser.getCurrentUser();
        Usuario user = new Usuario(pUser);
        return user;
    }

    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }
}
