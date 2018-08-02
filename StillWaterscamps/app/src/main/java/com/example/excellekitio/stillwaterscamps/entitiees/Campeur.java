package com.example.excellekitio.stillwaterscamps.entitiees;

import java.sql.Date;

/**
 * Created by lesli on 13/12/2017.
 */

public class Campeur {
    private String email_user;
    private int id_camp;
    private int id_admin;
    private Date date;
    private boolean valider;

    public Campeur() {
    }

    public Campeur(String email_user, int id_camp, int id_admin, Date date, boolean valider) {
        this.email_user = email_user;
        this.id_camp = id_camp;
        this.id_admin = id_admin;
        this.date = date;
        this.valider = valider;
    }

    public String getEmail_user() {
        return email_user;
    }

    public void setEmail_user(String email_user) {
        this.email_user = email_user;
    }

    public int getId_camp() {
        return id_camp;
    }

    public void setId_camp(int id_camp) {
        this.id_camp = id_camp;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    @Override
    public String toString() {
        return "Campeur{" +
                "email_user='" + email_user + '\'' +
                ", id_camp=" + id_camp +
                ", id_admin=" + id_admin +
                ", date=" + date +
                ", valider=" + valider +
                '}';
    }
}
