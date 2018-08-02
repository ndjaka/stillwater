package com.example.excellekitio.stillwaterscamps.temoignage;

import android.app.Activity;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Temoigniage;
import com.example.excellekitio.stillwaterscamps.implement.TemoigniageImp;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by excelle kitio on 14/12/2017.
 */
public class temoignagelist extends AppCompatActivity implements AdapterView.OnItemClickListener {

   List<String> titre;
   List<Integer> Id_user;
    List<String> contenu;
ImageView view;
    //String[] statues;
    //String[] contactType;

    List<datatemoignage> rowItems;
    ListView mylistview;
    CustomAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listtemoignage);

        rowItems = new ArrayList<>();

            titre=new ArrayList<>();
            Id_user=new ArrayList<>();
           contenu =new ArrayList<>();
        view = (ImageView)findViewById(R.id.member_pic);
//        titre = getResources().getStringArray(R.array.camp_names);
//        nom = getResources().getStringArray(R.array.nom);
//        contenu = getResources().getStringArray(R.array.camp_contenu);
      // member_pics = getResources().obtainTypedArray(R.array.camp_pics);

        // statues = getResources().getStringArray(R.array.statues);

        // contactType = getResources().getStringArray(R.array.contactType);
       // ArrayList<Temoigniage> temoigniages = new ArrayList<Temoigniage>(ModelCamp.getListTemoigniage()) ;

        for(Temoigniage t : ModelCamp.getListTemoigniage()){

                titre.add(t.getTitre());
                Id_user.add(t.getId_user());
                contenu.add(t.getContenu());

        }
//
       //String url = CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName=images.jpg";
//
//        url = url.replace(" ", "%20");
//        Uri myUri = Uri.parse(url);
//
//          Picasso.with(view.getContext()).load(myUri).centerCrop().fit().into(view);
           for(int i=0;i<contenu.size();i++){

              datatemoignage item = new datatemoignage(titre.get(i),contenu.get(i));

               rowItems.add(item);
           }



//        for (int i = 0; i < titre.length; i++) {
//            datatemoignage item = new datatemoignage(titre[i],nom[i],contenu[i],
//                    member_pics.getResourceId(i, -1));
//            rowItems.add(item);
//        }

        mylistview = (ListView) findViewById(R.id.list);
       adapter = new CustomAdapter(this,rowItems);
        mylistview.setAdapter(adapter);
        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String camp_name = rowItems.get(position).getTitre();
        Toast.makeText(getApplicationContext(), "" + camp_name,
                Toast.LENGTH_SHORT).show();
    }
}
