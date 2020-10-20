package com.example.bloodbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class LoginActivity extends AppCompatActivity {

    EditText logemail, logpass;
    Button loginbutton;
    private FirebaseAuth auth;
    TextView signupnow, forgotpass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        logemail =findViewById(R.id.email_text);
        logpass = findViewById(R.id.password_txt);
        loginbutton = findViewById(R.id.signin_btn);
        forgotpass = findViewById(R.id.forgotpassword);
        signupnow = findViewById(R.id.signuppage);
        signupnow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gosignup = new Intent(LoginActivity.this,SignupActivity.class);
                startActivity(gosignup);

            }
        });

        //Get Firebase auth instance
        auth = FirebaseAuth.getInstance();

        //Sign in button work
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                String email = logemail.getText().toString().trim();
                String password = logpass.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    logemail.setError("Email Required");
                    logemail.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    logpass.setError("Password Required");
                    logpass.requestFocus();
                    return;
                }
                if (password.length()< 6){
                    logpass.setError("Password should be at least 6 characters long");
                    logpass.requestFocus();
                    return;
                }
                final SweetAlertDialog pDialog = new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.PROGRESS_TYPE);
                pDialog.getProgressHelper().setBarColor(Color.parseColor("#A71A1A"));
                pDialog.setTitleText("Please Wait...");
                pDialog.setCancelable(false);
                pDialog.show();


                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

                                if (task.isSuccessful())
                                {
                                    if (user.isEmailVerified()){
                                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                                    }
                                    else {
                                        new SweetAlertDialog(LoginActivity.this, SweetAlertDialog.WARNING_TYPE)
                                                .setTitleText("Email not verified!")
                                                .setContentText("Please check your email for VERIFICATION")
                                                .setConfirmText("OK")
                                                .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                                    @Override
                                                    public void onClick(SweetAlertDialog sweetAlertDialog) {
                                                        Intent loginac = new Intent(LoginActivity.this,LoginActivity.class);
                                                        startActivity(loginac);
                                                    }
                                                })
                                                .show();

                                        FirebaseAuth.getInstance().signOut();
                                    }
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, task.getException().getMessage(),
                                            Toast.LENGTH_LONG).show();
                                }
                                pDialog.dismiss();
                            }
                        });
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,Reset_PassActivity.class));
            }
        });

    }

}
