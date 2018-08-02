package com.example.excellekitio.stillwaterscamps;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class RateApp extends Activity {
    Context ctx;
    RatingBar rbrateapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        setContentView(R.layout.activity_rate_app);

        this.alloacatememory();
        this.setEvents();
    }

    private void setEvents() {
        rbrateapp.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast tost = Toast.makeText(getApplicationContext(), "Merci d'avoir donné " + rbrateapp.getProgress() + " étoiles", Toast.LENGTH_SHORT);
                tost.show();
            }
        });
    }

    private void alloacatememory() {
        rbrateapp = (RatingBar) findViewById(R.id.rbrateapp);
    }

}

