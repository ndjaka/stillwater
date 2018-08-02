package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeurs;
import com.example.excellekitio.stillwaterscamps.interfaces.iCampeurs;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class CampeursImp implements iCampeurs {
    private  static CampeursImp campeursImp;
    private CampeursImp(){

    }
    public static CampeursImp getCampeursImp(){
        if(campeursImp == null){
            campeursImp = new CampeursImp();
        }
        return campeursImp;
    }
    @Override
    public List<Campeurs> listerCampeurs() {

        List<Campeurs> campeurss = new ArrayList<Campeurs>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "listerCampeurs",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                Campeurs campeurs = new Campeurs();
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
               campeurs.setCamp(soapObject.getPrimitivePropertyAsString("camp"));
                campeurs.setCampeur(soapObject.getPrimitivePropertyAsString("campeur"));
                campeurss.add(campeurs);

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return campeurss;
    }
}
