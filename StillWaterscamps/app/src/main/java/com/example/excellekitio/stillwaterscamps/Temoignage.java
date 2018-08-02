package com.example.excellekitio.stillwaterscamps;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.implement.TemoigniageImp;

import java.sql.Date;

public class Temoignage extends AppCompatActivity {

    private EditText text;
    private EditText titre;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temoignage);

        text=(EditText)findViewById(R.id.contenu);
        titre=(EditText)findViewById(R.id.titre);
        btn = (Button)findViewById(R.id.button_sendTemoignage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        if(TextUtils.isEmpty(text.getText().toString().trim()) && TextUtils.isEmpty(titre.getText().toString().trim())){
            text.setError("le titre ne peut pas etre vide ");
            titre.setError("le contenu ne peut pas etre vide");
        }else{
            try{
                TemoigniageImp imp = TemoigniageImp.getTemoigniageImp();

                boolean b =  imp.insert_temoignage(3,text.getText().toString(),titre.getText().toString());
                ModelCamp.getListTemoigniage().add(new Temoigniage(1,3,new Date(new java.util.Date().getTime()),text.getText().toString(),titre.getText().toString()));
                ModelCamp.getListTemoigniage().notify();

                if(!b ){

                    Toast.makeText(Temoignage.this,"temoignage enregistrer ...merci",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(Temoignage.this,"temoignage non inserer",Toast.LENGTH_LONG).show();
                }
            }catch (Exception e){
                Log.e("Erreur de conneixione","ererrrrrrr");
                Toast.makeText(Temoignage.this,"erreur de connexion veuillez reesayer",Toast.LENGTH_LONG).show();
            }

        }
            }
        });
    }
}
