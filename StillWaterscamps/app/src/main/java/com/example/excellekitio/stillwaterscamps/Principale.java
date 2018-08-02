package com.example.excellekitio.stillwaterscamps;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;
import android.widget.ViewFlipper;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.CreerCamp;
import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.listedesartcilesparticipant;
import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.listedesarticles;
import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.vsi2;
import com.example.excellekitio.stillwaterscamps.CONTEDUNARTICLE.article;
import com.example.excellekitio.stillwaterscamps.MEDIA.Galerieprimcipale;
import com.example.excellekitio.stillwaterscamps.MEDIA.GalerieprincipaleEssam;
import com.example.excellekitio.stillwaterscamps.MEDIA.Image;
import com.example.excellekitio.stillwaterscamps.MEDIA.galerievideo;
import com.example.excellekitio.stillwaterscamps.MEDIA.galerievideoessam;
import com.example.excellekitio.stillwaterscamps.temoignage.temoignagelist;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;


public class Principale extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final int FLIP_DURATION = 2000;
    private ViewFlipper viewFlipper;
    private Button but;
    private boolean isSlideshowOn = false;
    private ImageView imageView;
    private ImageButton calendar;
    private Button gallerie;
    private Button articles;
    private Button video;
    private   Button sponsor;
    private   Button benevol;
    private   Button inscription;
    private ImageButton regles;
    private ImageButton registres;
    private ImageButton pdf;
    private Button temoin;
    FloatingActionMenu fab;
    public static Boolean openFLoating =false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_principale);
        Log.e("bool",""+Principale.openFLoating);
        if(Principale.openFLoating == true) {
            Log.e("bool .if",""+Principale.openFLoating);
            fab = ( FloatingActionMenu) findViewById(R.id.menu);
            fab.setVisibility(View.VISIBLE);
        }
        if(idAdminPref()!=0){

            fab = ( FloatingActionMenu) findViewById(R.id.menu);
            fab.setVisibility(View.VISIBLE);
        }

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        getSupportActionBar().setHomeButtonEnabled(true);
//      getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        temoin=(Button)findViewById(R.id.temoignage);
        temoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Principale.this,Temoignage.class);
                startActivity(intent);
            }
        });
        sponsor = (Button)findViewById(R.id.sponsor);

        sponsor.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this,sponsors.class);
                startActivity(intent);
            }
        });
        inscription = (Button)findViewById(R.id.incriptioni);

        inscription.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this,incriptioninternationnale.class);
                startActivity(intent);
            }
        });
        benevol = (Button)findViewById(R.id.benevole);

        benevol.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this,benevole.class);
                startActivity(intent);
            }
        });
        viewFlipper = (ViewFlipper)findViewById(R.id.image_view_flipper);
        video= (Button)findViewById(R.id.idbtn);

        articles = (Button)findViewById(R.id.articles);

        articles.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this,Image.class);
                startActivity(intent);
            }
        });
         gallerie = (Button)findViewById(R.id.idbtng);

        gallerie.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this, GalerieprincipaleEssam.class);
                startActivity(intent);
            }
        });
        video.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this, galerievideoessam.class);
                startActivity(intent);
            }
        });
        //imageView=(ImageView)findViewById(R.id.imageview);
        calendar=(ImageButton)findViewById(R.id.calendrier);
        calendar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Principale.this,Calendier.class);
                startActivity(intent);
            }
        });



        viewFlipper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isSlideshowOn){
                    isSlideshowOn = false;
                }else{
                    isSlideshowOn = true;
                }
                if(isSlideshowOn){
                    startSlideshow();
                    animateSlideshow();
                }else{
                    stopSlideshow();
                }
            }
        });
        viewFlipper.setInAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_in));
        viewFlipper.setOutAnimation(AnimationUtils.loadAnimation(this, R.anim.fade_out));




        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT);
        ActionBar actionBar = getSupportActionBar();
        if(null != actionBar){
            actionBar.hide();
        }

    }

    public int idAdminPref(){
        SharedPreferences preferences = getSharedPreferences("myFileAdmin",0);
        //String datavalue = "DefaultName";
        int id = preferences.getInt("id_user",0);
        Log.e("id_user",id+"");
        return id;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.principale, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_benevole) {
            Intent intent=new Intent(Principale.this,benevole.class);
            startActivity(intent);
        }
        if (id == R.id.action_sponsor) {
            Intent intent=new Intent(Principale.this,sponsors.class);
            startActivity(intent);
        }
        if (id == R.id.action_incription) {
            Intent intent=new Intent(Principale.this,incriptioninternationnale.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent=new Intent(this,Connexion.class);
            startActivity(intent);
        } else if (id == R.id.nav_slideshow) {
            Intent intent=new Intent(this,conctater.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent=new Intent(this,temoignagelist.class);
            startActivity(intent);
        } else if (id == R.id.nav_share) {
            Intent intent=new Intent(this,Share.class);
            startActivity(intent);
        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_connexion) {
            Intent intent=new Intent(this,Apropos.class);
            startActivity(intent);
        }else if (id == R.id.nav_faq) {
            Intent intent=new Intent(this,FAQ.class);
            startActivity(intent);
        }else if (id == R.id.nav_archive) {
            Intent intent=new Intent(this,Image.class);
            startActivity(intent);
        }
        else if (id == R.id.nav_rate) {
            Intent intent=new Intent(this,RateApp.class);

            startActivity(intent);
        }else if(id==R.id.nav_listcamp){
            Intent intent=new Intent(this,listedesarticles.class);

            startActivity(intent);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private void startSlideshow(){
        if(!viewFlipper.isFlipping()){
            viewFlipper.setAutoStart(true);
            viewFlipper.setFlipInterval(FLIP_DURATION);
            viewFlipper.startFlipping();
        }
    }
    private void animateSlideshow(){
        viewFlipper.getInAnimation().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }
    private void stopSlideshow(){
        if(viewFlipper.isFlipping()){
            viewFlipper.stopFlipping();
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bp = (Bitmap) data.getExtras().get("data");
        imageView.setImageBitmap(bp);

    }
    public void ActiveAdmin(){

      String val =  getIntent().getExtras().getString("open");
        //String value =
            if(val.equalsIgnoreCase("")){
                fab.setVisibility(View.INVISIBLE);
            }else if(val.equalsIgnoreCase("bon")){
                fab.setVisibility(View.VISIBLE);
            }else{
                fab.setVisibility(View.INVISIBLE);
            }


    }
    public void ouvrirregistre(View view ){
        Intent intent=new Intent(this,compte.class);
        startActivity(intent);
    }
    public void ouvrirregle(View view ){
        Intent intent=new Intent(this,Rules.class);
        startActivity(intent);
    }
    public void ouvrirmedicale(View view ){
        Intent intent=new Intent(this,medicalpdf.class);
        startActivity(intent);
    }
    public void voirarticle(View view ){
        Intent intent=new Intent(this,article.class);
        startActivity(intent);
    }
    public void modifiercamps(View view ){
        Intent intent=new Intent(this,listedesarticles.class);
        startActivity(intent);
    }
    public void creercamps(View view ){
        Intent intent=new Intent(this,CreerCamp.class);
        startActivity(intent);
    }
    public void listepartcipant(View view ){
        Intent intent=new Intent(this,listedesartcilesparticipant.class);
        startActivity(intent);


}
public void listDeemande(View view ){
    Intent intent=new Intent(this,valider_demande_Activity.class);
    startActivity(intent);


}
public void ajouterDemande(View view){
    Intent intent=new Intent(this,vsi2.class);
    startActivity(intent);
}
}