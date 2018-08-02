package com.example.excellekitio.stillwaterscamps;

/**
 * Created by ndjaka on 18/12/2017.
 */

public class Validerdemanderow {
    private String Email;
    private String id_camp;
    private Boolean valider;

    public Validerdemanderow(String email, String id_camp, Boolean valider) {
        Email = email;
        this.id_camp = id_camp;
        this.valider = valider;
    }

    public Validerdemanderow() {
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getId_camp() {
        return id_camp;
    }

    public void setId_camp(String id_camp) {
        this.id_camp = id_camp;
    }

    public Boolean getValider() {
        return valider;
    }

    public void setValider(Boolean valider) {
        this.valider = valider;
    }
}
