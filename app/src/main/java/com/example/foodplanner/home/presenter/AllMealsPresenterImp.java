package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class AllMealsPresenterImp implements AllMealPresenter, NetworkCallback {
    private AllMealView _view;
    private MealRepository _repo;

    public AllMealsPresenterImp(AllMealView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getMeals() {
        _repo.getAllMeals(this);
    }

//    @Override
//    public void addToFav(Meal product) {
//        _repo.insertProduct(product);
//    }

    @Override
    public void onSuccessResult(List<Meal> products) {
        _view.showData(products);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);

    }
}
