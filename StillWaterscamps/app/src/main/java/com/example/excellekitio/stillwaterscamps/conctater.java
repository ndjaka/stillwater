package com.example.excellekitio.stillwaterscamps;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class conctater extends AppCompatActivity {
    Intent intent = null;
    Context ctx;
    Button btncall, btndial, btnbrowser, btngooglemap;
    Button btnsms, btnmail;
    Button btncontact;
    EditText txtfirstvalue, txtsecondvalue;
    Intent MyIntent;
    String firstvalue, secondvalue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conctater);
        this.alloacatememory();
        this.setEvents();

    }
    private void setEvents() {

        btncall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firstvalue = "695390226";
                try {
                    firstvalue = "695390226";
                    MyIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + firstvalue));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        if (checkSelfPermission(android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            Toast.makeText(conctater.this, "permission granted", Toast.LENGTH_SHORT).show();
                            requestPermissions(new String[]{android.Manifest.permission.CALL_PHONE}, 10);
                            return;
                        }
                    }

                    ctx.startActivity(MyIntent);

                } catch (SecurityException e) {
                    Log.e("call error", "can't call");
                    Toast.makeText(conctater.this, "cant't call", Toast.LENGTH_SHORT).show();
                }


            }
        });
        btnsms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstvalue = "695390226";
                //secondvalue = txtsecondvalue.getText().toString();
                try {
                    MyIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:"));
                    MyIntent.setType("vnd.android-dir/mms-sms");
                    MyIntent.putExtra("address", firstvalue);
                    MyIntent.putExtra("sms_body", "");
                    startActivity(MyIntent);
                } catch (Exception e) {
                    Toast.makeText(ctx, "Sms faild, please try again", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });
        btnmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firstvalue = "armelsanou@gmail.com";
                secondvalue ="";

                MyIntent = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
                MyIntent.setType("text/plain");
                MyIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{firstvalue});
                // MyIntent.putExtra(Intent.EXTRA_SUBJECT,stxtsubject);
                MyIntent.putExtra(Intent.EXTRA_TEXT, secondvalue);

                try {
                    ctx.startActivity(Intent.createChooser(MyIntent, "send email..."));
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(ctx, "There is no email client installed.", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        });


    }

    private void alloacatememory() {
        //txtfirstvalue = (EditText) this.findViewById(R.id.txtfirst);
        //txtsecondvalue = (EditText) this.findViewById(R.id.txtsecond);

        btncall = (Button) this.findViewById(R.id.btncall);
        btnsms = (Button) findViewById(R.id.btnsms);
        btnmail = (Button) findViewById(R.id.btnmail);
    }

}

