package com.example.foodplanner.meal.view;

import static android.content.Intent.getIntent;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.presenter.AllMealsPresenterImp;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.meal.presenter.MealPresenter;
import com.example.foodplanner.meal.presenter.MealPresenterImp;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;


public class MealFragment extends Fragment implements MealView{

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager ;
    HomeAdapter homeAdapter;
    MealPresenter mealPresenter;
    String mealName;

    public MealFragment() {
        // Required empty public constructor
    }

    public static MealFragment newInstance(String param1, String param2) {
        MealFragment fragment = new MealFragment();
        Bundle args = new Bundle();
         fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_meal, container, false);

//        recyclerView=view.findViewById(R.id.areaRecView);
//
//        // cardView=view.findViewById(R.id.cardView);
//        linearLayoutManager=new LinearLayoutManager(view.getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        homeAdapter=new HomeAdapter(view.getContext(),new ArrayList<>(),this);
//
//        mealPresenter= new MealPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));
//
//        recyclerView.setAdapter(homeAdapter);
//
//
//        mealPresenter.getMealByCategory();
        return view;
    }

    @Override
    public void showMeals(List<Meal> categories) {

    }

    @Override
    public void showErrMsg(String error) {

    }
}