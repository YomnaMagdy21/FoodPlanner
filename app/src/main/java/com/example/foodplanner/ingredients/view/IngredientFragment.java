package com.example.foodplanner.ingredients.view;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.presenter.AllMealPresenter;
import com.example.foodplanner.home.presenter.AllMealsPresenterImp;
import com.example.foodplanner.ingredients.modelIngredients.Ingredients;
import com.example.foodplanner.ingredients.presenter.IngredientPresenter;
import com.example.foodplanner.ingredients.presenter.IngredientPresenterImp;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.search.view.SearchAdepter;

import java.util.ArrayList;
import java.util.List;


public class IngredientFragment extends Fragment implements IngredientView{
    IngredientAdapter homeAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    IngredientPresenter allMealPresenter;
    EditText search;


    public IngredientFragment() {
        // Required empty public constructor
    }

       public static IngredientFragment newInstance(String param1, String param2) {
        IngredientFragment fragment = new IngredientFragment();
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
        View view=inflater.inflate(R.layout.fragment_ingredient, container, false);

        search=view.findViewById(R.id.searchEdit);
        recyclerView=view.findViewById(R.id.mealSearch);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter=new IngredientAdapter(view.getContext(),new ArrayList<>(),this);

        allMealPresenter= new IngredientPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));

        recyclerView.setAdapter(homeAdapter);
        allMealPresenter.getIngredient();

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                //  Log.i(TAG, "beforeTextChanged: ");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //   Log.i(TAG, "onTextChanged: ");

            }

            @Override
            public void afterTextChanged(Editable s) {
                //  Log.i(TAG, "afterTextChanged: ");
                homeAdapter.getFilter().filter(s);

            }
        });

        return view;
    }

    @Override
    public void showIngredients(List<Ingredients> areas) {
        homeAdapter.setList(areas);
        homeAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occurred");
        AlertDialog alertDialog=builder.create();
        alertDialog.show();

    }
}