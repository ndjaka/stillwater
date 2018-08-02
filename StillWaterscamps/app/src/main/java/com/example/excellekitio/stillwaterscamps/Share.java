package com.example.excellekitio.stillwaterscamps;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Share extends AppCompatActivity {
    Button btnsharetext;
    TextView txtsharetext;
    Intent intent = null;
    Context ctx;
    Uri pictureUri;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_share);

        allocatememory();
        setevents();
    }

    private void allocatememory() {
        ctx = this;
        btnsharetext = (Button) findViewById(R.id.btnsharetext);
        txtsharetext = (TextView) findViewById(R.id.txtsharetext);
    }

    private void setevents() {
        btnsharetext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, txtsharetext.getText().toString());
                intent.setType("text/plain");
                startActivity(intent);

            }
        });
    }


}
