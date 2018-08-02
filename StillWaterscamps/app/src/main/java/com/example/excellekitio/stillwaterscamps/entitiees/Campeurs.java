package com.example.excellekitio.stillwaterscamps.entitiees;

/**
 * Created by lesli on 13/12/2017.
 */

public class Campeurs {
    private String camp;
    private String campeur;

    public Campeurs() {
    }

    public Campeurs(String camp, String campeur) {
        this.camp = camp;
        this.campeur = campeur;
    }

    public String getCamp() {
        return camp;
    }

    public void setCamp(String camp) {
        this.camp = camp;
    }

    public String getCampeur() {
        return campeur;
    }

    public void setCampeur(String campeur) {
        this.campeur = campeur;
    }

    @Override
    public String toString() {
        return "Campeurs{" +
                "camp='" + camp + '\'' +
                ", campeur='" + campeur + '\'' +
                '}';
    }
}
