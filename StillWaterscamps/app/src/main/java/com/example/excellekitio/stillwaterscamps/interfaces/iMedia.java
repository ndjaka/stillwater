package com.example.excellekitio.stillwaterscamps.interfaces;

import android.app.ProgressDialog;

import com.example.excellekitio.stillwaterscamps.Tmedia;
import com.example.excellekitio.stillwaterscamps.entitiees.Media;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iMedia {
    public abstract void addMedia(ArrayList<Tmedia> tmedias, int id_camp, ProgressDialog progressDialog);
    public abstract List<Media> allMediaByCamp(int idCamp);
    public  abstract Media find(int idMedia);
}
