package com.example.excellekitio.stillwaterscamps.implement;

import android.app.ProgressDialog;
import android.util.Log;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.CreerCamp;
import com.example.excellekitio.stillwaterscamps.Tmedia;
import com.example.excellekitio.stillwaterscamps.entitiees.Media;
import com.example.excellekitio.stillwaterscamps.filemanager.FileUpload;
import com.example.excellekitio.stillwaterscamps.filemanager.FileUploadByEssam;
import com.example.excellekitio.stillwaterscamps.interfaces.iMedia;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;

import org.ksoap2.serialization.SoapObject;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import static com.example.excellekitio.stillwaterscamps.webserviceinvoker.ExempleAppelServiceWeb.getVectorFromWebServiceResponse;

/**
 * Created by lesli on 13/12/2017.
 */

public class MediaImp implements iMedia {

    private static MediaImp instance ;
    Media media ;

    private MediaImp()
    {
        media = new Media();
    }

    public  static MediaImp getInstance()
    {
        if (instance==null)
        {
            instance= new MediaImp();
        }
        return  instance;
    }
    @Override

    public void addMedia(ArrayList<Tmedia> tmedias, int id_camp, ProgressDialog progressDialog) {
        Vector resultat  ;
        Long  time ;
        String fileName = "";
        progressDialog.setMessage("Veillez patientez");
        for(int i=0;i<tmedias.size();i++) {
            fileName  = new File(tmedias.get(i).getFilePath()).getName();
            time = Calendar.getInstance().getTimeInMillis();
            String path;
                    if(tmedias.get(i).getTypeMedia().equals("image"))
                    {
                        path="Galerie/"+tmedias.get(i).getTypeMedia()+"@typo__Infinix"+time+"_"+fileName;
                    }else
                    {
                        path=fileName;
                    }
                media =  new Media(0,id_camp,String.valueOf(time),path,fileName);
            resultat = getVectorFromWebServiceResponse(
                    new CallWebService().callWebService(
                            "addMedia",
                            new String[]{"id_camp", "titre", "contenu"},
                            new Object[]{id_camp, media.getTitre(), media.getContenu()},
                            new Class[]{Integer.class, String.class, String.class}
                    )
            );

            if(tmedias.get(i).getTypeMedia().equals("image"))
            {
                FileUploadByEssam.getInstance().uploadFile(tmedias.get(i).getFilePath(),tmedias.get(i).getTypeMedia()+"@typo__Infinix"+time+"_"+fileName, "\\Galerie");
            }else
            {
                progressDialog.setMessage("veillez patientez");
                progressDialog.setCancelable(false);
                new FileUpload().uploadFile(tmedias.get(i).getFilePath(), progressDialog);
            }

        }
    }

    @Override
    public List<Media> allMediaByCamp(int idCamp) {
        return null;
    }

    @Override
    public Media find(int idMedia) {
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "findMedia",
                        new String[]{"idMedia"},
                        new Object[]{idMedia},
                        new Class[]{Integer.class}
                )
        );


        if (resultat != null) {
            SoapObject soapObject ;
                soapObject = (SoapObject) resultat.get(0);
                media =  new Media();
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                    media.setContenu(soapObject.getPrimitivePropertyAsString("contenu"));
                    media.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                    media.setTitre(soapObject.getPrimitivePropertyAsString("titre"));
                    media.setId_camp(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp")));
                    media.setDate(soapObject.getPrimitivePropertyAsString("date"));
            ;

        }
        return media;
    }

    public List<Media> allMediaByCamp(int idCamp,String  type) {
        List<Media> medias = new ArrayList<Media>();
        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allMediaByCamp",
                        new String[]{"id_camp"},
                        new Object[]{idCamp},
                        new Class[]{Integer.class}
                )
        );
        if (type.equals("video"))
        {   if (resultat != null) {
            SoapObject soapObject;
            for (int i = 0; i < resultat.size(); i++) {
                soapObject = (SoapObject) resultat.get(i);
                media= new Media();
                    media.setContenu(soapObject.getPrimitivePropertyAsString("contenu"));
                    media.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                    media.setTitre(soapObject.getPrimitivePropertyAsString("titre"));
                    media.setId_camp(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp")));
                    media.setDate(soapObject.getPrimitivePropertyAsString("date"));
                    medias.add(media);
            }
        }
        }else {
        if (resultat != null) {
            SoapObject soapObject ;
            for (int i = 0; i < resultat.size(); i++) {
                soapObject = (SoapObject) resultat.get(i);
                // SoapPrimitive soapPrimitive = (SoapPrimitive) resultat.get(i);
                media= new Media();
                String [] extension = soapObject.getPrimitivePropertyAsString("contenu").split("@typo__Infinix");
                if (extension.length>1)
                {
                    if(extension[0].contains(type)) {
                        media.setContenu(soapObject.getPrimitivePropertyAsString("contenu"));
                        media.setId(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id")));
                        media.setTitre(soapObject.getPrimitivePropertyAsString("titre"));
                        media.setId_camp(Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp")));
                        media.setDate(soapObject.getPrimitivePropertyAsString("date"));
                        medias.add(media);
                    }

                }

                //Toast.makeText(MainActivity, "resultat renvoye par le service web est : "+j, Toast.LENGTH_LONG).show();
            }
        }
        }
        return medias;
    }
    public List<Media> lastsImgByCamp(String type) {
        List<Media> medias = new ArrayList<Media>();
        ArrayList<Integer> integerArrayList =  new ArrayList<>();

        Vector resultat = getVectorFromWebServiceResponse(
                new CallWebService().callWebService(
                        "allMedia",new String[]{},
                        new Object[]{},
                        new Class[]{}
                )
        );

        if (type.equals("video"))
        {   if (resultat != null) {
            SoapObject soapObject;
            for (int i = 0; i < resultat.size(); i++) {
                soapObject = (SoapObject) resultat.get(i);
                int id_camp = Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp"));
                if (!integerArrayList.contains(id_camp)) {
                    List<Media> temps = allMediaByCamp(id_camp, type);
                    if (temps.size() > 0) {
                        medias.add(temps.get(temps.size() - 1));
                    }
                    integerArrayList.add(id_camp);
                }
            }
        }
        }else {
            if (resultat != null) {
                SoapObject soapObject;
                for (int i = 0; i < resultat.size(); i++) {
                    soapObject = (SoapObject) resultat.get(i);
                    String[] extension = soapObject.getPrimitivePropertyAsString("contenu").split("@typo__Infinix");

                    if (extension.length > 1) {
                        if (extension[0].contains(type)) {
                            int id_camp = Integer.parseInt(soapObject.getPrimitivePropertyAsString("id_camp"));
                        if (!integerArrayList.contains(id_camp)) {
                            List<Media> temps = allMediaByCamp(id_camp, type);
                            if (temps.size() > 0) {
                                medias.add(temps.get(temps.size() - 1));
                            }
                            integerArrayList.add(id_camp);
                        }
                    }
                    }

                }
            }
        }
        return medias;
    }

}
