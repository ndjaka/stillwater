package com.example.excellekitio.stillwaterscamps.Partipants;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.excellekitio.stillwaterscamps.ADMINISTRATEUR.ModelCamp;
import com.example.excellekitio.stillwaterscamps.R;

import java.util.ArrayList;
import java.util.List;

public class partipants extends Activity implements AdapterView.OnItemClickListener {


    String[] members_names;
    TypedArray member_pics;
    //String[] statues;
    //String[] contactType;

    List<dataparticipant> rowItems;
    ListView mylistview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partipants);

        rowItems = new ArrayList<dataparticipant>();

        members_names = getResources().getStringArray(R.array.camp_names);

        member_pics = getResources().obtainTypedArray(R.array.camp_pics);

        // statues = getResources().getStringArray(R.array.statues);

        // contactType = getResources().getStringArray(R.array.contactType);

        for (int i = 0; i < ModelCamp.getListparticipant().size(); i++) {
            dataparticipant item = new dataparticipant(ModelCamp.getListparticipant().get(i).getCampeur(),
                    member_pics.getResourceId(i, -1));
            rowItems.add(item);
        }

        mylistview = (ListView) findViewById(R.id.list);
        CustomAdapter adapter = new CustomAdapter(this, rowItems);
        mylistview.setAdapter(adapter);

        mylistview.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,
                            long id) {

        String camp_name = rowItems.get(position).getMember_name();
        Toast.makeText(getApplicationContext(), "" + camp_name,
                Toast.LENGTH_SHORT).show();
    }

}