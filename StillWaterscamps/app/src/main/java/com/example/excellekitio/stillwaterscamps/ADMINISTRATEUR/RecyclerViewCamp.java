package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Camp;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeur;
import com.example.excellekitio.stillwaterscamps.implement.CampeurImp;
import com.example.excellekitio.stillwaterscamps.webserviceinvoker.CallWebService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by lesli on 18/12/2017.
 */

public class RecyclerViewCamp extends RecyclerView.Adapter<RecyclerViewCamp.PostCampHolder>{

    private static String LOG_TAG = "RecyclerViewCamp";

    private ArrayList<Camp> mDataset;

    public static MyClickListener myClickListener;

    class PostCampHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView id;
        private TextView age;
        private TextView theme;
        private TextView date;
        private ImageView logo;

        private Button participer;

        public ImageView getLogo() {
            return logo;
        }

        public void setLogo(ImageView logo) {
            this.logo = logo;
        }

        public TextView getId() {
            return id;
        }

        public void setId(TextView id) {
            this.id = id;
        }

        public TextView getAge() {
            return age;
        }

        public void setAge(TextView age) {
            this.age = age;
        }

        public TextView getTheme() {
            return theme;
        }

        public void setTheme(TextView theme) {
            this.theme = theme;
        }

        public TextView getDate() {
            return date;
        }

        public void setDate(TextView date) {
            this.date = date;
        }

        public Button getParticiper() {
            return participer;
        }

        public void setParticiper(Button participer) {
            this.participer = participer;
        }

        public PostCampHolder(View itemView) {

            super(itemView);
            id = (TextView)itemView.findViewById(R.id.id);
            theme = (TextView)itemView.findViewById(R.id.theme);
            age =(TextView)itemView.findViewById(R.id.age);
            date =(TextView)itemView.findViewById(R.id.date);
            participer =(Button)itemView.findViewById(R.id.participer);
            logo =(ImageView)itemView.findViewById(R.id.image);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        public  void setImage(String nomImage, ImageView imageView){

            String url  = CallWebService.URL_DOWNLOAD_SERVLET+ "?fileName=" + nomImage;
            url = url.replace(" ", "%20");
            Uri myUri = Uri.parse(url);
            Picasso.with(imageView.getContext()).load(myUri).centerCrop().fit().into(imageView);
        }
        public void setAll(Camp camp){

            age.setText(camp.getAge_max() +"ans et" + camp.getAge_min()+"ans");
            date.setText("du "+camp.getDate_debut()+ "au " + camp.getDate_fin());
            id.setText("camp "+camp.getId());
            theme.setText(camp.getTheme());
            setImage(camp.getLogo(),logo);

            if(ModelCamp.findDemand(ModelCamp.getUser().getEmail(),camp.getId())){
                participer.setEnabled(true);
            }
        }

        @Override
        public void onClick(View v) {

        }


    }

    public void setOnItemClickListener(MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }
    public RecyclerViewCamp(ArrayList<Camp> myDataset) {

        mDataset = myDataset;
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }

    @Override
    public PostCampHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_view_row,parent,false);
        return new PostCampHolder(view);
    }

    @Override
    public void onBindViewHolder(final PostCampHolder holder, final int position) {

       holder.getId().setText(mDataset.get(position).getId()+"");
        holder.getTheme().setText(mDataset.get(position).getTheme());
        holder.getAge().setText(mDataset.get(position).getAge_max()+"ans et" + mDataset.get(position).getAge_min()+"ans");
        holder.setImage(mDataset.get(position).getLogo(),holder.getLogo());

        holder.getParticiper().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CampeurImp campeurImp = CampeurImp.getCampeurImp();
                campeurImp.demande(ModelCamp.getUser().getEmail(),mDataset.get(position).getId());
                ModelCamp.getListCampeur().add(new Campeur(
                        ModelCamp.getUser().getEmail(),
                        mDataset.get(position).getAuteur(),
                        mDataset.get(position).getId(),
                        new java.sql.Date(new java.util.Date().getTime()),
                        false
                ));
                holder.getParticiper().setEnabled(true);
                holder.getParticiper().setText("Inviter");
            }
        });


    }

    @Override
    public int getItemCount() {

        return ModelCamp.getListCampeur().size();
    }
    public void addItem(Camp dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
}








