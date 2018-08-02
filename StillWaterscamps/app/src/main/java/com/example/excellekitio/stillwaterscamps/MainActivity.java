package com.example.excellekitio.stillwaterscamps;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgbar;
    Button btn;
    ModelCamp modelCamp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        modelCamp = new ModelCamp();
        setContentView(R.layout.activity_main);

        Thread mythread = new Thread(){
            @Override
            public  void run(){
               try{
                    sleep(8000);

                    modelCamp = new ModelCamp();
                    Intent intent = new Intent (getApplicationContext(),Principale.class);//class principal
                    startActivity(intent);
                    finish();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }

        };
        mythread.start();
    }
    }

