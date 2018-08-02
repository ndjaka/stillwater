package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;

import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iTemoigniage {
    public abstract List<Temoigniage> allTemoigniage();
    public abstract boolean insert_temoignage(int id_user, String contenu, String titre);

}
