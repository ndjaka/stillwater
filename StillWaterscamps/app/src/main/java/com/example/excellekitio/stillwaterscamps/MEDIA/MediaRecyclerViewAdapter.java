package com.example.excellekitio.stillwaterscamps.MEDIA;
/**
 * Creé par  excelle kitio le 15/11/2017.
 */

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewParentCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class MediaRecyclerViewAdapter extends RecyclerView
        .Adapter<MediaRecyclerViewAdapter
        .DataObjectHolder> {
    private static String LOG_TAG = "MediaRecyclerViewAdapter";
    private ArrayList<DataObjectEssam> mDataset;
    private static OnItemClickListener myClickListener;
    Context  context ;

    public MediaRecyclerViewAdapter(ArrayList<DataObjectEssam> dataObjectEssams, Context context) {
        this(dataObjectEssams);
        this.context=context;
    }

    public MediaRecyclerViewAdapter(ArrayList<DataObjectEssam> mDataset, OnItemClickListener clickListener, Context context) {
        this.mDataset = mDataset;
        this.context = context;
        this.myClickListener=clickListener;
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder  {
        TextView label;
        TextView dateTime;
        ImageView imagev;
        int idCamp ;
        CardView card ;
        VideoView videoView ;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView2);
            imagev=(ImageView) itemView.findViewById(R.id.image);
            card =  (CardView)itemView.findViewById(R.id.cardgalerie) ;
        }
        public  void Bind(final DataObjectEssam dataObjectEssam, final OnItemClickListener listener)
        {
            label.setText(dataObjectEssam.getmText1());
            dateTime.setText(dataObjectEssam.getmText2());
            idCamp=dataObjectEssam.getId();
            Picasso.with(imagev.getContext()).load(CallWebService.URL_DOWNLOAD_SERVLET+dataObjectEssam.getImageUrl()).into(imagev);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(dataObjectEssam);
                }
            });

            /*card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type.equals("image"))
                    {
                        Intent intent =  new Intent(context ,Galleryessam.class);
                        intent.putExtra("idCamp",idC);
                        context.startActivity(intent);
                    }else
                    {
                        Intent  intent =  new Intent(context ,videoparticipantsEssam.class);
                        intent.putExtra("idCamp",idC);
                        context.startActivity(intent);
                    }

                }
            });*/
        }

    }
    public MediaRecyclerViewAdapter(ArrayList<DataObjectEssam> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row_essam, parent, false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(final DataObjectHolder holder, int position) {

        holder.Bind(mDataset.get(position),myClickListener);
    }

    public String convertDare(String longd)
    {
        longd+="L";
        Date date  = new Date(Long.parseLong(longd) * 1000);
        return date.getDay()+"-"+date.getMonth()+"-"+date.getYear();
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface OnItemClickListener {
        public void onItemClick(DataObjectEssam contentItem);
    }

}
