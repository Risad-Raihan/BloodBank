package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class BeDonerActivity extends AppCompatActivity {

    EditText  dname, demail,daddress, dgender,dbg, dPhone;
    Button Badoner;

    DatabaseReference badreference;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_be_doner);
        getSupportActionBar().setTitle("Donor Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        dname = findViewById(R.id.dfullname);
        dgender = findViewById(R.id.dgender);
        demail = findViewById(R.id.demail);
        daddress = findViewById(R.id.daddress);
        dbg = findViewById(R.id.dbloodgroup);
        dPhone = findViewById(R.id.dphone);
        Badoner = findViewById(R.id.dsignup_btn);

        badreference = FirebaseDatabase.getInstance().getReference("Donors");

        Badoner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                upload();
            }
        });
    }


              private void upload(){
                  String txt_fulname = dname.getText().toString();
                  String txt_gender = dgender.getText().toString();
                  String txt_email = demail.getText().toString();
                  String txt_address = daddress.getText().toString();
                  String txt_bloodg = dbg.getText().toString();
                  String txt_phone = dPhone.getText().toString();

                  if (!TextUtils.isEmpty(txt_fulname)){
                      String Sid = badreference.push().getKey();
                      Doners_data Donersdata = new Doners_data(Sid, txt_fulname, txt_gender,txt_email,txt_address,txt_bloodg,txt_phone);
                      badreference.child(Sid).setValue(Donersdata);

                      new SweetAlertDialog(BeDonerActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                              .setTitleText("Success!")
                              .setContentText("Thanks for Registration!")
                              .setConfirmText("OK")
                              .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                  @Override
                                  public void onClick(SweetAlertDialog sweetAlertDialog) {
                                      Intent loginac = new Intent(BeDonerActivity.this,MainActivity.class);
                                      startActivity(loginac);
                                  }
                              })
                              .show();


                  }
              }
    //back button
    @Override
    public boolean onSupportNavigateUp() {
        Intent i =new Intent(BeDonerActivity.this, MainActivity.class);
        startActivity(i);
        return true;
    }

}