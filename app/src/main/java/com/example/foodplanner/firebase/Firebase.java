package com.example.foodplanner.firebase;

import android.content.Context;
import android.util.Log;

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

    public void syncFavorites(Meal favoriteMeals) {
       mealList=new ArrayList<Meal>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference favoritesRef = databaseRef.child("Favorite");
            favoritesRef.child(userId).child("strMeal").setValue(favoriteMeals.getStrMeal())
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("FirebaseSyncManager", "Failed to clear existing favorites: " + e.getMessage());

                        }
                    });

            // Clear existing favorites
//            favoritesRef.removeValue()
//                    .addOnSuccessListener(aVoid -> {
//                        // Add new favorites
//                        for (Meal meal : mealList) {
//                            String mealId = favoritesRef.push().getKey(); // Generate a unique key
//                            favoritesRef.child(favoriteMeals.getStrMeal()).setValue(favoriteMeals);
//                        }
//                    })
//                    .addOnFailureListener(e -> {
//                        // Handle failure
//                        Log.e("FirebaseSyncManager", "Failed to clear existing favorites: " + e.getMessage());
//                    });
        }
    }
}
           // favoritesRef.child(favoriteMeals.getStrMeal()).setValue(favoriteMeals)
