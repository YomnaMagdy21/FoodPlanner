package com.example.foodplanner.favmeal.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoriteView {

    public void showFav(Flowable<List<Meal>> meals);
    public void removeMeal(Meal meal);
}
