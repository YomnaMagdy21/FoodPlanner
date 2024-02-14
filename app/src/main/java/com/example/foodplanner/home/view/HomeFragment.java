package com.example.foodplanner.home.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.area.modelArea.Area;
import com.example.foodplanner.area.presenter.AreaPresenterImp;
import com.example.foodplanner.area.view.AreaAdapter;
import com.example.foodplanner.area.view.AreaView;
import com.example.foodplanner.categories.modelC.Categories;
import com.example.foodplanner.categories.presenter.CategoriesPresenter;
import com.example.foodplanner.categories.presenter.CategoriesPresenterImp;
import com.example.foodplanner.categories.view.CategoriesAdapter;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.presenter.AllMealPresenter;
import com.example.foodplanner.home.presenter.AllMealsPresenterImp;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment implements AllMealView, CategoriesView, AreaView {

    RecyclerView recyclerView,recyclerViewCategories,recyclerViewArea;
    LinearLayoutManager linearLayoutManager ,linearLayoutManagerCategories,linearLayoutManagerArea;
    HomeAdapter homeAdapter;
    AllMealPresenter allMealPresenter;
     MealsRemoteDataSourceImp remoteDataSource;
     CardView cardView;
     CategoriesAdapter categoriesAdapter;
     CategoriesPresenterImp categoriesPresenter;
     AreaPresenterImp areaPresenterImp;
     AreaAdapter areaAdapter;


    public HomeFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=view.findViewById(R.id.recView);
        recyclerViewCategories=view.findViewById(R.id.categoriesRecView);
        recyclerViewArea=view.findViewById(R.id.areaRecView);

       // cardView=view.findViewById(R.id.cardView);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
     homeAdapter=new HomeAdapter(view.getContext(),new ArrayList<>(),this);

        allMealPresenter= new AllMealsPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));

        recyclerView.setAdapter(homeAdapter);
        allMealPresenter.getMeals();

        linearLayoutManagerCategories=new LinearLayoutManager(view.getContext());
        linearLayoutManagerCategories.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewCategories.setLayoutManager(linearLayoutManagerCategories);
        categoriesAdapter=new CategoriesAdapter(view.getContext(),new ArrayList<>(),this);
        categoriesPresenter= new CategoriesPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));

        recyclerViewCategories.setAdapter(categoriesAdapter);
        categoriesPresenter.getCategories();

        linearLayoutManagerArea=new LinearLayoutManager(view.getContext());
        linearLayoutManagerArea.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewArea.setLayoutManager(linearLayoutManagerArea);
        areaAdapter=new AreaAdapter(view.getContext(),new ArrayList<>(),this);
        areaPresenterImp= new AreaPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));

        recyclerViewArea.setAdapter(areaAdapter);
        areaPresenterImp.getArea();



        return view;
    }




    @Override
    public void showData(List<Meal> meals) {
        homeAdapter.setList(meals);
        homeAdapter.notifyDataSetChanged();

//        areaAdapter.setList(meals);
//        areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCategories(List<Categories> categories) {
        categoriesAdapter.setList(categories);
        categoriesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showCountries(List<Area> areas) {
        areaAdapter.setList(areas);
       areaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occurred");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }

    @Override
    public void navigateToDetails(Meal meal) {
       // startActivity(new Intent(getContext(), MealDetailsActivity.class));

    }
}