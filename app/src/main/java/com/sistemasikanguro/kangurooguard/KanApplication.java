package com.sistemasikanguro.kangurooguard;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseCrashReporting;
import com.parse.ParseException;
import com.parse.ParseUser;
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
        super.onCreate();
        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);
        ParseCrashReporting.enable(this);

        Parse.initialize(this, "TryWsZNlenNVm78IatXtzpPEM8G2CJISYEffoznN", "zDr1H4RMytzEWDj42e2neDx76q9Hr0rbugJt6NNW");

        try {
            ParseUser pUser = ParseUser.getCurrentUser();
            if (pUser != null)
                pUser.fetch();
        } catch (ParseException e) {
            ParseUser.logOut();
        }

        ParseAD adatos = ParseAD.getInstance();
        adatos.updateInstallation();
        AnalyticsTrackers.initialize(this);

        app = this;
    }

}
