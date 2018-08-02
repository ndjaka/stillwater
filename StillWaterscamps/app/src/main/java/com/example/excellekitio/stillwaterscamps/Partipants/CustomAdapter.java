package com.example.excellekitio.stillwaterscamps.Partipants;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;

import java.util.List;

/**
 * Created by excelle kitio on 11/12/2017.
 */
public class CustomAdapter extends BaseAdapter {

    Context context;
    List<dataparticipant> rowItems;

    CustomAdapter(Context context, List<dataparticipant> rowItems) {
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
    private class ViewHolder {
        ImageView member_pic;
        TextView member_name;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater) context
                .getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_itemparticipant, null);
            holder = new ViewHolder();

            holder.member_name = (TextView) convertView
                    .findViewById(R.id.member_name);
            holder.member_pic = (ImageView) convertView
                    .findViewById(R.id.member_pic);

            dataparticipant row_pos = rowItems.get(position);

            holder.member_pic.setImageResource(row_pos.getMember_pic_id());
            holder.member_name.setText(row_pos.getMember_name());

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        return convertView;
    }

}
