package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.net.Uri;
import android.widget.ImageView;

import com.example.excellekitio.stillwaterscamps.entitiees.Article;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeurs;
import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.entitiees.User;
import com.example.excellekitio.stillwaterscamps.implement.ArticleImp;
import com.example.excellekitio.stillwaterscamps.implement.CampImp;
import com.example.excellekitio.stillwaterscamps.implement.CampeurImp;
import com.example.excellekitio.stillwaterscamps.implement.CampeursImp;
import com.example.excellekitio.stillwaterscamps.implement.TemoigniageImp;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by lesli on 14/12/2017.
 */

public class ModelCamp {

    private static List<Camp> listCamp;
    private static List<Campeurs> listparticipant;
    private static List<Campeur> listCampeur;
    private static List<Article> listArticle;
    private static List<Temoigniage> listTemoigniage;

    private static User user;

    public ModelCamp() {

        if(listCamp == null){

            CampImp camp = CampImp.getCampImp();
            listCamp = camp.allCamp();
        }
        if(listTemoigniage == null){

            TemoigniageImp temoigniageImp = TemoigniageImp.getTemoigniageImp();
            listTemoigniage = temoigniageImp.allTemoigniage();
        }


        if(listparticipant == null){

            CampeursImp campeursImp = CampeursImp.getCampeursImp();
            listparticipant = campeursImp.listerCampeurs();
        }

        if(listCampeur == null){
            CampeurImp campeurImp = CampeurImp.getCampeurImp();
            listCampeur = campeurImp.allDemande();
        }
        if(user==null){
            user = new User();
            user.setEmail("kombouyvan@yahoo.fr");
        }

        if(listArticle == null){
            ArticleImp articleImp = ArticleImp.getArticleImp();
            // listArticle = articleImp.allArticle();
        }

    }

    public static List<Article> getListArticle() {
        return listArticle;
    }

    public static void setListArticle(List<Article> listArticle) {
        ModelCamp.listArticle = listArticle;
    }

    public static List<Temoigniage> getListTemoigniage() {
        return listTemoigniage;
    }

    public static void setListTemoigniage(List<Temoigniage> listTemoigniage) {
        ModelCamp.listTemoigniage = listTemoigniage;
    }

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        ModelCamp.user = user;
    }

    public static List<Campeur> getListCampeur() {
        return listCampeur;
    }

    public static void setListCampeur(List<Campeur> listCampeur) {
        ModelCamp.listCampeur = listCampeur;
    }

    public static List<Camp> getListCamp() {
        return listCamp;
    }

    public static void setListCamp(List<Camp> listCamp) {
        ModelCamp.listCamp = listCamp;
    }

    public static List<Campeurs> getListparticipant() {
        return listparticipant;
    }

    public static void setListparticipant(List<Campeurs> listparticipant) {
        ModelCamp.listparticipant = listparticipant;
    }

    public static void setImage(String nomImage, ImageView imageView){

        String url  = CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName=" + nomImage;
        url = url.replace(" ", "%20");

        Uri myUri = Uri.parse(url);
        Picasso.with(imageView.getContext()).load(myUri).centerCrop().fit().into(imageView);
    }
    public static boolean findDemand(String email,int id_camp){

        boolean demande = false;
        for(Campeur c : listCampeur){

            if(c.getEmail_user() == email && c.getId_camp() == id_camp){
                demande = c.isValider();
            }
        }
        return  demande;
    }
    public static String findById(int id){
        String name = "";
        for (Camp c : getListCamp()){
            if (c.getId()==id){
                name = c.getTheme();
                break;
            }
        }
        return name;
    }
}
