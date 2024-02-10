package com.example.foodplanner.network;

import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface MealService {

  @GET("search.php?s")
  Call<MealResponse> getMeals();

  @GET("categories.php")
  Call<MealResponse> getCategories();

  @GET("list.php?a=list")
  Call<MealResponse> getArea();
}
