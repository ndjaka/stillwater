package com.example.excellekitio.stillwaterscamps.entitiees;

import java.util.Date;

/**
 * Created by ndjaka on 18/12/2017.
 */

public class Alldemande {
    private Date date;
    private String Email;
    private int id_admin,id_camp;
    private boolean valider;

    public Alldemande() {
    }

    public Alldemande(String email, int id_admin, int id_camp, boolean valider,Date date) {
        Email = email;
        this.id_admin = id_admin;
        this.id_camp = id_camp;
        this.valider = valider;
        this.date=date;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
    }

    public int getId_camp() {
        return id_camp;
    }

    public void setId_camp(int id_camp) {
        this.id_camp = id_camp;
    }

    public boolean isValider() {
        return valider;
    }

    public void setValider(boolean valider) {
        this.valider = valider;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Alldemande that = (Alldemande) o;

        if (id_admin != that.id_admin) return false;
        if (id_camp != that.id_camp) return false;
        if (valider != that.valider) return false;
        return Email != null ? Email.equals(that.Email) : that.Email == null;

    }

    @Override
    public int hashCode() {
        int result = Email != null ? Email.hashCode() : 0;
        result = 31 * result + id_admin;
        result = 31 * result + id_camp;
        result = 31 * result + (valider ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Alldemande{" +
                "Email='" + Email + '\'' +
                ", id_admin=" + id_admin +
                ", id_camp=" + id_camp +
                ", valider=" + valider +
                '}';
    }
}
