package com.example.foodplanner.firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Firebase {

    private static Firebase firebaseInstance;
    MealsRepositoryImp mealsRepositoryImp;

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
    public void showFavFromFirebase(FirebaseUser firebaseUser,Context context){
        String userId = firebaseUser.getUid();
        DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
        favoritesRef.child("favorites").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Meal meal=dataSnapshot.getValue(Meal.class);
                    mealsRepositoryImp=MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance((context),"NULL"));
                    mealsRepositoryImp.insertMeal(meal);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


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

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }
    }
    public void insertInPlan(Meal favoriteMeals,Context context) {
        mealList=new ArrayList<Meal>();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
            favoritesRef.child("plans").child(favoriteMeals.getDay()).child(favoriteMeals.getStrMeal()).setValue(favoriteMeals)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Log.e("Firebase", "Added to plan successfully ");

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("Firebase", "Failed to add plan: " + e.getMessage());

                        }
                    });





        }
        else {
            Toast.makeText(context, "Guest users can't add to favorite", Toast.LENGTH_SHORT).show();
        }
    }

    public void showPlanFromFirebase(FirebaseUser firebaseUser,Context context,String day){
        String userId = firebaseUser.getUid();
        DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
        favoritesRef.child("plans").child(day).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    Meal meal=dataSnapshot.getValue(Meal.class);
                    mealsRepositoryImp=MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance((context),"NULL"));
                    mealsRepositoryImp.insertMeal(meal);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

}
    public void removeMealFromPlan(Meal favoriteMeals) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String userId = user.getUid();
            DatabaseReference favoritesRef = databaseRef.child("Client").child(userId);
            favoritesRef.child("plans").child(favoriteMeals.getDay()).child(favoriteMeals.getStrMeal()).removeValue()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                        }
                    });

        }
    }
}
