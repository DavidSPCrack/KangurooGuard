package com.sistemasikanguro.kangurooguard.framework.entities;

import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.framework.BasicEntity;

import java.util.HashMap;

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

    public static Usuario getInstance(String usuarCod) {
        return null;
    }

    public static Usuario getInstance() {
        return null;
    }

    @Override
    public String[] getBasicFields() {
        return FIELDS;
    }
}
