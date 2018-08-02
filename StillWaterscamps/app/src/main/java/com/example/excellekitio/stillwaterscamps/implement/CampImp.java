package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.entitiees.User;
import com.example.excellekitio.stillwaterscamps.interfaces.iCamp;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class CampImp implements iCamp {
    private  static CampImp campImp;
    private CampImp(){

    }
    public static CampImp getCampImp(){
        if(campImp == null){
            campImp = new CampImp();
        }
        return campImp;
    }
    @Override
    public List<Camp> allCamp() {

        List<Camp> camps = new ArrayList<Camp>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allCamp",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                Camp camp = new Camp();
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                camp.setAge_max(Integer.parseInt(soapObject.getPrimitivePropertyAsString("age_max")));
                camp.setAge_min(Integer.parseInt(soapObject.getPrimitivePropertyAsString("age_min")));
                camp.setAuteur(Integer.parseInt(soapObject.getPrimitivePropertyAsString("auteur")));
                camp.setDate_debut(soapObject.getPrimitivePropertyAsString("date_debut"));
                camp.setDate_fin(soapObject.getPrimitivePropertyAsString("date_fin"));
                camp.setDate_publication(soapObject.getPrimitivePropertyAsString("date_publication"));
                camp.setDescription(soapObject.getPrimitivePropertyAsString("description"));
                camp.setLogo(soapObject.getPrimitivePropertyAsString("logo"));
                camp.setTheme(soapObject.getPrimitivePropertyAsString("theme"));
                camp.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                camps.add(camp);

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return camps;
    }

    @Override
    public Camp creerCamp(String theme, String description, String date_debut, String date_fin, int age_min, int age_max, int auteur,String logo) {
        Camp camp = new Camp();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "creerCamp",
                        new String[]{"theme", "description","date_debut","date_fin","age_min","age_max","auteur","logo"},
                        new Object[]{theme, description,date_debut,date_fin,age_min,age_max,auteur,logo},
                        new Class[]{String.class, String.class,String.class,String.class,Integer.class,Integer.class,Integer.class,String.class}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                camp.setAge_max(Integer.parseInt(soapObject.getPrimitivePropertyAsString("age_max")));
                camp.setAge_min(Integer.parseInt(soapObject.getPrimitivePropertyAsString("age_min")));
                camp.setAuteur(Integer.parseInt(soapObject.getPrimitivePropertyAsString("auteur")));
                camp.setDate_debut(soapObject.getPrimitivePropertyAsString("date_debut"));
                camp.setDate_fin(soapObject.getPrimitivePropertyAsString("date_fin"));
                camp.setDate_publication(soapObject.getPrimitivePropertyAsString("date_publication"));
                camp.setDescription(soapObject.getPrimitivePropertyAsString("description"));
                camp.setLogo(soapObject.getPrimitivePropertyAsString("logo"));
                camp.setTheme(soapObject.getPrimitivePropertyAsString("theme"));
                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return camp;
    }

    @Override
    public boolean delateCamp(int id_camp) {

        boolean enregistrement_v = false;

        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "validerDemande",
                        new String[]{"id_camp"},
                        new Object[]{ id_camp},
                        new Class[]{Integer.class}
                )
        );


        if (resultat != null) {
           enregistrement_v = true;
        }

        return enregistrement_v;
    }


    @Override
    public boolean updateCamp(int id, String theme, String description, String date_debut, String date_fin, int age_min, int age_max, int auteur) {
        return false;
    }
}
