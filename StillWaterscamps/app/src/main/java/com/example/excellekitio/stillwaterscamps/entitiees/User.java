package com.example.excellekitio.stillwaterscamps.entitiees;

/**
 * Created by lesli on 13/12/2017.
 */

public class User {
    private int id;
    private String email;
    private String name;
    private String img;
    private int sexe;
    private String pays;
    private String date_naissance;
    private  String password;

    public User() {
    }

    public User(int id, String email, String name, String img, int sexe, String pays, String date_naissance, String password) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.img = img;
        this.sexe = sexe;
        this.pays = pays;
        this.date_naissance = date_naissance;
        this.password = password;
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

    public String getDate_naissance() {
        return date_naissance;
    }

    public void setDate_naissance(String date_naissance) {
        this.date_naissance = date_naissance;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", img='" + img + '\'' +
                ", sexe=" + sexe +
                ", pays='" + pays + '\'' +
                ", date_naissance='" + date_naissance + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
