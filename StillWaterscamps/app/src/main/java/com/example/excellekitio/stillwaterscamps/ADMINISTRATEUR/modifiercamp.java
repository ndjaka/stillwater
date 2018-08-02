package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.NotificationView;
import com.example.excellekitio.stillwaterscamps.R;

public class modifiercamp extends AppCompatActivity {
    ImageView image;

    Button b;

    Context ctx;


    NotificationManager notificationManager;
    Intent intent = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modifiercamp);
        image = (ImageView) findViewById(R.id.imageducamps);
        b = (Button) findViewById(R.id.idbtn);

        telechargerImageProfile();
        this.alloacatememory();
        this.setEvents();

    }
    private void setEvents() {
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(ctx, NotificationView.class);

                PendingIntent pendingIntent = PendingIntent.getActivity
                        (ctx, (int) System.currentTimeMillis(), intent, 0);

                long[] vv = {500, 1000};
                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                // you need to insert your image into drawable folder
                Bitmap largeicon = BitmapFactory.decodeResource(getResources(), R.drawable.businessman);

                Notification notification = new Notification.Builder(ctx)
                        .setContentTitle("Modification du compte")
                        .setContentText("Modification de la creation.")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(largeicon)
                        .setSound(uri)
                        .setAutoCancel(true)
                        .setFullScreenIntent(pendingIntent, true)
                        .setVibrate(vv)
                        .setWhen((long) System.currentTimeMillis())
                        .setContentIntent(pendingIntent)
                        .build();

                notificationManager.notify(0, notification);
            }
        });

    }
    private void alloacatememory() {

        ctx = this;
        b = (Button) findViewById(R.id.button_creerCompte);
        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }
    private void telechargerImageProfile() {
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 99);
            }
        });
    }
}
