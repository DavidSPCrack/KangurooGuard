package com.sistemasikanguro.kangurooguard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.sistemasikanguro.kangurooguard.R;
import com.sistemasikanguro.kangurooguard.framework.entities.Persona;
import com.sistemasikanguro.kangurooguard.util.basic.Fecha;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario.apellido on 28/02/2015.
 *
 * @author david.sancho
 */
public class PersonsAdapter extends ArrayAdapter<Persona> {

    private Context mContext;
    private List<Persona> mPersons;

    public PersonsAdapter(Context context) {
        this(context, new ArrayList<Persona>());
    }

    public PersonsAdapter(Context context, List<Persona> persons) {
        super(context, R.layout.additem_student, persons);
        mContext = context;
        mPersons = persons;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.additem_student, null);

            holder = new ViewHolder();
            holder.setCheckbox((CheckBox) convertView.findViewById(R.id.checkBox));
            holder.setStudentLabel((TextView) convertView.findViewById(R.id.studenlabel));
            holder.setTimeLabel((TextView) convertView.findViewById(R.id.timeLabel));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Persona persona = mPersons.get(position);
        if (persona != null) {
            SecureRandom sRnd = new SecureRandom();
            boolean checked = sRnd.nextBoolean();
            holder.checkbox.setChecked(checked);

            holder.studentLabel.setText(persona.getName());
            holder.timeLabel.setText(Fecha.getFechaSistema().toString());
        }

        return convertView;

    }

    public void refill(List<Persona> personas) {
        mPersons.clear();
        mPersons.addAll(personas);
        notifyDataSetChanged();
    }

    public Persona get(int i) {
        return mPersons.get(i);
    }

    private static class ViewHolder {
        private CheckBox checkbox;
        private TextView studentLabel;
        private TextView timeLabel;

        private ViewHolder() {
        }

        public void setCheckbox(CheckBox checkbox) {
            this.checkbox = checkbox;
        }

        public void setStudentLabel(TextView studentLabel) {
            this.studentLabel = studentLabel;
        }

        public void setTimeLabel(TextView timeLabel) {
            this.timeLabel = timeLabel;
        }
    }
}
