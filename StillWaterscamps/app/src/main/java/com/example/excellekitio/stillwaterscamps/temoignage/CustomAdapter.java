package com.example.excellekitio.stillwaterscamps.temoignage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by excelle kitio on 11/12/2017.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<datatemoignage> rowItems;

    CustomAdapter(Context context, List<datatemoignage> rowItems) {
        this.context = context;
        this.rowItems = rowItems;
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

    /* private view holder class */
    public class ViewHolder {
        TextView titre;
        TextView contenu;
//        TextView nom;
//        ImageView member_pic;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_temoignage, null);
            holder = new ViewHolder();

            holder.titre = (TextView) convertView
                    .findViewById(R.id.titre);
            holder.contenu = (TextView) convertView
                    .findViewById(R.id.contenu);
//            holder.member_pic = (ImageView) convertView
//                    .findViewById(R.id.member_pic);
//            holder.nom = (TextView) convertView
//                    .findViewById(R.id.nom);

            datatemoignage row_pos = rowItems.get(position);
            holder.titre.setText(row_pos.getTitre());
           // holder.nom.setText(row_pos.getId_user());
            holder.contenu.setText(row_pos.getContenu());


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
