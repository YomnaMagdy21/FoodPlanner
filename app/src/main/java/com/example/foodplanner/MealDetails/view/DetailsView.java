package com.example.foodplanner.MealDetails.view;

import com.example.foodplanner.model.Meal;
import com.example.foodplanner.plan.model.MealPlan;

import java.util.List;

public interface DetailsView {

    public void showMealDetails(List<Meal> mealList);
    public void handlePlan(List<MealPlan> mealList);
    //public  void addMealToFav(Meal meal);
}
