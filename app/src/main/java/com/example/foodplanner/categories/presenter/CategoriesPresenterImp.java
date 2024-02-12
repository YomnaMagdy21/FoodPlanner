package com.example.foodplanner.categories.presenter;

import com.example.foodplanner.home.presenter.AllMealPresenter;
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

public class CategoriesPresenterImp implements CategoriesPresenter, NetworkCallback {
    private AllMealView _view;
    private MealRepository _repo;

    public CategoriesPresenterImp(AllMealView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getCategories() {
      //  _repo.getAllCategories(this);
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
