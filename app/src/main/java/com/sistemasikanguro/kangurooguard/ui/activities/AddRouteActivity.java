package com.sistemasikanguro.kangurooguard.ui.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.actions.CreateRuta;
import com.sistemasikanguro.kangurooguard.util.UtilActivity;

/**
 * Created by Andres on 18/05/2015.
 *
 * @author andres.alvarez
 */
public final class AddRouteActivity extends AbstractAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addroute);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    public void changeRoute(View view) throws ErrorGeneral {
        final UtilActivity util = getUtil();
        String name = util.getEditTextValue(R.id.etxtnameroute);
        String comment = util.getEditTextValue(R.id.etxtCommentRoutes);
        CreateRuta create = new CreateRuta(this, name, comment);
        create.execute();
    }

    public void cancelRoute(View view) throws ErrorGeneral {
        final UtilActivity util = getUtil();
        util.openNewActivity(RoutesActivity.class);
    }
}
