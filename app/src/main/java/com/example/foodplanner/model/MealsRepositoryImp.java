package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.categories.modelC.CategoryResponse;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;


import java.util.List;

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

   // public LiveData<List<Meal>> getStoredProducts(){return localDataSource.getAllStoredProducts();}

    public Observable<MealResponse> getAllMeals(){//NetworkCallback networkCallback
        return remoteSource.makeNetworkCall();

    }

    public Observable<CategoryResponse>  getAllCategories(){
        return remoteSource.categoryCall();
    }



//    public void insertProduct(Meal product){
//        localDataSource.insert(product);
//    }
//
//    public void deleteProduct(Meal product){
//        localDataSource.delete(product);
//    }
}