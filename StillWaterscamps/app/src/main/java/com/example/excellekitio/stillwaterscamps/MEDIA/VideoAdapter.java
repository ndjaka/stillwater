package com.example.excellekitio.stillwaterscamps.MEDIA;

/**
 * Created by FELICITE on 13/12/2017.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.VideoView;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.filemanager.DownloadTask;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import java.util.List;


public class VideoAdapter extends BaseAdapter {

    public static Context context;
    private LayoutInflater inflater = null;
    static int idList;
    List<DataObjectEssam> VideosList;

    VideoAdapter(Context con, List<DataObjectEssam> list) {

        VideoAdapter.context = con;
        inflater = LayoutInflater.from(context);
        VideosList =list ;
    }


    @Override
    public int getCount() {
        return VideosList.size();
    }

    @Override
    public Object getItem(int i) {
        return VideosList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertview, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (convertview == null) {

            convertview = inflater.inflate(R.layout.video_template, null);
            holder = new ViewHolder();
            VideoAdapter.idList= i;
            holder.titre =(TextView)convertview.findViewById(R.id.idtitre);
            holder.videoPlayer = (VideoView) convertview.findViewById(R.id.idvideo);
            holder.downloadB = (ImageButton)convertview.findViewById(R.id.id_downloadV);

            convertview.setTag(holder);

        } else {
            holder = (ViewHolder) convertview.getTag();
        }

        holder.titre.setText(VideosList.get(i).getmText1());
        String url  = CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName=" +VideosList.get(i).getImageUrl();
        url = url.replace(" ", "%20");
        Uri myUri = Uri.parse(url);
        holder.videoPlayer.setVideoURI(myUri);
        MyClickClass mCC = new MyClickClass(i);
        holder.downloadB.setOnClickListener(mCC);
        return convertview;
    }
    public class MyClickClass implements View.OnClickListener {
        int idTest;
        public MyClickClass(int e){
            this.idTest= e;
        }
        @Override
        public void onClick(View v) {
            telecharg(idTest);
        }
    }

    private void telecharg(int i){
        String titreProgression="Telechargement...";
        ProgressDialog mProgressDialog = ProgressDialog.show(VideoAdapter.context, titreProgression, "Veuillez patienter", true);
        mProgressDialog.setCancelable(false);


        String url = VideosList.get(i).getImageUrl() ;
        DownloadTask downloadTask = new DownloadTask(context,mProgressDialog,url,2);
        downloadTask.execute(CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName="+url);
    }
    public class ViewHolder {

       VideoView videoPlayer;
        TextView titre;
        ImageButton downloadB;
    }
}