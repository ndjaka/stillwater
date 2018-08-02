package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.Toast;


import com.example.excellekitio.stillwaterscamps.MEDIA.MediaAdapterEssam;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.Tmedia;

import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.implement.CampImp;
import com.example.excellekitio.stillwaterscamps.implement.MediaImp;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class vsi2 extends AppCompatActivity{

    Button mStart;
    Button up_btn ;
    GridView gridview ;
    MediaAdapterEssam mediaAdapterEssam;
    ArrayList<Tmedia> multimedia =  new ArrayList<Tmedia>();
    Spinner spinnerCam ;
    int idCamp=0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vsi_essam);

        up_btn = (Button)findViewById(R.id.uploader);
        mStart = (Button) findViewById(R.id.start);
        mediaAdapterEssam = new MediaAdapterEssam(this,multimedia);
        spinnerCam =(Spinner)findViewById(R.id.spinCamp);
        gridview = (GridView) findViewById(R.id.gridview);
        final List<Camp> campList = CampImp.getCampImp().allCamp();

        final CharSequence[] options = {"Images", "Videos", "Cancel"};
        final  AlertDialog.Builder builder = new AlertDialog.Builder(vsi2.this);
        builder.setTitle("Selectionner a partir ...");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Images")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(intent, 1);
                } else if (options[item].equals("Videos")) {
                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI);
                    intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                    startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
                dialog.dismiss();
            }
        });

        mStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.show();

            }
        });



        //set adapter
        gridview.setAdapter(mediaAdapterEssam);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    final int position, long id) {
                Tmedia tmedia = multimedia.get(position);
                final AlertDialog.Builder confirm  =  new AlertDialog.Builder(vsi2.this);

                confirm.setTitle("Supprimer de la selection");
                confirm.setMessage("voulez-vous enlever "+new File(tmedia.getFilePath()).getName()+" de la liste de selection");
                confirm.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        multimedia.remove(position);
                        mediaAdapterEssam.notifyDataSetChanged();
                    }
                });
                confirm.setNegativeButton("non",null);
                confirm.show();
            }
        });

        ArrayAdapter<Camp> adapterSpinner = new ArrayAdapter<Camp>(this,android.R.layout.simple_dropdown_item_1line,campList );
        spinnerCam.setAdapter(adapterSpinner);
        spinnerCam.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                idCamp = campList.get(position).getId();
                spinnerCam.setSelection(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // uploade file there
                if (spinnerCam.getSelectedItemPosition()<0 || multimedia.size()==0)
                {
                    AlertDialog.Builder alert = new AlertDialog.Builder(vsi2.this);
                    alert.setMessage("veillez selectionner le camp");
                    alert.show();
                }else
                {
                   ProgressDialog progressDialog  = new ProgressDialog(vsi2.this);
                   progressDialog.setMessage("Veillez patientez");
                   progressDialog.show();

                    MediaImp.getInstance().addMedia(multimedia,((Camp)spinnerCam.getSelectedItem()).getId(),new ProgressDialog(vsi2.this));
                    progressDialog.dismiss();
                    Toast.makeText(vsi2.this, "Fichié uploadé avec succes", Toast.LENGTH_SHORT).show();

                }
            }
        });

    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == 1 && resultCode == RESULT_OK && null != data) {
                pickMultimedia(data,"image");

            }else {
                if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                    pickMultimedia(data,"video");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Dysfonctionnement...", Toast.LENGTH_SHORT);

        }

    }

    public String getMediaRealPathFromURI(Context context, Uri contentUri,String type) {
        Cursor cursor = null;
        try {
            String[] filePathColumn ;
            if(type.equals("image"))
            {
                filePathColumn = new String[]{MediaStore.Images.Media.DATA};
            }else
            {
                filePathColumn = new String[]{MediaStore.Video.Media.DATA};

            }
            cursor = context.getContentResolver().query(contentUri, filePathColumn, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(filePathColumn[0]);
            cursor.moveToFirst();

            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
    public void pickMultimedia(Intent data,String type)
    {
        Uri clipData  = data.getData();
        if (clipData != null) {
            String path = getMediaRealPathFromURI(getApplicationContext(), clipData, type);
             multimedia.add(new Tmedia(clipData,type,path));
             mediaAdapterEssam.notifyDataSetChanged();
             mediaAdapterEssam.notifyAll();

        }

    }
}

