package com.sistemasikanguro.kangurooguard.framework.actions;

import com.sistemasikanguro.kangurooguard.util.UtilActivity;
import com.sistemasikanguro.kangurooguard.util.thread.AsyncTaskStandard;
import com.sistemasikanguro.kangurooguard.util.thread.ITheadElement;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public abstract class AbstractAction implements ITheadElement {

    private UtilActivity util;

    protected AbstractAction(UtilActivity util) {
        this.util = util;
    }

    public UtilActivity getUtil() {
        return util;
    }

    @Override
    public void execute() {
        AsyncTaskStandard.doTask(getUtil(), this);
    }
}
