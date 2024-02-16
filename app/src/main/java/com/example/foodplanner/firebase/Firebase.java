package com.example.foodplanner.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.model.Meal;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Firebase {

    private static Firebase firebaseInstance;

    public static Firebase getInstance() {
        if (firebaseInstance == null) {
            firebaseInstance = new Firebase();
        }
        return firebaseInstance;
    }

//    GoogleSignInClient mGoogleSignInClient;
//
//
//    public void request(Context context){
//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();
//
//        mGoogleSignInClient = GoogleSignIn.getClient(context, gso);
//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(context);
//    }
private DatabaseReference databaseRef = FirebaseDatabase.getInstance().getReference();
    List<Meal> mealList;

    public void syncFavorites(Meal favoriteMeals,Context context) {
       mealList=new ArrayList<Meal>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
            favoritesRef.child("favorites").child(favoriteMeals.getStrMeal()).setValue(favoriteMeals)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Log.e("Firebase", "Added to favorite successfully ");

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Firebase", "Failed to add favorites: " + e.getMessage());

                        }
                    });





        }
        else {
            Toast.makeText(context, "Guest users can't add to favorite", Toast.LENGTH_SHORT).show();
        }
    }

    public void removeMealFromFav(Meal favoriteMeals) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
            favoritesRef.child("favorites").child(favoriteMeals.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e("Firebase", "Remove from favorite successfully ");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Firebase", "Failed to remove favorites: " + e.getMessage());

                        }
                    });

        }
    }

    public void showFavFromFirease(){

    }
}
