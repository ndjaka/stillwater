package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.R;

/**
 * Created by excelle kitio on 22/11/2017.
 */

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    // Constructor
    public ImageAdapter(Context c) {
        mContext = c;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);
        return imageView;
    }

    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.camp, R.drawable.jourr,
            R.drawable.jour, R.drawable.campii,
            R.drawable.campp, R.drawable.camp,
            R.drawable.campii, R.drawable.jour,
            R.drawable.campi, R.drawable.campi,
            R.drawable.camp, R.drawable.jourr,
            R.drawable.jour, R.drawable.campii,
            R.drawable.campp, R.drawable.camp,
            R.drawable.campii, R.drawable.jour,
            R.drawable.campi, R.drawable.campi,

    };
}
