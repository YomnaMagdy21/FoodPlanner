package com.example.foodplanner.plan.model;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
@Dao
public interface PlanDAO {


    @Query("SELECT * FROM plans_table")
    Flowable<List<MealPlan>> getAllMeals();
    //LiveData<List<Product>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertPlan(MealPlan meal);
    // void insert(Product product);

    @Delete
    void deletePlan(MealPlan meal);

    @Query("DELETE FROM plans_table")
    void deleteAllPlans();
    @Query("SELECT * From plans_table WHERE day LIKE:day")
    Flowable<List<MealPlan>>  retrievePlanMeal(String day);

}
