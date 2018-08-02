package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.app.NotificationManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.filemanager.FileUpload;
import com.example.excellekitio.stillwaterscamps.implement.CampImp;

import java.io.File;
import java.util.Date;

public class CreerCamp extends AppCompatActivity {

    Button  b;
    private int id_auteur = 1;
    String selectedFile;

    Context ctx;

    NotificationManager notificationManager;
    private EditText theme;
    private EditText description;
    private EditText date_debut;
    private EditText date_fin;
    private EditText age_min;
    private EditText age_max;
    private EditText auteur;
    private TextView texImage;

    Intent intent = null;
    Button button_creerCamp;
    Uri pictureUri;
    ImageView logo;
    ModelCamp modelCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_camp);

        theme = (EditText) findViewById(R.id.theme_text);
        description = (EditText) findViewById(R.id.description_text);
        date_debut = (EditText) findViewById(R.id.date_debut_text);
        date_fin = (EditText) findViewById(R.id.date_fin_text);
        age_min = (EditText) findViewById(R.id.age_min_text);
        age_max = (EditText) findViewById(R.id.age_max_text);
        auteur = (EditText) findViewById(R.id.auteur_text);
        logo = (ImageView) findViewById(R.id.logo);
        texImage = (TextView) findViewById(R.id.texte_label);

        modelCamp = new ModelCamp();

        b= (Button)findViewById(R.id.button_creerCamp);

        b.setOnClickListener(new View.OnClickListener() {
            Camp camp = new Camp();
            @Override
            public void onClick(View v) {

                CampImp campImp = CampImp.getCampImp();

                try {


                    long  timeState  = new Date().getTime();
                    boolean b = validercreation();

                    if(b){

                        camp = campImp.creerCamp(
                                theme.getText().toString(),
                                description.getText().toString(),
                                date_debut.getText().toString(),
                                date_fin.getText().toString(),
                                Integer.parseInt(age_min.getText().toString()),
                                Integer.parseInt(age_max.getText().toString()),
                                id_auteur,
                                new File(selectedFile).getName()+timeState
                        );

                    }




                    if(camp.getId()!=0){

                        Toast.makeText(getApplication(),"camp creer"+camp.toString(),Toast.LENGTH_LONG);

                        ModelCamp.getListCamp().add(camp);

                        ProgressDialog mProgressDialog = ProgressDialog.show(CreerCamp.this,"","Veuillez patienter", true);
                        mProgressDialog.setCancelable(false);

                        new FileUpload().uploadFile(selectedFile, mProgressDialog);
                        Toast.makeText(getApplication(),"enregistrement du camp terminer",Toast.LENGTH_LONG);

                        Intent intent = new Intent(CreerCamp.this,listedesarticles.class);
                        startActivity(intent);

                    }

                }catch (Exception e){
                    Log.e("E",e.getMessage());
                }

            }

        });
        telechargerImageProfile();

    }


    int RESULT_LOAD_IMG = 99;
    private void telechargerImageProfile() {
        logo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                // Start the Intent
                startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {

                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgPath = cursor.getString(columnIndex);
                selectedFile = imgPath;
                ////System.out.println("Fichier selectionne  : " + selectedFile);
                cursor.close();
                if (!selectedFile.substring(selectedFile.lastIndexOf("/") + 1).matches("[a-zA-Z0-9 _.-]*")) {
                    Toast.makeText(this, "Pas de caracteres speciaux\ndans le nom du fichier !",Toast.LENGTH_SHORT);
                    return;
                }
                else
                    logo.setImageURI(data.getData());
                    pictureUri = data.getData();
            } else {
                //ToastDisplayer.displayToast(this, "Aucune photo de profil selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Dysfonctionnement...",Toast.LENGTH_SHORT);
        }
    }
    public boolean validercreation(){

        boolean b = true;

        if(theme.getText().toString().equalsIgnoreCase("")){
            theme.setError("not empty");
            b = false;
        }

        if(date_debut.getText().toString().isEmpty() || date_fin.getText().toString().isEmpty()){
            try {

                 new java.sql.Date(new Date(date_debut.getText().toString()).getTime());
                new java.sql.Date(new Date(date_fin.getText().toString()).getTime());

            }catch (Exception e){
                date_debut.setError("format date 2017-01-03");
                date_fin.setError("format date 2017-01-03");
                b = false;
            }
        }

        try{
            Integer.parseInt(age_min.getText().toString());
            Integer.parseInt(age_max.getText().toString());
            Integer.parseInt(auteur.getText().toString());

        }catch (Exception e){
            age_min.setError("number");
            age_max.setError("number");
            auteur.setError("number");
            b = false;
        }

        if(selectedFile.isEmpty()){
            texImage.setError("check Imgage");
            b = false;
        }

        return b;
    }
}
