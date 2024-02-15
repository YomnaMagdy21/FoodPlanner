package com.example.foodplanner.meal.view;

import com.example.foodplanner.categories.modelC.Categories;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface MealView {

    public void showMeals(List<Meal> categories);
    public void showErrMsg(String error);
}
