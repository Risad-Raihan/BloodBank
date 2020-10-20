package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Donor_ListActivity extends AppCompatActivity {
    ListView lv;
    EditText serrch;
    DatabaseReference DBref;
    List<Doners_data> datas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor__list);
        getSupportActionBar().setTitle("Donors");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        DBref = FirebaseDatabase.getInstance().getReference("Donors");
        datas = new ArrayList<>();
        lv = findViewById(R.id.donerlist_view);
        serrch = findViewById(R.id.search_by);


    }

    @Override
    protected void onStart() {
        super.onStart();
        DBref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot statussnapshot : dataSnapshot.getChildren()) {
                    Doners_data getdata = statussnapshot.getValue(Doners_data.class);
                    datas.add(getdata);
                }
                DonorlistAdapter adapter = new DonorlistAdapter(Donor_ListActivity.this, datas);
                lv.setAdapter(adapter);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        final DonorlistAdapter adapter = new DonorlistAdapter(Donor_ListActivity.this, datas);
        lv.setAdapter(adapter);
    }


    //back button
    @Override
    public boolean onSupportNavigateUp() {
        Intent i =new Intent(Donor_ListActivity.this, MainActivity.class);
        startActivity(i);
        return true;
    }
}
