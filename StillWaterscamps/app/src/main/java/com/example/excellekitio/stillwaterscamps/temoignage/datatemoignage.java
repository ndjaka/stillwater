package com.example.excellekitio.stillwaterscamps.temoignage;

/**
 * Created by excelle kitio on 11/12/2017.
 */
public class datatemoignage {

    private String titre;
    private String contenu;

    public datatemoignage(String titre, String contenu) {
        this.titre = titre;
        this.contenu = contenu;
    }

    public datatemoignage() {
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }
}
