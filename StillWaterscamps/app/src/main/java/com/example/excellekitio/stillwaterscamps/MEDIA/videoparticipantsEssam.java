package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Media;
import com.example.excellekitio.stillwaterscamps.implement.MediaImp;

import java.util.ArrayList;
import java.util.List;

public class videoparticipantsEssam extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos_list);
        int id=-1 ;
        Bundle bundle =  getIntent().getExtras();
        if(bundle!=null)
        {
            if(bundle.containsKey("idCamp"))
            {
                id =  bundle.getInt("idCamp");
            }
        }
        if (id>-1)
        {
            ListView lv = (ListView) findViewById(R.id.idListVideos);
            VideoAdapter adapter = new VideoAdapter(this,getDataSet(id));
            lv.setAdapter(adapter);
        }else {

                TextView textView =  new TextView(this);
                textView.setText("Aucune video enregistré pour ce camp");
                addContentView(textView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT));
        }

    }
    public ArrayList<DataObjectEssam> getDataSet(int id)
    {
        ArrayList<DataObjectEssam> dataObjectEssams = new ArrayList<DataObjectEssam>();
        List<Media> allMediaByCamp = MediaImp.getInstance().allMediaByCamp(id,"video");

        for (Media media : allMediaByCamp)
        {
            dataObjectEssams.add(new DataObjectEssam(media.getTitre(),media.getDate(),media.getContenu()));
        }
    return dataObjectEssams;
    }
}

