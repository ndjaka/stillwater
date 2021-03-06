package com.example.excellekitio.stillwaterscamps.MEDIA;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.excellekitio.stillwaterscamps.R;

import java.util.ArrayList;

/**
 * Created by excelle kitio on 02/12/2017.
 */

public class MyRecyclerViewg extends RecyclerView
.Adapter<MyRecyclerViewg
.DataObjectHolder> {
    private static String LOG_TAG = "MyRecyclerViewAdapter";
    private ArrayList<Datagallerie> mDataset;
    private static MyClickListener myClickListener;
    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View
            .OnClickListener {

        TextView label;
        TextView dateTime;
        ImageView imagev;

        public DataObjectHolder(View itemView) {
            super(itemView);
            label = (TextView) itemView.findViewById(R.id.textView);
            dateTime = (TextView) itemView.findViewById(R.id.textView);
            imagev=(ImageView) itemView.findViewById(R.id.image);
            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
// myClickListener.onItemClick(getAdapterPosition(), v);
// Toast.makeText(this,"This is card View",Toast.LENGTH_LONG).show();
        }
    }
    public void setOnItemClickListener(MyClickListener myClickListener) {
        this.myClickListener = myClickListener;
    }
    public MyRecyclerViewg(ArrayList<Datagallerie> myDataset) {
        mDataset = myDataset;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_row, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.label.setText(mDataset.get(position).getmText1());
        holder.dateTime.setText(mDataset.get(position).getmText2());
        // holder.imagev.setImageResource(R.drawable.acceuil);
        holder.imagev.setBackgroundResource(mDataset.get(position).getImageUrl());

    }
    public void addItem(Datagallerie dataObj, int index) {
        mDataset.add(index, dataObj);
        notifyItemInserted(index);
    }
    public void deleteItem(int index) {
        mDataset.remove(index);
        notifyItemRemoved(index);
    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }
    public interface MyClickListener {
        public void onItemClick(int position, View v);
    }
}
