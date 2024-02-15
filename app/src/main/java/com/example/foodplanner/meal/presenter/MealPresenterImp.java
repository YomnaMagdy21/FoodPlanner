package com.example.foodplanner.meal.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.meal.view.MealView;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class MealPresenterImp implements MealPresenter{

    private MealView _view;
    private MealRepository _repo;

    public MealPresenterImp(MealView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void getMealByCategory(String name) {
        Observable<MealResponse> observable= _repo.getMealByCategory(name);

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse categoryResponse) {
                        _view.showMeals(categoryResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void getMealByArea(String name) {
        Observable<MealResponse> observable= _repo.getMealByArea(name);

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<MealResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull MealResponse categoryResponse) {
                        _view.showMeals(categoryResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
