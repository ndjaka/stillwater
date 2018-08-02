package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Article;
import com.example.excellekitio.stillwaterscamps.interfaces.iArticle;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 19/12/2017.
 */

public class ArticleImp implements iArticle {

    private  static  ArticleImp articleImp;

    private  ArticleImp(){

    }
    public static ArticleImp getArticleImp() {

        if(articleImp==null){

            articleImp = new ArticleImp();
        }
        return articleImp;
    }

    @Override
    public boolean addArticle(String photo, String contenu, String theme_camp) {
        boolean enregistrement_v = false;

        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "addArticle",
                        new String[]{"photo","contenu","theme_camp"},
                        new Object[]{photo,contenu,theme_camp},
                        new Class[]{String.class,String.class,String.class}
                )
        );


        if (resultat != null) {
            enregistrement_v = true;
        }

        return enregistrement_v;
    }

    @Override
    public List<Article> allArticle() {

        List<Article> articles = new ArrayList<Article>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allArticle",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {

                Article article = new Article();

                SoapObject soapObject = (SoapObject) resultat.get(i);

                article.setContenu(soapObject.getPrimitivePropertyAsString("contenu"));
                article.setPhoto(soapObject.getPrimitivePropertyAsString("photo"));
                article.setTheme(soapObject.getPrimitivePropertyAsString("theme"));

                articles.add(article);
            }
        }
        return articles;
    }
}
