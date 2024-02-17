package com.example.foodplanner.home.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;


import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AllMealsPresenterImp implements AllMealPresenter {//,NetworkCallback{
    private AllMealView _view;
    private MealRepository _repo;

    public AllMealsPresenterImp(AllMealView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMeals() {
        Observable<MealResponse> observable= _repo.getAllMeals();

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse mealResponse) {
                        _view.showData(mealResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

      //  _repo.getAllMeals();
    }

    @Override
    public void deleteData() {
        _repo.deleteEverMeal();
    }

//    @Override
//    public void addToFav(Meal product) {
//        _repo.insertProduct(product);
//    }

//    @Override
//    public void onSuccessResult(List<Meal> products) {
//        _view.showData(products);
//
//    }
//
//    @Override
//    public void onFailureResult(String errorMsg) {
//        _view.showErrMsg(errorMsg);
//
//    }
}
