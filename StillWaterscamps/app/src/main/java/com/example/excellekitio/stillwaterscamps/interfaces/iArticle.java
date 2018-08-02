package com.example.excellekitio.stillwaterscamps.interfaces;

import com.example.excellekitio.stillwaterscamps.entitiees.Article;

import java.util.List;

/**
 * Created by lesli on 19/12/2017.
 */

public interface iArticle {
    public abstract boolean addArticle(String photo,String contenu,String theme_camp);
    public abstract List<Article> allArticle();
}
