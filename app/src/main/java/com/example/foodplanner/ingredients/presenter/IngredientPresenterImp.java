package com.example.foodplanner.ingredients.presenter;

import com.example.foodplanner.ingredients.modelIngredients.IngredientResponse;
import com.example.foodplanner.ingredients.view.IngredientView;
import com.example.foodplanner.model.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class IngredientPresenterImp implements IngredientPresenter {
    private IngredientView _view;
    private MealRepository _repo;

    public IngredientPresenterImp(IngredientView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getIngredient() {
        Observable<IngredientResponse> observable = _repo.getAllIngredients();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<IngredientResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull IngredientResponse areaResponse) {
                        _view.showIngredients(areaResponse.getMeals());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
