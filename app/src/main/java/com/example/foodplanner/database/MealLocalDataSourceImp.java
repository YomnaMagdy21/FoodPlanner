package com.example.foodplanner.database;

import android.content.Context;

import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImp {
    private MealDAO mealDAO;
    private Flowable<List<Meal>> storedMeals;
    private static MealLocalDataSourceImp localSource=null;

    private  MealLocalDataSourceImp(Context context){

        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= db.mealDAO();
        storedMeals=mealDAO.getAllMeals();
    }
    public static MealLocalDataSourceImp getInstance(Context context) {
        if(localSource==null){
            localSource=new MealLocalDataSourceImp(context);
        }
        return localSource;
    }

    public Flowable<List<Meal>> getAllStoredMeals(){return storedMeals;}

    public void delete(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(meal);
            }
        }).start();
    }
    public void insert(Meal meal){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insert(meal);
            }
        }).start();
    }

}
