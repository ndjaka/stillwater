package com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;
import com.example.excellekitio.stillwaterscamps.entitiees.Campeurs;

import java.util.ArrayList;

/**
 * Created by lesli on 19/12/2017.
 */

public class RecyclerViewParticipant extends RecyclerView.Adapter<ParticipantHolder>{

    private static String LOG_TAG = "RecyclerViewParticipant";

    private ArrayList<Campeurs> mDataset;

    public static ParticipantHolder.MyClickListener myClickListener;



    public RecyclerViewParticipant(ArrayList<Campeurs> myDataset) {

        mDataset = (ArrayList<Campeurs>) ModelCamp.getListparticipant();
    }
    public void setOnItemClickListener(ParticipantHolder.MyClickListener myClickListener) {

        this.myClickListener = myClickListener;

    }

    @Override
    public ParticipantHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.cart_view_participant,parent,false);
        return new ParticipantHolder(view);
    }

    @Override
    public void onBindViewHolder(ParticipantHolder holder, int position) {
        holder.setAll(mDataset.get(position));
    }

    @Override
    public int getItemCount() {
        return ModelCamp.getListparticipant().size();
    }

    public void addItem(Campeurs dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }

    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }
}
class ParticipantHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private static String LOG_TAG = "ParticipantHolder";

    private TextView nom;
    private  TextView theme;



    public ParticipantHolder(View itemView) {

        super(itemView);
        nom = (TextView)itemView.findViewById(R.id.nom_campeur);
        theme =(TextView)itemView.findViewById(R.id.theme_camp);
        Log.i(LOG_TAG, "Adding Listener");
        itemView.setOnClickListener(this);
    }

    public TextView getNom() {
        return nom;
    }

    public void setNom(TextView nom) {
        this.nom = nom;
    }

    public TextView getTheme() {
        return theme;
    }

    public void setTheme(TextView theme) {
        this.theme = theme;
    }

    public void setAll(Campeurs campeurs){
        nom.setText(campeurs.getCampeur());
        theme.setText(campeurs.getCamp());

    }

    @Override
    public void onClick(View v) {

    }

    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}

