package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.MEDIA.DataObject;
import com.example.excellekitio.stillwaterscamps.MEDIA.MyRecyclerViewAdapter;
import com.example.excellekitio.stillwaterscamps.Partipants.partipants;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;

import java.util.ArrayList;

public class listedesarticles extends AppCompatActivity {


    Button btnpopup;
    Context ctx;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private static String LOG_TAG = "CardViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {


                ctx = this;
                super.onCreate(savedInstanceState);
                setContentView(R.layout.listdesarticles);

                mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
                mRecyclerView.setHasFixedSize(true);
                mLayoutManager = new LinearLayoutManager(this);
                mRecyclerView.setLayoutManager(mLayoutManager);
                mAdapter = new RecyclerViewCamp((ArrayList<Camp>) ModelCamp.getListCamp());
                mRecyclerView.setAdapter(mAdapter);



    }


    @Override
    protected void onResume() {
            super.onResume();
        ((RecyclerViewCamp) mAdapter).setOnItemClickListener(new RecyclerViewCamp.MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);


            }
        });
    }

    public void ouvrirgallery(View view ){

        Intent intent=new Intent(this,partipants.class);
            startActivity(intent);
    }

}