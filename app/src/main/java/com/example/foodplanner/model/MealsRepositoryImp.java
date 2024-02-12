package com.example.foodplanner.model;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.network.NetworkCallback;

import java.util.List;

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

    public void getAllMeals(NetworkCallback networkCallback){
        remoteSource.makeNetworkCall(networkCallback);

    }

    public void getAllCategories(NetworkCallback networkCallback){
        remoteSource.categoryCall(networkCallback);
    }



//    public void insertProduct(Meal product){
//        localDataSource.insert(product);
//    }
//
//    public void deleteProduct(Meal product){
//        localDataSource.delete(product);
//    }
}