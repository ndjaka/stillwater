package com.example.excellekitio.stillwaterscamps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.example.excellekitio.stillwaterscamps.entitiees.Admin;
import com.example.excellekitio.stillwaterscamps.implement.AdminImp;

import java.security.Principal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Connexion extends AppCompatActivity {
    TextInputEditText txtUserName, txtPassword;
    Button btnLogin;
    String FileName = "myFileAdmin";
    Admin admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        txtUserName = (TextInputEditText) findViewById(R.id.idUserName);
        txtPassword = (TextInputEditText) findViewById(R.id.idPassword);
        btnLogin = (Button) findViewById(R.id.idBtonLogin);

        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
            }
        });
    }
    private void login(){
        boolean b = true;
            AdminImp imp = new AdminImp();


        if (TextUtils.isEmpty(txtUserName.getText().toString().trim()) || TextUtils.isEmpty(txtPassword.getText().toString().trim())){
            txtUserName.setError("ce champ ne peut pas etre vide");
            txtPassword.setError("ce champ ne peut pas etre vide");
            b = false;
        }else if(b){
                try {

                   // String valider ;
                    Intent i;
                    i = new Intent(this,Principale.class);
                    admin = imp.connect(txtPassword.getText().toString(),txtUserName.getText().toString());
                    Toast.makeText(this,""+admin.toString(),Toast.LENGTH_LONG).show();

                    if (admin.getId()!=0){
                        Principale.openFLoating = true;
                      //valider = "bon";
                        Save();
                        Toast.makeText(this,""+admin.toString(),Toast.LENGTH_LONG).show();
                        Log.e("admin",""+admin.toString());


                      //  i.putExtra("open",valider);
                        startActivity(i);
                        finish();

                    }else{
                        //valider = "mauvais";
                        Toast.makeText(this,"impossible de vous connectez ",Toast.LENGTH_LONG).show();
//                        i.putExtra("open",valider);
//                        startActivity(i);
//                        finish();

                    }
                }catch (Exception ex){
                    Log.e("Erreur de connexion",ex.getMessage());
                    Toast.makeText(this, "erreur de connexion reesayez avec les bons parametres", Toast.LENGTH_SHORT).show();
                }


        }
    }

private void Save(){
    int id = admin.getId();
    SharedPreferences preferences = getSharedPreferences(FileName,0);
    SharedPreferences.Editor editor = preferences.edit();
    editor.putInt("id_user",id);
    editor.commit();
    Log.e("id_user_save",id+"");


}

    public int idAdminPref(){
            SharedPreferences preferences = getSharedPreferences("myFileAdmin",0);
            //String datavalue = "DefaultName";
            int id = preferences.getInt("id_user",0);
         Log.e("id_user",id+"");
            return id;
    }
}
