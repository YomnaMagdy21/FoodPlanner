package com.example.foodplanner.ingredients.view;

import com.example.foodplanner.area.modelArea.Area;
import com.example.foodplanner.ingredients.modelIngredients.Ingredients;

import java.util.List;

public interface IngredientView {
    public void showIngredients(List<Ingredients> areas);
    public void showErrMsg(String error);
}
