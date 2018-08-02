package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.Camp;

import java.util.List;

/**
 * Created by lesli on 13/12/2017.
 */

public interface iCamp {
    public abstract List<Camp> allCamp();
    public abstract Camp creerCamp(String theme, String description, String date_debut, String date_fin, int age_min, int age_max, int auteur,String logo);
    public abstract boolean delateCamp(int id_camp);
    public abstract boolean updateCamp(int id, String theme, String description, String date_debut, String date_fin, int age_min, int age_max, int auteur);
}
