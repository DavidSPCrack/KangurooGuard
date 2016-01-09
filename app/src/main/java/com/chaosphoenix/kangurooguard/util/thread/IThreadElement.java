package com.chaosphoenix.kangurooguard.util.thread;

import com.chaosphoenix.kangurooguard.framework.ErrorGeneral;

/**
 * Created by David on 10/05/2015.
 *
 * @author david.sancho
 */
public interface IThreadElement {

    void execute(IThreadElement... elements);

    void doInBackground() throws ErrorGeneral;

    void postExecute();

    String getTitle();
}
