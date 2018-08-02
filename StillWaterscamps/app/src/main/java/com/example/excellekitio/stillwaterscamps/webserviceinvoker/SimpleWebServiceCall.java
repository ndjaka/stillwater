package com.example.excellekitio.stillwaterscamps.webserviceinvoker;

import android.content.SharedPreferences;
import android.util.Log;

import org.ksoap2.HeaderProperty;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dave on 15/10/2017.
 */

public class SimpleWebServiceCall {

    String methodName;
    String[] cles;
    Object[] valeurs;
    Class[] classesObjets;
    String SOAP_ACTION;
    public static SharedPreferences preferences;
    public static String set_cookie_key = null;
    Object resultat = null;

    public SimpleWebServiceCall(Class[] classesObjets, Object[] valeurs, String[] cles, String methodName) {

        this.classesObjets = classesObjets;
        this.valeurs = valeurs;
        this.cles = cles;
        this.methodName = methodName;
        SOAP_ACTION = CallWebService.NAMESPACE + methodName;
    }

    public SimpleWebServiceCall() {
    }

    public Object doInBackgroundCommon(){

        Log.i("TAG", "Invocation du service simple...");

        SharedPreferences.Editor editor = null;

        if (preferences != null)
            editor = preferences.edit();

        PropertyInfo pi;
        SoapObject request = new SoapObject(CallWebService.NAMESPACE, methodName);
        if (classesObjets != null) {
            for (int i = 0; i < cles.length; i++) {
                    /*Pour ajouter un paramètre à la requête nous utilisons un objet de type PropertyInfo qui contiendra la
                    clé du paramètre, sa valeur et son type. */
                pi = new PropertyInfo();
                pi.setName(cles[i]);//Nom du paramètre
                pi.setValue(valeurs[i]);//Valeur du paramètre
                pi.setType(classesObjets[i]);//TypePack du paramètre
                request.addProperty(pi);//Ajout du paramètre à la requête
            }
        }
        try {

            SoapSerializationEnvelope soapEnvelope = new
                    SoapSerializationEnvelope(SoapEnvelope.VER11);
                /*Si le service web renvoie des objets complexes comme un Customer on peut indiquer à
                l’application le type de donnée en retour attend grâce à l’instruction :*/
            //soapEnvelope.addMapping(NAMESPACE, "Customer",new Customer().getClass());
            soapEnvelope.setOutputSoapObject(request);
            HttpTransportSE transport = new HttpTransportSE(CallWebService.URL);

            List<HeaderProperty> headerList = new ArrayList<HeaderProperty>();

            if (preferences != null && set_cookie_key != null) {
                    /*////////////System.out.println("Les preferences ne sont pas nulles !!!");
                    ////////////System.out.println(set_cookie_key+" = "+ preferences.getString(set_cookie_key, ""));*/
                HeaderProperty headerPropertyObj = new HeaderProperty("cookie", preferences.getString(set_cookie_key, ""));
                headerList.add(headerPropertyObj);
            }

            //HttpTransportSE transport = new HttpTransportSE(URL, 60000);

            headerList = transport.call(SOAP_ACTION, soapEnvelope, headerList);

            if (methodName.equalsIgnoreCase("connexion") && preferences != null) {
                for (Object header : headerList) {
                    HeaderProperty headerProperty = (HeaderProperty) header;
                    String headerKey = headerProperty.getKey();
                    String headerValue = headerProperty.getValue();
                    //////////////System.out.println("Nouvel element d'entete : "+headerKey +" = " + headerValue);
                    if (headerKey != null && headerKey.equalsIgnoreCase("set-cookie")) {
                        set_cookie_key = headerKey;
                        editor.putString(headerKey, headerValue);
                    }
                }
                editor.commit();
            }

            resultat = soapEnvelope.getResponse();
            transport.getServiceConnection().disconnect();

        } catch (Exception ex) {
            //Log.e("TAG", " resultat:"+resultat.toString());
            ex.printStackTrace();
            //Log.i("TAG", "doInBackground"+ ex.getMessage());
            //Log.e("TAG", "Error: " + ex.getMessage()+" resultat:"+resultat.toString());
        }
        return resultat;
    }
}

