package com.example.foodplanner.database;

import android.content.Context;

import com.airbnb.lottie.L;
import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealLocalDataSourceImp {

    private MealDAO mealDAO;
    private Flowable<List<Meal>> storedMeals;
    private Observable<List<Meal>> storedPlans;
    private Context context;
    private static MealLocalDataSourceImp localSource=null;

    private  MealLocalDataSourceImp(Context context,String day){

        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO= db.mealDAO();
        storedMeals=mealDAO.getAllMeals();
//        if(day.equals("NULL")){
//            storedMeals=mealDAO.getPlansMealsFromDay(day);
//        }
    }
    public static MealLocalDataSourceImp getInstance(Context context,String day) {
        if(localSource==null){
            localSource=new MealLocalDataSourceImp(context,day);
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

    public void deleteAllData(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.deleteAll();
            }
        }).start();
    }

    public Observable<List<Meal>> getPlanMealsByDay(String day){
        new Thread(new Runnable() {
            @Override
            public void run() {
//                AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
//                mealDAO= db.mealDAO();
               storedPlans= mealDAO.getPlanMeals(day);
            }
        }).start();
        return storedPlans;
    }

}
