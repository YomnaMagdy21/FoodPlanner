package com.example.foodplanner.favmeal.presenter;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface FavoritePresenter {

    public void removeFromFav(Meal meal);
    public Flowable<List<Meal>> getFavMeal();
    public void showFavMeal(Flowable<List<Meal>> meals);
}
