package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.Tmedia;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Creé par  root le 16/12/17.
 */

public class MediaAdapterEssam extends BaseAdapter {
    private Context mContext;
    private ArrayList<Tmedia>  medias ;
    private boolean b=false ;

    public MediaAdapterEssam(Context mContext, ArrayList<Tmedia> medias) {
        this.mContext = mContext;
        this.medias = medias;

    }

    public MediaAdapterEssam(Context galleryessam, ArrayList<Tmedia> multimedia, boolean b) {

        this(galleryessam,multimedia);
        this.b=b;
    }

    @Override
    public int getCount() {
        return medias.size();
    }

    @Override
    public Object getItem(int position) {
        return medias.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(120, 120));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else
        {
            imageView = (ImageView) convertView;
        }
        if (medias.get(position).getTypeMedia().equals("image"))
        {
            if(b)
            {
                Picasso.with(imageView.getContext()).load(CallWebService.URL_DOWNLOAD_SERVLET+medias.get(position).getFilePath()).into(imageView);
            }else
            {
                imageView.setImageURI(medias.get(position).getUriMedia());
            }
        }else
        {
          imageView.setImageBitmap(ThumbnailUtils.createVideoThumbnail(medias.get(position).getFilePath(), MediaStore.Images.Thumbnails.MINI_KIND));
        }
        return imageView;
    }
    public void AddItem(Tmedia tmedia)
    {
        medias.add(tmedia);
    }

    public ArrayList<Tmedia> getMedias() {
        return medias;
    }
}
