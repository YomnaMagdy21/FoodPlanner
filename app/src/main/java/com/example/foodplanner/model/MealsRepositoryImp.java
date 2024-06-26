package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.area.modelArea.AreaResponse;
import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.ingredients.modelIngredients.IngredientResponse;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.plan.model.MealPlan;


import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;

public class MealsRepositoryImp implements MealRepository{
    MealsRemoteDataSourceImp remoteSource;
    MealLocalDataSourceImp localDataSource;
    private static MealsRepositoryImp repo=null;

    public static MealsRepositoryImp getInstance(MealsRemoteDataSourceImp remoteSource,
                                                 MealLocalDataSourceImp localDataSource){
        if(repo==null){
            repo=new MealsRepositoryImp(remoteSource,localDataSource);
        }
        return repo;
    }

    private MealsRepositoryImp(MealsRemoteDataSourceImp remoteSource, MealLocalDataSourceImp localDataSource) {
        this.remoteSource = remoteSource;
        this.localDataSource = localDataSource;
    }

    public Flowable<List<Meal>> getStoredMeals(){return localDataSource.getAllStoredMeals();}

    public Observable<MealResponse> getAllMeals(){//NetworkCallback networkCallback
        return remoteSource.makeNetworkCall();

    }

    public Observable<CategoryResponse>  getAllCategories(){
        return remoteSource.categoryCall();
    }

    @Override
    public Observable<AreaResponse> getAllCountries() {
        return remoteSource.areaCall();
    }

    @Override
    public Observable<IngredientResponse> getAllIngredients() {
        return remoteSource.ingredientCall();
    }

    @Override
    public Observable<MealResponse> getMealByName(String name) {
        return remoteSource.getMealByName(name);
    }

    @Override
    public Observable<MealResponse> getMealByCategory(String name) {
        return remoteSource.categoryMealCall(name);
    }

    @Override
    public Observable<MealResponse> getMealByArea(String name) {
        return remoteSource.areaMealCall(name);
    }


    public void insertMeal(Meal meal){
        localDataSource.insert(meal);
    }

    public void deleteMeal(Meal meal){
        localDataSource.delete(meal);
    }

    @Override
    public void deleteEverMeal() {
        localDataSource.deleteAllData();
    }

//    @Override
//    public void insertP(MealPlan meal) {
//        localDataSource.insertPlan(meal);
//    }
//
//    @Override
//    public void deleteP(MealPlan meal) {
//         localDataSource.deletePlan(meal);
//    }

    @Override
    public Observable<List<Meal>> getPlan(String day) {
       return localDataSource.getPlanMealsByDay(day);
    }


}