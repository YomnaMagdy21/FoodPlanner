package com.example.foodplanner.model;



import com.example.foodplanner.area.modelArea.AreaResponse;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.ingredients.modelIngredients.IngredientResponse;

import io.reactivex.rxjava3.core.Observable;

public interface MealRepository {

    public Observable<MealResponse> getAllMeals();//NetworkCallback networkCallback
    public Observable<CategoryResponse> getAllCategories();
    public Observable<AreaResponse> getAllCountries();
    public Observable<IngredientResponse> getAllIngredients();
    public  Observable<MealResponse> getMealByName(String name);
    public Observable<MealResponse> getMealByCategory(String name);
    public Observable<MealResponse> getMealByArea(String name);


//    public void insertMeal(Meal meal);
//    public void deleteMeal(Meal meal);
}
