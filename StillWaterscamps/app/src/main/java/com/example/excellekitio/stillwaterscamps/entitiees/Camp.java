package com.example.excellekitio.stillwaterscamps.entitiees;

import java.util.Date;

/**
 * Created by lesli on 13/12/2017.
 */

public class Camp {
    private int id;
    private String theme;
    private String description;
    private String logo;
    private String date_debut;
    private String date_fin;
    private int age_min;
    private int age_max;
    private String date_publication;
    private int auteur;

    public Camp() {
    }

    public Camp(int id, String theme, String description, String logo, String date_debut, String date_fin, int age_min, int age_max, String date_publication, int auteur) {
        this.id = id;
        this.theme = theme;
        this.description = description;
        this.logo = logo;
        this.date_debut = date_debut;
        this.date_fin = date_fin;
        this.age_min = age_min;
        this.age_max = age_max;
        this.date_publication = date_publication;
        this.auteur = auteur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getDate_debut() {
        return date_debut;
    }

    public void setDate_debut(String date_debut) {
        this.date_debut = date_debut;
    }

    public String getDate_fin() {
        return date_fin;
    }

    public void setDate_fin(String date_fin) {
        this.date_fin = date_fin;
    }

    public int getAge_min() {
        return age_min;
    }

    public void setAge_min(int age_min) {
        this.age_min = age_min;
    }

    public int getAge_max() {
        return age_max;
    }

    public void setAge_max(int age_max) {
        this.age_max = age_max;
    }

    public String getDate_publication() {
        return date_publication;
    }

    public void setDate_publication(String date_publication) {
        this.date_publication = date_publication;
    }

    public int getAuteur() {
        return auteur;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Camp{" +
                "id=" + id +
                ", theme='" + theme + '\'' +
                ", description='" + description + '\'' +
                ", logo='" + logo + '\'' +
                ", date_debut='" + date_debut + '\'' +
                ", date_fin='" + date_fin + '\'' +
                ", age_min=" + age_min +
                ", age_max=" + age_max +
                ", date_publication=" + date_publication +
                ", auteur=" + auteur +
                '}';
    }
}
