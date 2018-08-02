package com.example.excellekitio.stillwaterscamps.implement;

import com.example.excellekitio.stillwaterscamps.entitiees.Media;
import com.example.excellekitio.stillwaterscamps.entitiees.User;
import com.example.excellekitio.stillwaterscamps.interfaces.iUser;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class UserImp implements iUser {
    @Override
    public boolean Insertion_user(String email, String name, String img, int sexe, String pays, String date_naissance) {
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "Insertion_user",
                        new String[]{"email", "name","img","sexe","pays","date_naissance"},
                        new Object[]{email,name,img,sexe,pays,date_naissance},
                        new Class[]{String.class, String.class,String.class,Integer.class,String.class,String.class}
                )
        );

        boolean insertion_v = false;
        if (resultat != null) {
            insertion_v = true;
        }else {
            insertion_v = false;
        }
        return insertion_v;
    }


    @Override
    public List<User> allUser() {
        User user = new User();
        List<User> users = new ArrayList<User>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allUser",
                        new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );


        if (resultat != null) {
            for (int i = 0; i < resultat.size(); i++) {
                SoapObject soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                user.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                user.setEmail(soapObject.getPrimitivePropertyAsString("email"));
                user.setName(soapObject.getPrimitivePropertyAsString("name"));
                user.setPays(soapObject.getPrimitivePropertyAsString("pays"));
                user.setSexe(Integer.parseInt(soapObject.getPrimitivePropertyAsString("sexe")));
                user.setDate_naissance(soapObject.getPrimitivePropertyAsString("date_naissance"));
                user.setImg(soapObject.getPrimitivePropertyAsString("img"));
                user.setPassword(soapObject.getPrimitivePropertyAsString("password"));

                users.add(user);

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return users;
    }
}
