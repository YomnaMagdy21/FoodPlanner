package com.example.foodplanner.categories.view;

import com.example.foodplanner.categories.modelC.Categories;
import com.example.foodplanner.model.Meal;

import java.util.List;

public interface CategoriesView {

    public void showCategories(List<Categories> categories);
    public void showErrMsg(String error);
}
