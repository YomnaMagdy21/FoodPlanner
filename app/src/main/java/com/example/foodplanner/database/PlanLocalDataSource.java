//package com.example.foodplanner.database;
//
//import android.content.Context;
//
//import com.example.foodplanner.model.Meal;
//import com.example.foodplanner.plan.model.MealPlan;
//import com.example.foodplanner.plan.model.PlanDAO;
//import com.example.foodplanner.plan.model.PlanDataBase;
//
//import java.util.List;
//
//import io.reactivex.rxjava3.core.Flowable;
//import io.reactivex.rxjava3.core.Observable;
//
//public class PlanLocalDataSource {
//    private MealDAO mealDAO;
//    private PlanDAO planDAO;
//    private Flowable<List<Meal>> storedMeals;
//    private Observable<List<MealPlan>> storedPlans;
//    private Context context;
//    private static PlanLocalDataSource localSource=null;
//
//    private  PlanLocalDataSource(Context context,String day){
//
//        PlanDataBase dbplan=PlanDataBase.getInstance(context.getApplicationContext());
//        planDAO= dbplan.planDAO();
//        storedMeals=mealDAO.getAllMeals();
//
////        if(day.equals("NULL")){
////            storedMeals=mealDAO.getPlansMealsFromDay(day);
////        }
//    }
//    public static PlanLocalDataSource getInstance(Context context,String day) {
//        if(localSource==null){
//            localSource=new PlanLocalDataSource(context,day);
//        }
//        return localSource;
//    }
//    public Observable<List<MealPlan>> getPlanMealsByDay(String day){
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                storedPlans= mealDAO.getPlanMeals(day);
//            }
//        }).start();
//        return storedPlans;
//    }
//
//}
