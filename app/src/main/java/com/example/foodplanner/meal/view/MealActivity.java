package com.example.foodplanner.meal.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.meal.presenter.MealPresenter;
import com.example.foodplanner.meal.presenter.MealPresenterImp;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

public class MealActivity extends AppCompatActivity implements MealView {
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager ;
    MealAdapter homeAdapter;
    MealPresenter mealPresenter;
    String mealName,areaName;
    TextView categoryName;
    List<Meal> meals;
    Meal meal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);

        recyclerView=findViewById(R.id.recView);
        categoryName=findViewById(R.id.categoryName);

        // cardView=view.findViewById(R.id.cardView);
        linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter=new MealAdapter(this,new ArrayList<>(),this);

        mealPresenter= new MealPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(this)));

        recyclerView.setAdapter(homeAdapter);
        Bundle bundle=getIntent().getExtras();
        mealName=bundle.getString("Name");
        areaName=bundle.getString("Area");


        mealPresenter.getMealByCategory(mealName);
        mealPresenter.getMealByArea(areaName);

    }

    @Override
    public void showMeals(List<Meal> categories) {
        homeAdapter.setList(categories);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {

    }
}