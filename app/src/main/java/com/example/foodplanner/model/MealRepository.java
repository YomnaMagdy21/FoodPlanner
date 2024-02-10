package com.example.foodplanner.model;

import com.example.foodplanner.network.NetworkCallback;

public interface MealRepository {

    public void getAllMeals(NetworkCallback networkCallback);
//    public void insertMeal(Meal meal);
//    public void deleteMeal(Meal meal);
}
