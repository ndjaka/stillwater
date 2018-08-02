package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Media;
import com.example.excellekitio.stillwaterscamps.implement.MediaImp;

import java.util.ArrayList;
import java.util.List;

public class galerievideoessam extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private ImageView imageView;
    private int [] images = {R.drawable.camp, R.drawable.campb};
    private String[]date={"2016","2017"};
    private CardView cardg;
    private ArrayList<DataObjectEssam> dataObjectEssams;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        imageView = (ImageView) findViewById(R.id.image);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        dataObjectEssams = getDataSet();

        if(dataObjectEssams.size()>0)
        {
            mAdapter = new RecycleVideo(dataObjectEssams, this, new RecycleVideo.OnItemClickListener() {
                @Override
                public void onItemClick(DataObjectEssam contentItem) {
                    Intent inten  =  new Intent(galerievideoessam.this,videoparticipantsEssam.class);
                    inten.putExtra("idCamp",contentItem.getId());
                    startActivity(inten);
                }
            });


            mRecyclerView.setAdapter(mAdapter);

        }else
        {
            TextView textView =  new TextView(this);
            textView.setText("Pas de video disponibles");
            addContentView(textView,new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }


        /*imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {

            }
        });*/

// Code to Add an item with default animation
//((MediaRecyclerViewAdapter) mAdapter).addItem(obj, index);
// Code to remove an item with default animation
//((MediaRecyclerViewAdapter) mAdapter).deleteItem(index);
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    private ArrayList<DataObjectEssam> getDataSet() {
        ArrayList results = new ArrayList<DataObjectEssam>();
        List<Media> lastMedias   = MediaImp.getInstance().lastsImgByCamp("video");
        DataObjectEssam obj;
        for (int index = 0; index <lastMedias.size(); index++) {
            obj= new DataObjectEssam(String.valueOf(lastMedias.get(index).getId_camp()),
                    lastMedias.get(index).getDate(),lastMedias.get(index).getContenu(),lastMedias.get(index).getId_camp(),"video");
            results.add(index, obj);
        }
        return results;
    }
  /*  public void ouvrirgallery(View view ){
        Intent intent=new Intent(this,videocamp.class);
        startActivity(intent);
    }*/
}
