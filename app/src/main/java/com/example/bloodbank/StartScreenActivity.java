package com.example.bloodbank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

public class StartScreenActivity extends AppCompatActivity {
    private int SLEEP_TIMER = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_start_screen);
        getSupportActionBar().hide();


        //Opening Page Code
        LogoLauncher logoLauncher = new LogoLauncher();
        logoLauncher.start();
    }
    private class LogoLauncher extends Thread{
        public void run(){
            try {
                sleep(1000 * SLEEP_TIMER);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
            Intent intent = new Intent(StartScreenActivity.this,LoginActivity.class);
            startActivity(intent);
            StartScreenActivity.this.finish();
        }
    }
}
