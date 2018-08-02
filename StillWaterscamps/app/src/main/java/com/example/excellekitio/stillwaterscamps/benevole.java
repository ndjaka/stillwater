package com.example.excellekitio.stillwaterscamps;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class benevole extends AppCompatActivity {
   EditText txtUserName, txtPassword;
    Button btncompte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_benevole);
        txtUserName = (EditText) findViewById(R.id.idUserName);
        txtPassword = (EditText) findViewById(R.id.idPassword);
        btncompte = (Button) findViewById(R.id.accout);

        btncompte.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                login();
                Intent intent=new Intent(benevole.this,compte.class);
                startActivity(intent);
            }

        });
    }
    private void login(){
        if (TextUtils.isEmpty(txtUserName.getText().toString().trim()) || TextUtils.isEmpty(txtPassword.getText().toString().trim())){
            txtUserName.setError("ce champ ne peut pas etre vide");
            txtPassword.setError("ce champ ne peut pas etre vide");
        }else if (!emailValidator(txtUserName.getText().toString())){
            txtUserName.setError("svp entrez une adresse email valide");
        }else{
            Toast.makeText(getApplicationContext(),"connxion reussie",Toast.LENGTH_SHORT).show();
        }
    }

    //écrivons la methode qui permet de vérifier une adresse email

    public boolean emailValidator(String email){
        Pattern pattern;
        Matcher matcher;
        final String EMAIL_PATTERN ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return  matcher.matches();
    }
}
