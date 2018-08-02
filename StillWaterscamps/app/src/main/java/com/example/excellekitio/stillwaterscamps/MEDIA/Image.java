package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.R;

import java.util.ArrayList;

public class Image extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static String LOG_TAG = "CardViewActivity";
    private ImageView imageView;
    private int [] images = {R.drawable.camp, R.drawable.campb, R.drawable.campi, R.drawable.camppp, R.drawable.campp};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        imageView = (ImageView) findViewById(R.id.image);
        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new MyRecyclerViewAdapter(getDataSet());
        mRecyclerView.setAdapter(mAdapter);

// Code to Add an item with default animation
//((MyRecyclerViewAdapter) mAdapter).addItem(obj, index);
// Code to remove an item with default animation
//((MyRecyclerViewAdapter) mAdapter).deleteItem(index);
       /* imageView.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Image.this,Gallery.class);
                startActivity(intent);
            }
        });*/



    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyRecyclerViewAdapter) mAdapter).setOnItemClickListener(new MyRecyclerViewAdapter
                .MyClickListener() {

            @Override
            public void onItemClick(int position, View v) {
                Log.i(LOG_TAG, " Clicked on Item " + position);
            }
        });
    }
    private ArrayList<DataObject> getDataSet() {
        ArrayList results = new ArrayList<DataObject>();
        for (int index = 0; index < 5; index++) {
            DataObject obj = new DataObject("Camps" + index,
                    "date publication" + index, images[index]);
            //Integer.parseInt("date publication") + index, images[index]);
            results.add(index, obj);
        }
        return results;
    }
    public void ouvrirgallery(View view ){
        Intent intent=new Intent(this,Gallery.class);
        startActivity(intent);
    }
}
