package com.example.foodplanner.network;

import com.example.foodplanner.area.modelArea.AreaResponse;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.model.MealResponse;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

  @GET("search.php?s")
  Observable<MealResponse> getMeals();

  @GET("categories.php")
  Observable<CategoryResponse> getCategories();

  @GET("list.php?a=list")
  Observable<AreaResponse> getArea();
}
