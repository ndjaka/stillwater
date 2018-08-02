package com.example.excellekitio.stillwaterscamps;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.media.RingtoneManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.example.excellekitio.stillwaterscamps.entitiees.Benevolt;
import com.example.excellekitio.stillwaterscamps.entitiees.User;
import com.example.excellekitio.stillwaterscamps.implement.BenevoltImp;
import com.example.excellekitio.stillwaterscamps.implement.UserImp;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.ksoap2.serialization.SoapObject;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.R.attr.data;
import static android.R.attr.name;

public class compte extends AppCompatActivity {
    private EditText nom;
    private EditText adresseEmail;
    private EditText pays;
    private EditText sexe;
    private EditText date_nais;
    private TextView text;
    private String selectedFile;

    Button button_creerCompte;
    Uri pictureUri;

    ImageView imageUser;

    Button  b;
    Intent intent = null;
    Context ctx;

    String nom_u,adresseEmail_u,pays_u,date_nais_u,sexe_u,Myfile;
    int sex=1;
    NotificationManager notificationManager;
   Spinner activite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compte_benevole);
        activite =(Spinner) findViewById(R.id.activite);
        RadioGroup radiosGroup = (RadioGroup) findViewById(R.id.radiogroup);
        nom = (EditText) findViewById(R.id.nom_text);
        adresseEmail = (EditText) findViewById(R.id.adresseEmail_text);
        pays = (EditText) findViewById(R.id.pays_text);
        date_nais=(EditText) findViewById(R.id.date_nais);
        text=(TextView) findViewById(R.id.activite_label) ;
        sexe = (EditText) findViewById(R.id.sexe_text);
        String[] activites={"cuisine","mecanique","netoyage","paintball","danse","natation"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,activites);
        activite.setAdapter(adapter);
        imageUser = (ImageView) findViewById(R.id.logo);
        button_creerCompte = (Button)findViewById(R.id.button_creerCompte);
        b = (Button) findViewById(R.id.button_creerCompte);


        radiosGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId){
                    case R.id.radioButton1:
                        activite.setVisibility(View.INVISIBLE);
                        text.setVisibility(View.INVISIBLE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Enregistre_user();
                            }
                        });
                        break;
                    case R.id.radioButton2:
                        text.setVisibility(View.VISIBLE);
                        activite.setVisibility(View.VISIBLE);
                        b.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Enregistre_benevolt();
                            }
                        });
                        break;
                }
            }
        });


        this.alloacatememory();
       // this.setEvents();
        telechargerImageProfile();

    }
//    private void setEvents() {
//        b.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                intent = new Intent(ctx, NotificationView.class);
//
//                PendingIntent pendingIntent = PendingIntent.getActivity
//                        (ctx, (int) System.currentTimeMillis(), intent, 0);
//
//                long[] vv = {500, 1000};
//                Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//                // you need to insert your image into drawable folder
//                Bitmap largeicon = BitmapFactory.decodeResource(getResources(), R.drawable.businessman);
//
//                Notification notification = new Notification.Builder(ctx)
//                        .setContentTitle("Creation du compte")
//                        .setContentText("Sucess de la creation.")
//                        .setSmallIcon(R.mipmap.ic_launcher)
//                        .setLargeIcon(largeicon)
//                        .setSound(uri)
//                        .setAutoCancel(true)
//                        .setFullScreenIntent(pendingIntent, true)
//                        .setVibrate(vv)
//                        .setWhen((long) System.currentTimeMillis())
//                        .setContentIntent(pendingIntent)
//                        .build();
//
//                notificationManager.notify(0, notification);
//            }
//        });
//
//    }
    private void alloacatememory() {

        ctx = this;
        b = (Button) findViewById(R.id.button_creerCompte);
        notificationManager =
                (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

    }

    private void telechargerImageProfile() {
        imageUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 99);
            }
        });
    }
    public void profil(View view ){
        intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 99);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 99 && data != null) {
            //set image for share
            imageUser.setImageURI(data.getData());

            //store image into Uri for send
            pictureUri = data.getData();




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

        }
    }
    private void Save(){
        adresseEmail_u=adresseEmail.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences(Myfile,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor= sharedPreferences.edit();
        editor.putString("name",adresseEmail_u);
        editor.commit();
        Log.e("donnees"+name,adresseEmail_u);
    }

    public String getEmail_Preference(){
        SharedPreferences sharedPreferences = getSharedPreferences(Myfile,Context.MODE_PRIVATE);
       String defaultValue ="Default";
        String name = sharedPreferences.getString("name",defaultValue);
        Log.e("email",name);
        return name;
    }

    public void Enregistre_user(){
        nom_u=nom.getText().toString();
        adresseEmail_u=adresseEmail.getText().toString();
        sexe_u = sexe.getText().toString();
        pays_u=pays.getText().toString();
        date_nais_u=date_nais.getText().toString();
        if(sexe_u == "masculin"){
            sex=Integer.parseInt(sexe_u);

        }else if(sexe_u == "feminin"){
            sex = Integer.parseInt(sexe_u);

        }

        try {
            UserImp userImp = new UserImp();
            userImp.Insertion_user(adresseEmail_u,nom_u,selectedFile,sex,pays_u,date_nais_u);
            Save();
            getEmail_Preference();
             Log.e("donnees",selectedFile);


        }catch (Exception e){
            e.printStackTrace();
        }


    }
    public void Enregistre_benevolt(){
        nom_u=nom.getText().toString();
        adresseEmail_u=adresseEmail.getText().toString();
        sexe_u = sexe.getText().toString();
        pays_u=pays.getText().toString();
        date_nais_u=date_nais.getText().toString();

        BenevoltImp benevoltImp =new BenevoltImp();
        Log.e("données",activite.getSelectedItem().toString());
        boolean result;
        result=benevoltImp.Insertion_benevolt(adresseEmail_u,nom_u,selectedFile,sexe_u,pays_u,date_nais_u,activite.getSelectedItem().toString());
        Log.e("resultat",""+result);
        if (result==false){
            Toast.makeText(this, "Succes ", Toast.LENGTH_LONG).show();
        }
    }

}


