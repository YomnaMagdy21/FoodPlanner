package com.example.foodplanner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.login.view.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    static SharedPreferences sharedPreferences;
    DatabaseReference mDatabase;
    private FirebaseAuth auth;
    Firebase firebase;

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
        }, 4200);


        auth = FirebaseAuth.getInstance();
        sharedPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        mDatabase = FirebaseDatabase.getInstance().getReference();

        // Check if the user is already logged in



    }

    private void openSignInScreen() {
//        if (isLoggedIn()) {
//            // User is already logged in, retrieve archived data
//            retrieveArchivedData();
//        } else {
            Intent i = new Intent(this, MainActivity.class);
            startActivity(i);
            finish();
            // User is not logged in, show login screen
            // Implement your login screen logic here
       // }

    }



    private boolean isLoggedIn() {
        // Check the login status from SharedPreferences
        return sharedPreferences.getBoolean("isLoggedIn", false);
    }

    public static void setLoggedIn(boolean isLoggedIn) {
        // Update the login status in SharedPreferences
        sharedPreferences.edit().putBoolean("isLoggedIn", isLoggedIn).apply();
    }

    private void retrieveArchivedData() {
        // Retrieve archived data from the Firebase Realtime Database
        // Example code to retrieve data
        FirebaseUser firebaseUser=auth.getCurrentUser();
        firebase.showFavFromFirebase(firebaseUser,this);
        firebase.showPlanFromFirebase(firebaseUser,this,"Sunday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Monday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Tuesday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Wednesday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Thursday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Friday");
        firebase.showPlanFromFirebase(firebaseUser,this,"Saturday");
        startActivity(new Intent(this, HomeActivity.class));


    }

}