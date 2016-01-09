package com.chaosphoenix.kangurooguard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.chaosphoenix.kangurooguard.R;
import com.chaosphoenix.kangurooguard.framework.entities.Ruta;
import com.chaosphoenix.kangurooguard.util.UtilResource;
import com.chaosphoenix.kangurooguard.util.basic.Transform;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by usuario.apellido on 28/02/2015.
 *
 * @author david.sancho
 */
public class RoutesAdapter extends ArrayAdapter<Ruta> {

    private Context mContext;
    private List<Ruta> mRoutes;

    public RoutesAdapter(Context context) {
        this(context, new ArrayList<Ruta>());
    }

    public RoutesAdapter(Context context, List<Ruta> routes) {
        super(context, R.layout.additem_route, routes);
        mContext = context;
        mRoutes = routes;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.additem_route, null);

            holder = new ViewHolder();
            holder.setRouteLabel((TextView) convertView.findViewById(R.id.routelabel));
            holder.setItemCounter((TextView) convertView.findViewById(R.id.itemcounter));

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Ruta ruta = mRoutes.get(position);
        if(ruta != null) {
            holder.routeLabel.setText(ruta.getName());

            SecureRandom sRnd = new SecureRandom();
            int kids = sRnd.nextInt(9) + 1;

           String alumnos = UtilResource.getString(R.string.number_kids, Transform.toString(kids));

            holder.itemCounter.setText(alumnos);
        }

        return convertView;

    }

    public void refill(List<Ruta> rutas) {
        mRoutes.clear();
        mRoutes.addAll(rutas);
        notifyDataSetChanged();
    }

    public Ruta get(int i) {
        return mRoutes.get(i);
    }

    private static class ViewHolder {
        private TextView routeLabel;
        private TextView itemCounter;

        private ViewHolder() {
        }

        public void setRouteLabel(TextView routeLabel) {
            this.routeLabel = routeLabel;
        }

        public void setItemCounter(TextView itemCounter) {
            this.itemCounter = itemCounter;
        }
    }
}
