package com.sistemasikanguro.kangurooguard.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseUser;
import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.adapters.StandardAdapter;
import com.sistemasikanguro.kangurooguard.framework.ErrorGeneral;
import com.sistemasikanguro.kangurooguard.framework.entities.Usuario;
import com.sistemasikanguro.kangurooguard.ui.activities.LoginActivity;


/**
 * Created by usuario.apellido on 28/02/2015.
 *
 * @author david.sancho
 */
public class UtilActivity {

    private Activity mActivity;

    public UtilActivity(Activity activity) {
        this.mActivity = activity;
    }

    public void doFieldAlertDialog(String field) {
        doAlertDialog(R.string.field_missing, field);
    }

    public void doAlertDialog(int msgId, String var) {
        String msg = getResourceString(msgId, var);
        doAlertDialog(msg);
    }

    public void doAlertDialog(int msgId) {
        String msg = getResourceString(msgId);
        doAlertDialog(msg);
    }

    public void doAlertDialog(Throwable t) {
        String msg = t.getMessage();
        doAlertDialog(msg);
    }

    public void doAlertDialog(String msg) {
        String title = getResourceString(R.string.alert);
        String button = getResourceString(R.string.alert_button);
        doDialog(title, msg, button);
    }

    public Resources getResources() {
        return mActivity.getResources();
    }

    public View findViewById(int id) {
        return mActivity.findViewById(id);
    }

    public int getIdentifier(String name, String type, String defPackage) {
        Resources res = getResources();
        return res.getIdentifier(name, type, defPackage);
    }

    public String getResourceString(int id) {
        Resources res = getResources();
        return res.getString(id);
    }

    public String getResourceString(String name) {
        int id = getIdentifier(name, "values", null);
        if(id == 0) {
            return "";
        }
        return getResourceString(id);
    }

    public String getResourceString(int id, String var1) {
        String s = getResourceString(id);
        return String.format(s, var1);
    }

    public String getResourceString(int id, String var1, String var2) {
        String s = getResourceString(id);
        return String.format(s, var1, var2);
    }

    public void doDialog(String title, String msg, String button) {
        AlertDialog alertDialog = new AlertDialog.Builder(mActivity).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(msg);
        alertDialog.setIcon(R.mipmap.ic_warning_amber);
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, button,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public String getEditTextValue(int id) {
        View v = findViewById(id);
        if (v != null) {
            if (v instanceof EditText) {
                EditText et = (EditText) v;
                Editable editable = et.getText();
                return editable.toString();
            }
        }
        return "";
    }

    public String getEditTextHint(int id) {
        View v = findViewById(id);
        if (v != null) {
            if (v instanceof EditText) {
                EditText et = (EditText) v;
                return et.getHint().toString();
            }
        }
        return "";
    }

    public void setEditTextValue(int id, String value) {
        View v = findViewById(id);
        if (v != null) {
            if (v instanceof EditText) {
                EditText et = (EditText) v;
                et.setText(value);
            }
        }
    }

    public void setButtonText(int id, String value) {
        View v = findViewById(id);
        if (v != null) {
            if (v instanceof Button) {
                Button bt = (Button) v;
                bt.setText(value);
            }
        }
    }

    public String getEditTextValueAndValidate(int id) {
        String text = getEditTextValue(id);
        String field = getEditTextHint(id);
        validateNoEmpty(text, field);
        return text;
    }

    public boolean validateNoEmpty(String text, String field) {
        if (isTextEmpty(text)) {
            doFieldAlertDialog(field);
            return false;
        }
        return true;
    }

    public boolean isTextEmpty(String s) {
        return s == null || s.isEmpty();
    }

    public boolean isAnyEmpty(String... strs) {
        for (String str : strs) {
            if (isTextEmpty(str)) {
                return true;
            }
        }
        return false;
    }

    public Drawable getDrawableRes(int id) {
        Resources res = getResources();
        return res.getDrawable(id);
    }

    public ProgressDialog getProgressDialog(int title) {
        return getProgressDialog(title, getResourceString(R.string.please_wait));
    }

    public ProgressDialog getProgressDialog(int title, String message) {
        return ProgressDialog.show(mActivity,
                getResourceString(title),
                message, true);
    }

    public void openNewHomeActivity(Class<?> dstClass) {
        Intent intent = getIntent(dstClass, true, true);
        mActivity.startActivity(intent);
    }

    public void openNewTaskActivity(Class<?> dstClass) {
        Intent intent = getIntent(dstClass, true, false);
        mActivity.startActivity(intent);
    }

    public void openNewActivity(Class<?> dstClass) {
        Intent intent = getIntent(dstClass, false, false);
        mActivity.startActivity(intent);
    }

    private Intent getIntent(Class<?> dstClass, boolean newTask, boolean clearTask) {
        Intent intent = new Intent(mActivity, dstClass);
        if (newTask)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (clearTask)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        return intent;
    }

    public void hideProgressDialog(ProgressDialog progressDialog) {
        progressDialog.dismiss();
    }

    public void hideView(int id) {
        View vFound = findViewById(id);
        hideView(vFound);
    }

    public void hideView(View v) {
        if (v != null) {
            v.setVisibility(View.GONE);
        }
    }

    public void doToast(int textId) {
        String text = getResourceString(textId);
        doToast(text);
    }

    public void doToast(String text) {
        Toast.makeText(mActivity, text, Toast.LENGTH_SHORT).show();
    }

    public StandardAdapter<ParseUser> getAdapterUsers(int layout) {
        return new StandardAdapter<>(mActivity, layout);
    }

    public StandardAdapter<ParseObject> getAdapterObjects(int layout) {
        return new StandardAdapter<>(mActivity, layout);
    }

    public boolean isUsuarioPadre() {
        Usuario user = getUsuarioActual();
        return user != null && user.isPadre();
    }

    public boolean isUsuarioMonitor() {
        Usuario user = getUsuarioActual();
        return user != null && user.isMonitor();
    }

    public boolean isUsuarioAdministrador() {
        Usuario user = getUsuarioActual();
        return user != null && user.isAdministrador();
    }

    public Usuario getUsuarioActual() {
        try {
            Usuario user = Usuario.getInstance();
            if (user == null)
                throw new ErrorGeneral(getResourceString(R.string.logon_exception));

            return user;
        } catch (ErrorGeneral eg) {
            doAlertDialog(eg);

            openNewHomeActivity(LoginActivity.class);
            return null;
        }
    }

}
