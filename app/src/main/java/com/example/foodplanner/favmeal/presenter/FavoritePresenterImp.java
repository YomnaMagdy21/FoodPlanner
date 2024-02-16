package com.example.foodplanner.favmeal.presenter;

import com.example.foodplanner.favmeal.view.FavoriteView;
import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableSubscriber;

public class FavoritePresenterImp implements FavoritePresenter{
    private FavoriteView _view;
    private MealRepository _repo;
    Flowable<List<Meal>> favProducts;
    Firebase firebase;


    public FavoritePresenterImp(FavoriteView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
        firebase=new Firebase();

    }


    @Override
    public void removeFromFav(Meal meal) {
        _repo.deleteMeal(meal);
        firebase.removeMealFromFav(meal);
    }

    @Override
    public Flowable<List<Meal>> getFavMeal() {

        return _repo.getStoredMeals();

    }

    @Override
    public void showFavMeal(List<Meal> meals) {
        _view.showFav(meals);
    }

}
