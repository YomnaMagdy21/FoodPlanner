package com.example.foodplanner.home.view;

import com.example.foodplanner.model.Meal;

import java.util.List;

public interface AllMealView {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);
   // public void addProduct(Product product);
}
