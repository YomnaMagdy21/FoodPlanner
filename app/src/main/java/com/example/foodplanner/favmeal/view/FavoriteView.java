package com.example.foodplanner.favmeal.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface FavoriteView {

    public void showFav(List<Meal> meals);
    public void removeMeal(Meal meal);
}
