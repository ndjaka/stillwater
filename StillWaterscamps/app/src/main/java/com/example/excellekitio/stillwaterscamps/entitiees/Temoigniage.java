package com.example.excellekitio.stillwaterscamps.entitiees;

import java.sql.Date;

/**
 * Created by lesli on 13/12/2017.
 */

public class Temoigniage {
    private int id;
    private int id_user;
    private Date date;
    private String contenu;
    private String titre;

    public Temoigniage() {
    }

    public Temoigniage(int id, int id_user, Date date, String contenu, String titre) {
        this.id = id;
        this.id_user = id_user;
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

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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
        return "Temoigniage{" +
                "id=" + id +
                ", id_user=" + id_user +
                ", date=" + date +
                ", contenu='" + contenu + '\'' +
                ", titre='" + titre + '\'' +
                '}';
    }
}
