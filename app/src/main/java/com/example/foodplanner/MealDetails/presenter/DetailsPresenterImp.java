package com.example.foodplanner.MealDetails.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.MealDetails.view.DetailsView;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailsPresenterImp implements DetailsPresenter {

    DetailsView _view;
    MealRepository _repo;
    Firebase firebase;
    public DetailsPresenterImp(DetailsView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
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
        firebase.syncFavorites( meal);
    }


}
