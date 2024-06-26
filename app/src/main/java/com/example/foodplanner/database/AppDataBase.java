package com.example.foodplanner.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.model.Meal;

@Database(entities = {Meal.class}, version = 8)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance=null;
    public abstract MealDAO mealDAO();
    public static synchronized AppDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"meals-db").fallbackToDestructiveMigration().build();
        }
        return instance;
    }

}

