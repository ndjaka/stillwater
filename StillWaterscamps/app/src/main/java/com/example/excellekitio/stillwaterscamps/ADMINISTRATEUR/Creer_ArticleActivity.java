package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Article;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.implement.ArticleImp;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class Creer_ArticleActivity extends AppCompatActivity {

    private EditText contenu;
    private ImageView photo;
    private Spinner theme;
    private  Button send;
    String selectedFile;
    Uri pictureUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer__article);

        contenu = (EditText)findViewById(R.id.contenu_article);
        photo = (ImageView)findViewById(R.id.img_article);
        theme = (Spinner)findViewById(R.id.theme_article);
        ArrayList<String> themes = new ArrayList<String>();

        for(Camp c : ModelCamp.getListCamp()){
            themes.add(c.getTheme());
        }
        ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,themes);
        theme.setAdapter(adapter);

        send = (Button)findViewById(R.id.btn_pulier);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long  timeState  = new Date().getTime();
                ArticleImp articleImp = ArticleImp.getArticleImp();
                articleImp.addArticle(new File(selectedFile).getName()+timeState,contenu.getText().toString(),theme.getSelectedItem().toString());
                ArticleImp.getArticleImp().addArticle(new File(selectedFile).getName()+timeState,contenu.getText().toString(),theme.getSelectedItem().toString());
            }
        });
        telechargerImageProfile();
    }
    int RESULT_LOAD_IMG = 99;
    private void telechargerImageProfile() {
        photo.setOnClickListener(new View.OnClickListener() {
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
                    photo.setImageURI(data.getData());
                pictureUri = data.getData();
            } else {
                //ToastDisplayer.displayToast(this, "Aucune photo de profil selectionnée !");
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Dysfonctionnement...",Toast.LENGTH_SHORT);
        }
    }
}
