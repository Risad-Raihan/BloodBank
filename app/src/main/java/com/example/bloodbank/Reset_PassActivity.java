package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class Reset_PassActivity extends AppCompatActivity {

    EditText userEmail;
    Button userPass;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset__pass);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        userEmail = findViewById(R.id.useremail);
        userPass = findViewById(R.id.btn_forgot_password);

        progressDialog = new ProgressDialog(this);


        firebaseAuth = FirebaseAuth.getInstance();


        userPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //progressbar
                progressDialog.setMessage("Please Wait....");
                progressDialog.show();

                firebaseAuth.sendPasswordResetEmail(userEmail.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(Reset_PassActivity.this, "Reset Password Link Send to Your Email Successfully",
                                            Toast.LENGTH_LONG).show();

                                    startActivity(new Intent(getApplicationContext(),
                                            MainActivity.class));
                                } else {
                                    Toast.makeText(Reset_PassActivity.this,
                                            task.getException().getMessage(), Toast.LENGTH_LONG).show();
                                } }
                        });
            }
        });
    }
}
