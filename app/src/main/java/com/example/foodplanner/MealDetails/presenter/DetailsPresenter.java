package com.example.foodplanner.MealDetails.presenter;

import com.example.foodplanner.model.Meal;

public interface DetailsPresenter {
    public void getDetails(String name);
    public void addToFav(Meal meal);
    public void addPlan(Meal meal);
}
