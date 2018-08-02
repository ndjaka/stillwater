package com.example.excellekitio.stillwaterscamps.MEDIA;

/**
 * Created by excelle kitio on 22/11/2017.
 */
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.filemanager.DownloadTask;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

public class SingleViewActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.single_view);
        Button  btn = (Button)findViewById(R.id.btn_down);

// Get intent data
        Intent i = getIntent();

// Selected image id
        final String url = i.getExtras().getString("urlMedia");
        ImageAdapteressam imageAdapteressam = new ImageAdapteressam(this);

        ImageView imageView = (ImageView) findViewById(R.id.SingleView);
        Picasso.with(imageView.getContext()).load(CallWebService.URL_DOWNLOAD_SERVLET+url).into(imageView);;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] fileName =  url.split("_");
                DownloadTask downloadTask = new DownloadTask(SingleViewActivity.this,new ProgressDialog(SingleViewActivity.this),fileName[fileName.length-1],1);
                downloadTask.execute(CallWebService.URL_DOWNLOAD_SERVLET+url);
            }
        });
    }
}
