package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.interfaces.iBenevolt;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class BenevoltImp implements iBenevolt {

    @Override
    public boolean Insertion_benevolt(String email, String name, String img, String sexe, String pays, String date_naissance, String apptitude) {
        Boolean insertion_v = null;
            Vector resultat = getVectorFromWebServiceResponse(
                    new CallWebService().callWebService(
                            "Insertion_benevolt",
                            new String[]{"email", "name","img","sexe","pays","date_naissance","apptitude"},
                            new Object[]{email,name,img,sexe,pays,date_naissance,apptitude},
                            new Class[]{String.class, String.class,String.class,String.class,String.class,String.class,String.class}
                    )

            );
    if (resultat!=null){
        insertion_v = Boolean.parseBoolean(resultat.toString());
    }
    return true;
    }
}
