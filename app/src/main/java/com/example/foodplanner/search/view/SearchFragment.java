package com.example.foodplanner.search.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.presenter.AllMealPresenter;
import com.example.foodplanner.home.presenter.AllMealsPresenterImp;
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealResponse;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;


public class SearchFragment extends Fragment {

    Button searchMeal,searchCategory,searchArea,searchIngredients;


    public SearchFragment() {
        // Required empty public constructor
    }

       public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        View view=inflater.inflate(R.layout.fragment_search, container, false);

   searchMeal=view.findViewById(R.id.btnMealSearch);
   searchCategory=view.findViewById(R.id.btnCategorySearch);
   searchArea=view.findViewById(R.id.btnAreaSearch);
   searchIngredients=view.findViewById(R.id.btnIngredientsSearch);
   searchMeal.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Navigation.findNavController(view).navigate(R.id.searchMealFragment);

       }
   });
   searchCategory.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           Navigation.findNavController(view).navigate(R.id.categoriesFragment);

       }
   });
        searchArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.areaFragment);

            }
        });
        searchIngredients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.ingredientFragment);

            }
        });


        return view;
    }


}