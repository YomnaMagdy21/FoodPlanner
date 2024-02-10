package com.example.foodplanner.database;

import android.content.Context;

import com.example.foodplanner.home.view.HomeFragment;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

public class MealLocalDataSourceImp {

    private static MealLocalDataSourceImp localSource=null;

    private  MealLocalDataSourceImp(){
//
//        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
//        productDAO= db.productDAO();
//        storedProducts=productDAO.getAllProduct();
    }
    public static MealLocalDataSourceImp getInstance() {
        if(localSource==null){
            localSource=new MealLocalDataSourceImp();
        }
        return localSource;
    }
}
