package com.example.foodplanner.area.presenter;

import com.example.foodplanner.area.modelArea.AreaResponse;
import com.example.foodplanner.area.view.AreaView;
import com.example.foodplanner.model.MealRepository;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class AreaPresenterImp implements AreaPresenter {
    private AreaView _view;
    private MealRepository _repo;

    public AreaPresenterImp(AreaView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getArea() {
        Observable<AreaResponse> observable= _repo.getAllCountries();
        observable
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Observer<AreaResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {

                            }

                            @Override
                            public void onNext(@NonNull AreaResponse areaResponse) {
                                 _view.showCountries(areaResponse.getAreaList());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {

                            }

                            @Override
                            public void onComplete() {

                            }
                        });

      //  _repo.getAllMeals();
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
