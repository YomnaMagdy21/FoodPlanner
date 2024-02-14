package com.example.foodplanner.area.view;

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
import com.example.foodplanner.area.modelArea.Area;
import com.example.foodplanner.area.presenter.AreaPresenter;
import com.example.foodplanner.area.presenter.AreaPresenterImp;
import com.example.foodplanner.categories.presenter.CategoriesPresenter;
import com.example.foodplanner.categories.presenter.CategoriesPresenterImp;
import com.example.foodplanner.categories.view.CategoriesAdapter;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;

import java.util.ArrayList;
import java.util.List;


public class AreaFragment extends Fragment implements AreaView{
    AreaAdapter homeAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    AreaPresenter allMealPresenter;
    EditText search;


    public AreaFragment() {
        // Required empty public constructor
    }

    public static AreaFragment newInstance(String param1, String param2) {
        AreaFragment fragment = new AreaFragment();
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
        View view=inflater.inflate(R.layout.fragment_area, container, false);

        search=view.findViewById(R.id.searchEdit);
        recyclerView=view.findViewById(R.id.mealSearch);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        homeAdapter=new AreaAdapter(view.getContext(),new ArrayList<>(),this);

        allMealPresenter= new AreaPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));

        recyclerView.setAdapter(homeAdapter);
        allMealPresenter.getArea();

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
    public void showCountries(List<Area> areas) {
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