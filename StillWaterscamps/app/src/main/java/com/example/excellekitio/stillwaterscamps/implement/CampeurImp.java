package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;
import com.example.excellekitio.stillwaterscamps.entitiees.User;
import com.example.excellekitio.stillwaterscamps.interfaces.iCampeur;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class CampeurImp implements iCampeur {
    private  static CampeurImp campeurImp = null;
    private CampeurImp(){

    }
    public static CampeurImp getCampeurImp(){
        if(campeurImp == null){
            campeurImp = new CampeurImp();
        }
        return campeurImp;
    }
    @Override
    public List<Campeur> allDemande() {

        List<Campeur> campeurs = new ArrayList<Campeur>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allDemande",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                Campeur campeur = new Campeur();
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                campeur.setEmail_user(soapObject.getPrimitivePropertyAsString("email_user"));
                campeur.setId_admin(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_admin")));
                campeur.setValider(Boolean.parseBoolean(soapObject.getPrimitivePropertyAsString("valider")));
                campeur.setId_camp(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp")));
                campeurs.add(campeur);

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return campeurs;
    }

    @Override
    public boolean demande(String email, int id_camp) {

        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "demande",
                        new String[]{"email", "id_camp"},
                        new Object[]{email, id_camp},
                        new Class[]{String.class, String.class}
                )
        );

        boolean demande = false;
        if (resultat != null) {
            demande = true;
        }else {
            demande = false;
        }
        return demande;
    }
}
