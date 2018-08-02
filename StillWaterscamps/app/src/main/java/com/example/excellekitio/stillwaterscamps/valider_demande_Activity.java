package com.example.excellekitio.stillwaterscamps;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.MEDIA.galerievideo;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Admin;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;
import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.implement.AdminImp;
import com.example.excellekitio.stillwaterscamps.implement.CampeurImp;
import com.example.excellekitio.stillwaterscamps.implement.TemoigniageImp;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by excelle kitio on 14/12/2017.
 */
public class valider_demande_Activity extends AppCompatActivity {

    List<String> email;
    List<Integer> Id_camp;
    List<Boolean> valider;
    Admin admin ;

    //String[] statues;
    //String[] contactType;

    List<Validerdemanderow> rowItems;
    ListView mylistview;
    Validerdemanderow items;
    Position position_item  = new Position();
    valider_demandeCustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valider_demande_);

        rowItems = new ArrayList<Validerdemanderow>();

        email=new ArrayList<>();
        Id_camp=new ArrayList<>();
        valider =new ArrayList<>();
//        titre = getResources().getStringArray(R.array.camp_names);
//        nom = getResources().getStringArray(R.array.nom);
//        contenu = getResources().getStringArray(R.array.camp_contenu);
        // member_pics = getResources().obtainTypedArray(R.array.camp_pics);

        // statues = getResources().getStringArray(R.array.statues);

        // contactType = getResources().getStringArray(R.array.contactType);
       // ArrayList<Campeur> campeurs = new ArrayList<Campeur>(ModelCamp.getListCampeur()) ;
        for(Campeur c : ModelCamp.getListCampeur()){

            email.add(c.getEmail_user());
            Id_camp.add(c.getId_camp());
            valider.add(c.isValider());

        }


        int i;
        for(i=0;i<email.size();i++){

            items = new Validerdemanderow(email.get(i),Id_camp.get(i).toString(),valider.get(i));

            rowItems.add(items);
        }



//        for (int i = 0; i < titre.length; i++) {
//            datatemoignage item = new datatemoignage(titre[i],nom[i],contenu[i],
//                    member_pics.getResourceId(i, -1));
//            rowItems.add(item);
//        }



        mylistview = (ListView) findViewById(R.id.list_demande);
        adapter = new  valider_demandeCustomAdapter(this,rowItems);
        mylistview.setAdapter(adapter);
       // registerForContextMenu(mylistview);
        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // Cursor cursor = (Cursor) parent.getItemAtPosition(position);
                registerForContextMenu(parent);
                openContextMenu(parent);
                position_item = new Position(position);

            }
        });

  }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo){
        super.onCreateContextMenu(menu,v,menuInfo);
        menu.setHeaderTitle("choix sur la demande");
        menu.add(0,v.getId(),0,"accepter");
        menu.add(0,v.getId(),0,"refuser");
    }
    @Override
    public boolean onContextItemSelected(MenuItem item){
        AdminImp imp = new AdminImp();

        CampeurImp imp1 = CampeurImp.getCampeurImp();

        valider_demandeCustomAdapter customAdapter = new valider_demandeCustomAdapter();
        String email = rowItems.get(position_item.getPostion()).getEmail();
        String id_camp = rowItems.get(position_item.getPostion()).getId_camp();
        Boolean bool = rowItems.get(position_item.getPostion()).getValider();

        if(item.getTitle()=="accepter"){

           Log.e("admin",""+idAdminPref());
            Toast.makeText(this,"id_admin :"+idAdminPref(),Toast.LENGTH_SHORT).show();

            imp.validerDemande(email,Integer.valueOf(id_camp),idAdminPref(),bool,true);

            rowItems.remove(position_item.getPostion());
            adapter.notifyDataSetChanged();
            //Toast.makeText(this,""+position_item.getPostion(),Toast.LENGTH_SHORT).show();
        }
        if(item.getTitle()=="refuser"){
            Toast.makeText(this,"refuser :"+email,Toast.LENGTH_SHORT).show();
        }
        return true;
    }


    public int idAdminPref(){
        SharedPreferences preferences = getSharedPreferences("myFileAdmin",0);
        //String datavalue = "DefaultName";
        int id = preferences.getInt("id_user",0);
        Log.e("id_user",id+"");
        return id;
    }
    private class Position{
         private int postion;

        public Position(int postion) {
            this.postion = postion;
        }

        public Position() {
        }

        public int getPostion() {
            return postion;
        }

        public void setPostion(int postion) {
            this.postion = postion;
        }
    }

}
