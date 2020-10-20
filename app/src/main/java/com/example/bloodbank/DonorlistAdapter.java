package com.example.bloodbank;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class DonorlistAdapter extends ArrayAdapter<Doners_data> {
    private Activity context;
    private List<Doners_data> donerlist;
    public DonorlistAdapter(Activity context, List<Doners_data>bookingList){
        super(context,R.layout.list_adapter,bookingList);
        this.context = context;
        this.donerlist = bookingList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View ListView = inflater.inflate(R.layout.list_adapter,null,true);

        TextView donorname = (TextView)ListView.findViewById(R.id.donorname);
        TextView gender = (TextView)ListView.findViewById(R.id.gender);
        TextView email = (TextView)ListView.findViewById(R.id.email1);
        TextView contact = (TextView)ListView.findViewById(R.id.phone);
        TextView city = (TextView)ListView.findViewById(R.id.address);
        TextView bg = (TextView)ListView.findViewById(R.id.blodgroup);



        Doners_data donor = donerlist.get(position);
        donorname.setText(donor.getMname().toUpperCase());
        gender.setText("Gender:" + donor.getMgender());
        email.setText("Email :" +donor.getMemail().toLowerCase());
        contact.setText("Phone No:" +donor.getMphone());
        city.setText("City : " +donor.getMaddress());
        bg.setText("Blood Group : " +donor.getMblodg());

        return ListView;

    }
}