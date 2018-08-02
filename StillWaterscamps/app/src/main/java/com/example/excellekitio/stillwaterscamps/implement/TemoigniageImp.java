package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.interfaces.iTemoigniage;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class TemoigniageImp implements iTemoigniage {

    private  static TemoigniageImp temoigniageImp;
    private TemoigniageImp(){

    }
    public static TemoigniageImp getTemoigniageImp(){
        if(temoigniageImp == null){
            temoigniageImp = new TemoigniageImp();
        }
        return temoigniageImp;
    }


    @Override
    public List<Temoigniage> allTemoigniage() {


        List<Temoigniage> Temoigniages = new ArrayList<Temoigniage>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allTemoigniage",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                Temoigniage temoigniage = new Temoigniage();
                SoapObject soapObject = (SoapObject) resultat.get(i);

                temoigniage.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                temoigniage.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_user")));
                temoigniage.setContenu(soapObject.getPrimitivePropertyAsString("contenu"));
                temoigniage.setTitre(soapObject.getPrimitivePropertyAsString("titre"));

                Temoigniages.add(temoigniage);

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return Temoigniages;
    }

    @Override
    public boolean insert_temoignage(int id_user, String contenu, String titre) {
        Boolean val = null;
        SoapPrimitive soapPrimitive;
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "insert_temoignage",
                        new String[]{"id_user", "contenu","titre"},
                        new Object[]{id_user,contenu,titre},
                        new Class[]{Integer.class, String.class,String.class}
                )
        );
        if(resultat!=null) {

          val  = Boolean.parseBoolean(resultat.toString());
        }

            if(val==true){
                return true;
            }else {
                return false;
            }
//        boolean insertion_v = false;
//        if (resultat != null) {
//            insertion_v = true;
//        }else {
//            insertion_v = false;
//        }
//        return insertion_v;
    }
}
