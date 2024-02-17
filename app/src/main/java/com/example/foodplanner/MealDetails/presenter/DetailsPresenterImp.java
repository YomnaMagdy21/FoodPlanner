package com.example.foodplanner.MealDetails.presenter;

import android.content.Context;

import androidx.annotation.NonNull;

import com.example.foodplanner.MealDetails.view.DetailsView;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.plan.model.MealPlan;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailsPresenterImp implements DetailsPresenter {

    DetailsView _view;
    MealRepository _repo;
    Firebase firebase;
    Context context;
    public DetailsPresenterImp(Context context, MealRepository _repo,DetailsView _view) {
        this.context=context;
        this._view = _view;
        this._repo = _repo;
        firebase=new Firebase();
    }

    @Override
    public void getDetails(String name) {
        Observable<MealResponse> observable= _repo.getMealByName(name);

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse categoryResponse) {
                        _view.showMealDetails(categoryResponse.getMeals());
                       // _view.handlePlan(categoryResponse.getMeals());

                    }

                    @Override
                    public void onError(@NonNull Throwable e) {


                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void addToFav(Meal meal) {
      _repo.insertMeal(meal);

          firebase.syncFavorites(meal,context);

    }

    @Override
    public void addPlan(Meal meal) {
        _repo.insertMeal(meal);
        firebase.insertInPlan(meal,context);
    }

    @Override
    public void removeFromFav(Meal meal) {
        _repo.deleteMeal(meal);
        firebase.removeMealFromFav(meal);
    }


}
