package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodplanner.login.view.LoginActivity;
import com.example.foodplanner.login.view.LoginFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Timer myTimer = new Timer();


        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                openSignInScreen();
            }
        }, 3800);


    }

    private void openSignInScreen() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}