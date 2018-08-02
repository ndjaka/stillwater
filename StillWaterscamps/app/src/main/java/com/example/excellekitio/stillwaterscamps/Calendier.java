package com.example.excellekitio.stillwaterscamps;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Calendier extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DatePicker date;
        RelativeLayout layouta;
        RelativeLayout layoutb;
        TableLayout tablea;
        TableLayout tableb;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        initialisationTableau();
        date=(DatePicker)findViewById(R.id.dataPicker);
        Calendar calendar = Calendar.getInstance();
        date.init(calendar.get(Calendar.YEAR),calendar.get(calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH),new DatePicker.OnDateChangedListener(){
            @Override
            public void onDateChanged(DatePicker date ,int i, int i1,int i2 ){
                Toast.makeText(getApplicationContext(),date.getDayOfMonth()+"-"+date.getMonth()+"-"+date.getYear(),Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void initialisationTableau(){
// donnÃ©es du tableau
        List<camps> listeCustomers=new ArrayList<camps>();
        for(Camp c : ModelCamp.getListCamp()){
            camps ca = new camps();
            ca.setIdcamps("idcamp" + c.getId());
            ca.setPeriode("du" + c.getDate_debut()+" au " + c.getDate_fin());
            ca.setIntervaled_age(c.getAge_max());
            listeCustomers.add(ca);
        }

// on prend le tableau dÃ©fini dans le layout
        TableLayout table = (TableLayout) findViewById(R.id.idTableau);
        TableRow row; // crÃ©ation d'un Ã©lÃ©ment : ligne
        TextView[] tv=new TextView[ModelCamp.getListCamp().size()]; // crÃ©ation des cellules
/*Entete du tableau*/
// Creation d'une nouvelle ligne devant contenir l'entete
        row = new TableRow(this);
        row.setMinimumHeight(30);//Taille minimale de la ligne
        for(int i=0;i<ModelCamp.getListCamp().size();i++) {
            tv[i] = new TextView(this); // crÃ©ation cellule
            tv[i].setGravity(Gravity.CENTER); // centrage dans la cellule
            tv[i].setHeight(55);//hauteur de la cellule
// adaptation de la largeur de colonne Ã  l'Ã©cran :
            tv[i].setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));
//Couleur du texte
            tv[i].setTextColor(Color.WHITE);


//Ajout du texte
            if(i==0)
                tv[i].setText("Camps");
            else if(i==1)
                tv[i].setText("Aage");
            else if(i==2)
                tv[i].setText("Période");
//Ajout des elements de l'entete
            row.addView(tv[i]);
        }
//Ajout de l'entete au tableau
        table.addView(row);
// pour chaque ligne
        for(int i=0;i<listeCustomers.size();i++) {
            row = new TableRow(this); // crÃ©ation d'une nouvelle ligne
            row.setMinimumHeight(30);//Taille minimale de la ligne
            for(int j=0;j<4;j++) {
                tv[j] = new TextView(this); // crÃ©ation cellule
                tv[j].setGravity(Gravity.CENTER); // centrage dans la cellule
                tv[j].setHeight(55);//hauteur de la cellule
// adaptation de la largeur de colonne Ã  l'Ã©cran :
                tv[j].setLayoutParams(new TableRow.LayoutParams(0, android.view.ViewGroup.LayoutParams.WRAP_CONTENT, 1));
// ajout du texte
                if(j==0)
                    tv[j].setText(String.valueOf(listeCustomers.get(i).getIdcamps()));
                else if(j==1)
                    tv[j].setText(String.valueOf(listeCustomers.get(i).getIntervaled_age()));
                else if(j==2)
                    tv[j].setText(String.valueOf(listeCustomers.get(i).getPeriode()));

// ajout des cellules Ã  la ligne
                row.addView(tv[j]);
            }
//Action Ã  executer lorsqu'on clique sur la ligne
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    TextView mTextView = (TextView) ((TableRow)v).getChildAt(0);
                    String c_id= mTextView.getText().toString();
                    Toast.makeText(Calendier.this, "le camps selectioné est: "+c_id, Toast.LENGTH_LONG).show();
                }
            });
// ajout de la ligne au tableau
            table.addView(row);
        }
    }

}
