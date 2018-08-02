package com.example.excellekitio.stillwaterscamps;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import java.io.IOException;
import java.net.URL;

/**
 * Creé par  root le 16/12/17.
 */

public class Tmedia {
    private Uri uriMedia  ;
    private String typeMedia ;
    private String filePath ;

    public Tmedia(Uri uriMedia, String typeMedia) {
        this.uriMedia = uriMedia;
        this.typeMedia = typeMedia;
    }

    public Tmedia(Uri uriMedia, String typeMedia, String filePath) {
        this.uriMedia = uriMedia;
        this.typeMedia = typeMedia;
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public Uri getUriMedia() {
        return uriMedia;
    }

    public void setUriMedia(Uri uriMedia) {
        this.uriMedia = uriMedia;
    }

    public String getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(String typeMedia) {
        this.typeMedia = typeMedia;
    }

    public static Bitmap bitmapImage(final String urlLink)
    {

        final URL[] url = new URL[1];
        final Bitmap[] bmp = {null};
        Thread mythread = new Thread(){
            @Override
            public  void run(){
                try {
                    url[0] = new URL(CallWebService.URL_DOWNLOAD_SERVLET2+urlLink);
                    bmp[0] = BitmapFactory.decodeStream(url[0].openConnection().getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        };
        mythread.start();

        return bmp[0];
    }
}
