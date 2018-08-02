package com.example.excellekitio.stillwaterscamps.entitiees;

import java.sql.Date;

/**
 * Created by lesli on 13/12/2017.
 */

public class Benevolt {
    private int id;
    private String email;
    private String name;
    private String img;
    private int sexe;//*******pourquoi le sexe est un int
    private String pays;
    private Date date_naissance;
    private String apptitude;

    public Benevolt() {
    }

    public Benevolt(int id, String email, String name, String img, int sexe, String pays, Date date_naissance, String apptitude) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.img = img;
        this.sexe = sexe;
        this.pays = pays;
        this.date_naissance = date_naissance;
        this.apptitude = apptitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getSexe() {
        return sexe;
    }

    public void setSexe(int sexe) {
        this.sexe = sexe;
    }

    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getApptitude() {
        return apptitude;
    }

    public void setApptitude(String apptitude) {
        this.apptitude = apptitude;
    }

    @Override
    public String toString() {
        return "Benevolt{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", sexe=" + sexe +
                ", pays='" + pays + '\'' +
                ", date_naissance=" + date_naissance +
                ", apptitude='" + apptitude + '\'' +
                '}';
    }
}
