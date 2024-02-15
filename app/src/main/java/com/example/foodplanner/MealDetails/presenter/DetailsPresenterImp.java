package com.example.foodplanner.MealDetails.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.MealDetails.view.DetailsView;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.model.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class DetailsPresenterImp implements DetailsPresenter {

    DetailsView _view;
    MealRepository _repo;
    public DetailsPresenterImp(DetailsView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getDetails(String name) {
        _repo.getMealByName(name);
    }


}
