package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.Tmedia;
import com.example.excellekitio.stillwaterscamps.entitiees.Media;
import com.example.excellekitio.stillwaterscamps.implement.MediaImp;

import java.util.ArrayList;
import java.util.List;

public class Galleryessam extends AppCompatActivity {

    ArrayList<Tmedia> multimedia =  new ArrayList<Tmedia>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        int id=-1 ;
        Bundle bundle =  getIntent().getExtras();
        if(bundle!=null)
        {
            if(bundle.containsKey("idCamp"))
            {
                id =  bundle.getInt("idCamp");

            final List<Media>  mesMedias = MediaImp.getInstance().allMediaByCamp(id,"image");

            addMedia(mesMedias);
            Log.v("W>HH","IBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBSD : "+id);
            gridview.setAdapter(new MediaAdapterEssam(this,multimedia,true));
            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent,
                                        View v, int position, long id){
// Send intent to SingleViewActivity
                    Intent i = new Intent(getApplicationContext(), SingleViewActivity.class);
// Pass image index
                    i.putExtra("urlMedia", mesMedias.get(position).getContenu());
                    startActivity(i);
                }
            });
            }
        }else
        {
            TextView textView =  new TextView(this);
            textView.setText("Aucune photo enregistré pour ce camp");
            addContentView(textView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }


    }
    public void addMedia(List<Media> medias)
    {
        for(Media media :medias)
        {
            multimedia.add(new Tmedia(null,"image",media.getContenu()));
        }
    }
}
