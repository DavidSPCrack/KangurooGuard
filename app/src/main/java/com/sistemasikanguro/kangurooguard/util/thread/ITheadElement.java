package com.sistemasikanguro.kangurooguard.util.thread;

import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public interface ITheadElement {

    void execute();

    void doInBackground() throws ErrorGeneral;

    void postExecute();

}
