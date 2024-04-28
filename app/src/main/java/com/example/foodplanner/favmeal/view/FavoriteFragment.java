package com.example.foodplanner.favmeal.view;

import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;
import com.example.foodplanner.area.presenter.AreaPresenterImp;
import com.example.foodplanner.area.view.AreaAdapter;
import com.example.foodplanner.categories.presenter.CategoriesPresenterImp;
import com.example.foodplanner.categories.view.CategoriesAdapter;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.favmeal.presenter.FavoritePresenter;
import com.example.foodplanner.favmeal.presenter.FavoritePresenterImp;
import com.example.foodplanner.home.presenter.AllMealPresenter;
import com.example.foodplanner.home.presenter.AllMealsPresenterImp;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class FavoriteFragment extends Fragment implements FavoriteView{

    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager ;
    FavoriteAdapter homeAdapter;
    FavoritePresenter allMealPresenter;
    Flowable<List<Meal>> mealList;




    public FavoriteFragment() {
        // Required empty public constructor
    }


    public static FavoriteFragment newInstance(String param1, String param2) {
        FavoriteFragment fragment = new FavoriteFragment();
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
        View view=inflater.inflate(R.layout.fragment_favorite, container, false);

        recyclerView=view.findViewById(R.id.recViewFav);

        // cardView=view.findViewById(R.id.cardView);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter=new FavoriteAdapter(view.getContext(),new ArrayList<>(),this);

        allMealPresenter= new FavoritePresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));

        recyclerView.setAdapter(homeAdapter);
        allMealPresenter.getFavMeal();
        mealList=allMealPresenter.getFavMeal();
        allMealPresenter.showFavMeal(mealList);




        return view;
    }

    @Override
    public void showFav(Flowable<List<Meal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(meal->{
                    homeAdapter.setList((List<Meal>) meal);
                    homeAdapter.notifyDataSetChanged();
                });
    }

    @Override
    public void removeMeal(Meal meal) {
        allMealPresenter.removeFromFav(meal);
    }
}