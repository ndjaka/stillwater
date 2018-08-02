package com.example.excellekitio.stillwaterscamps;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.temoignage.CustomAdapter;
import com.example.excellekitio.stillwaterscamps.temoignage.datatemoignage;

import java.util.List;

/**
 * Created by ndjaka on 18/12/2017.
 */

public class valider_demandeCustomAdapter extends BaseAdapter {
    Context context;
    List<Validerdemanderow> rowItems;


    public valider_demandeCustomAdapter(Context context, List<Validerdemanderow> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
    }
    public valider_demandeCustomAdapter() {

    }
    @Override
    public int getCount() {
        return rowItems.size();
    }

    @Override
    public Object getItem(int position) {
        return rowItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return rowItems.indexOf(getItem(position));
    }
    private class ViewHolder {
        TextView email;
        TextView id_camp;
        TextView valider;


    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
       ViewHolder holder = null;
        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
                if (convertView == null) {
                    convertView = mInflater.inflate(R.layout.valider_demane_row, null);
                    holder = new ViewHolder();
                    holder.email = (TextView) convertView
                            .findViewById(R.id.id_email);
                    holder.id_camp = (TextView) convertView
                            .findViewById(R.id.id_value_camp);
                    holder.valider = (TextView) convertView
                            .findViewById(R.id.id_valider);
                    Validerdemanderow rowpos = rowItems.get(position);
                    holder.email.setText(rowpos.getEmail());
                    holder.id_camp.setText(rowpos.getId_camp());
                    holder.valider.setText(rowpos.getValider().toString());
                    convertView.setTag(holder);
                } else {
                    holder = (ViewHolder) convertView.getTag();
                }

        return convertView;



    }
}
