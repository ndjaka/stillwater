package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Admin;
import com.example.excellekitio.stillwaterscamps.entitiees.Alldemande;
import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.interfaces.iAdmin;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class AdminImp implements iAdmin {
//    private  static AdminImp adminImp;
//    private AdminImp(){
//
//    }
//    public static AdminImp getAdminImp(){
//        if(adminImp == null){
//            adminImp = new AdminImp();
//        }
//        return adminImp;
//    }
    @Override
    public Admin createAdmin(String password, String name) {
        Admin admin = new Admin();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "createAdmin",
                        new String[]{"password", "name"},
                        new Object[]{password, name},
                        new Class[]{String.class, String.class}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                admin.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                admin.setName(soapObject.getPrimitivePropertyAsString("name"));
                admin.setPassword(soapObject.getPrimitivePropertyAsString("password"));

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return admin;
    }

    @Override
    public Admin connect(String password, String name) {
        Admin admin = new Admin();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "connect",
                        new String[]{"password", "name"},
                        new Object[]{password, name},
                        new Class[]{String.class, String.class}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                admin.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                admin.setName(soapObject.getPrimitivePropertyAsString("name"));
                admin.setPassword(soapObject.getPrimitivePropertyAsString("password"));

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return admin;
    }

    @Override
    public boolean validerDemande(String email_user, int id_camp, int id_admin, Boolean valider, Boolean choix) {
        boolean enregistrement_v = true;

        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "validerDemande",
                        new String[]{"email_user","id_camp","id_admin","valider","choix"},
                        new Object[]{email_user, id_camp,id_admin,valider,choix},
                        new Class[]{String.class,Integer.class,Integer.class,Boolean.class,Boolean.class}
                )
        );


        if (resultat != null) {
            enregistrement_v = true;
        }

        return enregistrement_v;
    }


}
