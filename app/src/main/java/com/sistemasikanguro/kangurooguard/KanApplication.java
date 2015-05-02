package com.sistemasikanguro.kangurooguard;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.sistemasikanguro.kangurooguard.util.ParseAD;

/**
 * Created by david.sancho on 16/01/2015.
 *
 * @author david.sancho
 */
public class KanApplication extends Application {

    private static KanApplication app;

    public static KanApplication getInstance() {
        return app;
    }

    @Override
    public void onCreate() {
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseCrashReporting.enable(this);

        Parse.initialize(this, "t6VmmFjjxEJmJWlmMPD1OWX74QP0l2lxlMZAoNE1", "YXGs5UvIXHkDwpJ91mrOlzVw5gs0xutvNr7eW25K");

        ParseAD adatos = ParseAD.getInstance();
        adatos.updateInstallation();

        app = this;
    }

}
