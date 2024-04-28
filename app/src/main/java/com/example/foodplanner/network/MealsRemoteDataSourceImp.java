package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.area.modelArea.AreaResponse;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.ingredients.modelIngredients.IngredientResponse;
import com.example.foodplanner.model.MealResponse;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp {


    private static final String TAG = "RESPONSE";
    private static final String BASE_URL = "https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSourceImp client = null;
    private MealService mealService;

    private MealsRemoteDataSourceImp() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        mealService = retrofit.create(MealService.class);

    }

    public static MealsRemoteDataSourceImp getInstance() {
        if (client == null) {
            client = new MealsRemoteDataSourceImp();
        }
        return client;
    }

    public Observable<MealResponse> makeNetworkCall() {

        Observable<MealResponse> observable = mealService.getMeals();
        return observable.subscribeOn(Schedulers.io());

    }

    public Observable<CategoryResponse> categoryCall() {

        Observable<CategoryResponse> categoriesObservable = mealService.getCategories();
        return categoriesObservable.subscribeOn(Schedulers.io());

    }

    public Observable<AreaResponse> areaCall(){
        Observable<AreaResponse> areaObservable =mealService.getArea();
        return areaObservable.subscribeOn(Schedulers.io());
    }
    public Observable<IngredientResponse> ingredientCall(){
        Observable<IngredientResponse> areaObservable =mealService.getIngredients();
        return areaObservable.subscribeOn(Schedulers.io());
    }
    public Observable<MealResponse> getMealByName(String name){
        Observable<MealResponse> areaObservable =mealService.getMealByName(name);
        return areaObservable.subscribeOn(Schedulers.io());
    }

    public Observable<MealResponse> categoryMealCall(String name) {

        Observable<MealResponse> categoriesObservable = mealService.getMealByCategory(name);
        return categoriesObservable.subscribeOn(Schedulers.io());

    }
    public Observable<MealResponse> areaMealCall(String name) {

        Observable<MealResponse> categoriesObservable = mealService.getMealByArea(name);
        return categoriesObservable.subscribeOn(Schedulers.io());

    }



}