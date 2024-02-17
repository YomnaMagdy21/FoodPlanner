package com.example.foodplanner.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

@Dao
public interface MealDAO {

    @Query("SELECT * FROM meals_table")
    Flowable<List<Meal>> getAllMeals();
    //LiveData<List<Product>> getAllProduct();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Meal meal);
    // void insert(Product product);

    @Delete
    void delete(Meal meal);

    @Query("DELETE FROM meals_table")
    void deleteAll();

    @Query("SELECT * From meals_table WHERE day LIKE:day")
    Observable<List<Meal>> getPlanMeals(String day);

    @Query("SELECT * From meals_table WHERE day LIKE:day")
    Flowable<List<Meal>> getPlansMealsFromDay(String day);



}
