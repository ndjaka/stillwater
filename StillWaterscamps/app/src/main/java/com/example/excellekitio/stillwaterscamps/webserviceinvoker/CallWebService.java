package com.example.excellekitio.stillwaterscamps.webserviceinvoker;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;


public class CallWebService {

    public static int TIME_OUT = 300000;

    public static final String NAMESPACE ="http://webservice.stillwater.com/";
    public static String IP="192.168.43.44:8080";
    public static String URL = "http://"+IP+"/stillwater_service_web/Stillwater?WSDL";
    public static String URL_UPLOAD_SERVLET = "http://"+IP+"/stillwater_service_web/UploadFileServlet";
    public static String URL_UPLOAD_SERVLET2 = "http://"+IP+"/stillwater_service_web/MediaServlet";
    public static String URL_DOWNLOAD_SERVLET ="http://"+IP+"/stillwater_service_web/DownloadFileServlet";
    public static String URL_DOWNLOAD_SERVLET2 ="http://"+IP+"/stillwater_service_web/fichiers/";


    Object resultat = null;

    public CallWebService() {
    }

    public CallWebService(SharedPreferences preferences) {
        SimpleWebServiceCall.preferences = preferences;
    }


    public Object callWebService(String methodName, String[] cles, Object[] valeurs, Class[] classesObjets) {

        try {
            AsyncCallWS task = new AsyncCallWS(classesObjets, valeurs, cles, methodName);
            task.execute();
            task.get();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return resultat;
    }

    class AsyncCallWS extends AsyncTask {

        String methodName;
        String[] cles;
        Object[] valeurs;
        Class[] classesObjets;
        String SOAP_ACTION;

        public AsyncCallWS(Class[] classesObjets, Object[] valeurs, String[] cles, String methodName) {

            this.classesObjets = classesObjets;
            this.valeurs = valeurs;
            this.cles = cles;
            this.methodName = methodName;
            SOAP_ACTION = NAMESPACE + methodName;
        }

        @Override
        protected void onPreExecute() {
            Log.i("TAG", "onPreExecute");
        }

        @Override
        protected Object doInBackground(Object[] params) {

            return (resultat=new SimpleWebServiceCall(classesObjets,  valeurs, cles, methodName).doInBackgroundCommon());
        }

        //@Override
        protected void onPostExecute(Void result) {

            if (resultat != null) {
                ////////////System.out.println("Le resultat de l'appel n'est pas null !!!");
            } else {
                ////////////System.out.println("Le resultat de l'appel est null !!!");
            }
        }

    }

}