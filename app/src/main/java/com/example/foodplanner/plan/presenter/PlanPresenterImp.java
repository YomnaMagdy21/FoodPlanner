package com.example.foodplanner.plan.presenter;

import com.example.foodplanner.firebase.Firebase;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealRepository;
import com.example.foodplanner.plan.view.PlanView;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterImp implements PlanPresenter{

    PlanView _view;
    MealRepository _repo;
    Firebase firebase;

    public PlanPresenterImp(PlanView _view, MealRepository _repo) {
        this._view = _view;
        this._repo = _repo;
        firebase=new Firebase();
    }


    @Override
    public void getPlan(String day) {
//        _repo.getPlan(day)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(meals -> {
//                    _view.getAllPlans((Observable<List<Meal>>) meals);
//                }, throwable -> {
//                    // Handle error
//                });
         _view.getAllPlans(_repo.getPlan(day));
       //  _view.getAllPlansMon(_repo.getPlan(day));
    }

    @Override
    public void deleteMealFromDay(Meal meal) {
        _repo.deleteMeal(meal);
        firebase.removeMealFromPlan(meal);

    }
}
