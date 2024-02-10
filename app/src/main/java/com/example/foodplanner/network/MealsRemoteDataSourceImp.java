package com.example.foodplanner.network;

import android.util.Log;

import com.example.foodplanner.model.MealResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsRemoteDataSourceImp {


    private static final String TAG="RESPONSE";
    private static final String BASE_URL="https://www.themealdb.com/api/json/v1/1/";
    private static MealsRemoteDataSourceImp client=null;
    private MealService mealService;

    private MealsRemoteDataSourceImp(){
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL).build();
        mealService=retrofit.create(MealService.class);

    }

    public static MealsRemoteDataSourceImp getInstance(){
        if (client==null){
            client=new MealsRemoteDataSourceImp();
        }
        return client;
    }

    public void makeNetworkCall(NetworkCallback networkCallback){

        Call<MealResponse> call=mealService.getMeals();
        call.enqueue(new Callback<MealResponse>() {
            @Override
            public void onResponse(Call<MealResponse> call, Response<MealResponse> response) {
                networkCallback.onSuccessResult(response.body().meals);
                Log.i(TAG, "onResponseeeeeee: "+response.body());

            }

            @Override
            public void onFailure(Call<MealResponse> call, Throwable t) {
                networkCallback.onFailureResult(t.getMessage());

            }
        });

    }
}
