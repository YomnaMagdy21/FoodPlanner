package com.example.foodplanner.plan.presenter;

import com.example.foodplanner.model.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface PlanPresenter {

    public void getPlan(String day);
    public void deleteMealFromDay(Meal meal);
}
