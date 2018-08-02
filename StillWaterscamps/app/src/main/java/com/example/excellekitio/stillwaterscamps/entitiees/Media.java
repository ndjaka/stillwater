package com.example.excellekitio.stillwaterscamps.entitiees;

/**
 * Created by lesli on 13/12/2017.
 */

public class Media {
    private int id;
    private int id_camp;
    private String date;
    private String contenu;
    private String titre;

    public Media() {
    }

    public Media(int id, int id_camp, String date, String contenu, String titre) {
        this.id = id;
        this.id_camp = id_camp;
        this.date = date;
        this.contenu = contenu;
        this.titre = titre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_camp() {
        return id_camp;
    }

    public void setId_camp(int id_camp) {
        this.id_camp = id_camp;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", id_camp=" + id_camp +
                ", date='" + date + '\'' +
                ", contenu='" + contenu + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
