package com.example.foodplanner.plan.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public interface PlanView {
    public void  getAllPlans(Observable<List<Meal>> meal);
    public void deleteFromPlan(Meal meal);
}
