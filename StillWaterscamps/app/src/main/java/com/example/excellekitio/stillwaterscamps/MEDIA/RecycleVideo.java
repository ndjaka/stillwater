package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Context;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import java.util.ArrayList;

/**
 * Created by InfinixX99 on 17/12/2017.
 */

public class RecycleVideo extends  RecyclerView.Adapter<ListVideoHolder> {

    private ArrayList<DataObjectEssam> mDataset ;
    private  Context  context;
    private final OnItemClickListener myClickListener;
    public RecycleVideo(ArrayList<DataObjectEssam> myDataset, Context context, OnItemClickListener myClickListener) {
        mDataset = myDataset;
        this.context=context ;
        this.myClickListener = myClickListener;
    }
    @Override
    public ListVideoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_videoessam, parent, false);
        ListVideoHolder listVideoHolder =   new ListVideoHolder(view,context);
        return listVideoHolder;
    }

    @Override
    public void onBindViewHolder(ListVideoHolder holder, int position) {
        holder.campability(mDataset.get(position),this.myClickListener);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface OnItemClickListener {
        public void onItemClick(DataObjectEssam contentItem);
    }

}


class ListVideoHolder extends RecyclerView.ViewHolder
{
    VideoView videoView;
    CardView card;
    Context parent ;
    TextView date ;
    TextView titre ;
    public ListVideoHolder(View itemView,Context context) {
        super(itemView);
        videoView = (VideoView)itemView.findViewById(R.id.videoview);
        card= (CardView)itemView.findViewById(R.id.card_view);
        parent =context ;
        date=(TextView)itemView.findViewById(R.id.date_p);
        titre=(TextView)itemView.findViewById(R.id.titre_p);
    }
    public void  campability(final DataObjectEssam dataObjectEssam, final RecycleVideo.OnItemClickListener listener)
    {
        String url  = CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName=" +dataObjectEssam.getImageUrl();
        url = url.replace(" ", "%20");
        Uri myUri = Uri.parse(url);
        videoView.setVideoURI(myUri);
        titre.setText(ModelCamp.findById(Integer.parseInt(dataObjectEssam.getmText1())));
        date.setText(dataObjectEssam.getmText2());
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(dataObjectEssam);
            }
        });
    }
}
