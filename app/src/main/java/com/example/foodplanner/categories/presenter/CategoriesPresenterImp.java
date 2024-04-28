package com.example.foodplanner.categories.presenter;

import androidx.annotation.NonNull;

import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.model.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class CategoriesPresenterImp implements CategoriesPresenter {
    private CategoriesView _view;
    private MealRepository _repo;

    public CategoriesPresenterImp(CategoriesView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getCategories() {
        Observable<CategoryResponse> observable= _repo.getAllCategories();

        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<CategoryResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull CategoryResponse categoryResponse) {
                        _view.showCategories(categoryResponse.getCategories());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        _view.showErrMsg(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
       // _repo.getAllCategories();
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
