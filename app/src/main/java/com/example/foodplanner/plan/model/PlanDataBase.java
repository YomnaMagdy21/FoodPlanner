//package com.example.foodplanner.plan.model;
//
//import android.content.Context;
//
//import android.content.Context;
//
//import androidx.room.Database;
//import androidx.room.Room;
//import androidx.room.RoomDatabase;
//
//import com.example.foodplanner.model.Meal;
//
//
//@Database(entities = {MealPlan.class}, version = 1)
//
//public abstract class PlanDataBase extends RoomDatabase{
//
//    private static PlanDataBase instance=null;
//    public abstract PlanDAO planDAO();
//    public static synchronized PlanDataBase getInstance(Context context){
//        if(instance==null){
//            instance= Room.databaseBuilder(context.getApplicationContext(), PlanDataBase.class,"plans-db").build();
//        }
//        return instance;
//    }
//
//}
//
