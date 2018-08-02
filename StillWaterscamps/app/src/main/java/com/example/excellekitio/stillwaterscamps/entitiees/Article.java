/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.excellekitio.stillwaterscamps.entitiees;

/**
 *
 * @author lesli
 */
public class Article {
    private String photo;
    private String contenu;
    private String theme;

    public Article() {
    }

    public Article(String photo, String contenu, String theme) {
        this.photo = photo;
        this.contenu = contenu;
        this.theme = theme;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
    
    
    
}
