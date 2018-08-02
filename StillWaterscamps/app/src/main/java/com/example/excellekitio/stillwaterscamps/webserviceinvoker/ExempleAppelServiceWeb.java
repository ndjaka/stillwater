package com.example.excellekitio.stillwaterscamps.webserviceinvoker;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;

import java.util.Vector;

/**
 * Created by dave on 20/11/2017.
 */

public class ExempleAppelServiceWeb {

    public static Vector getVectorFromWebServiceResponse(Object result) {

        Vector resultat = new Vector();
        try {
            resultat = (Vector) result;
        } catch (ClassCastException e) {
            e.printStackTrace();
            try {
                resultat.add((SoapObject) result);
            } catch (ClassCastException ex) {
                ex.printStackTrace();
                try {
                    resultat.add((SoapPrimitive) result);
                } catch (Exception exc) {
                    exc.printStackTrace();
                    resultat = null;
                }
            }
        }
        return resultat;
    }

    public static int main() {

        String password = "eugene", name = "15T2306";
        int j = 0;
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
               j = Integer.parseInt(soapObject.getPrimitivePropertyAsString("id"));

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        return j;
    }
}
