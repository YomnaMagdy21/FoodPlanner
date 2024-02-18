package com.example.foodplanner.plan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.MealDetails.view.DetailsView;
import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.favmeal.view.FavoriteAdapter;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.plan.presenter.PlanPresenter;
import com.example.foodplanner.plan.presenter.PlanPresenterImp;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class PlanFragment extends Fragment implements PlanView{

    private static final String TAG="TAG";

    Button sunday,monday,tuesday,wednesday,thursday,friday,saturday;
    PlanPresenter planPresenter;
    RecyclerView recyclerView,recyclerViewSun,recyclerViewMon,recyclerViewTue,recyclerViewWed,recyclerViewThu,recyclerViewFri,recyclerViewSat;
    LinearLayoutManager linearLayoutManager, linearLayoutManager2,linearLayoutManager3,linearLayoutManager4,linearLayoutManager5,linearLayoutManager6,linearLayoutManager7;
    PlanAdapter planAdapter;
    MonAdapter monAdapter;
    Observable<List<Meal>> mealList;
    Meal meal;
    List<Meal> mealsList;
    String day;


    public PlanFragment() {
        // Required empty public constructor
    }

       public static PlanFragment newInstance(String day) {
           PlanFragment fragment = new PlanFragment();
           Bundle args = new Bundle();
           args.putString("plan", day);
           fragment.setArguments(args);
           return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            // Retrieve the plan string from the arguments bundle
            day = getArguments().getString("plan");
            Log.i(TAG, "onCreate1: " + day);
        } else {
            // Handle the case where arguments are null
            Log.i(TAG, "No arguments found in bundle");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_plan, container, false);
        sunday=view.findViewById(R.id.sunday);
        monday=view.findViewById(R.id.monday);
        tuesday=view.findViewById(R.id.tuesday);
        wednesday=view.findViewById(R.id.wednesday);
        thursday=view.findViewById(R.id.thursday);
        friday=view.findViewById(R.id.friday);
        saturday=view.findViewById(R.id.saturday);
        recyclerView=view.findViewById(R.id.recViewPlans);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(linearLayoutManager);






        planAdapter=new PlanAdapter(view.getContext(),new ArrayList<>(),this);

        planPresenter=new PlanPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
       // recyclerViewSun.setAdapter(planAdapter);
        mealsList=new ArrayList<Meal>();
        Observable<List<Meal>> observable=Observable.fromArray(mealsList);



        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                  //  if("Sunday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Sunday");
                        Log.i(TAG, "onCreate3: " + day);
                        recyclerView.setAdapter(planAdapter);
                  //  }
                     getAllPlans(observable);



                }

            }
        });
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {

                   // if("Monday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Monday");
                        recyclerView.setAdapter(planAdapter);
                      //  getAllPlansMon(observable);
                   // }

                }

            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                  //  if("Tuesday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Tuesday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
                  //  }
                }

            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    if("Wednesday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Wednesday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
                    }
                }

            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                   // if("Thursday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Thursday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
                   // }
                }

            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           //     planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                  //  if("Friday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Friday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
                   // }
                }

            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
//                    if("Saturday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Saturday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
//                    }
                }

            }
        });


        return view;
    }

    @Override
    public void getAllPlans(Observable<List<Meal>> meals) {
        if (meals != null) {
            meals.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(meal -> {
                        planAdapter.setList((List<Meal>)meal);
                        planAdapter.notifyDataSetChanged();
//

                    });


        }
    }

    @Override
    public void deleteFromPlan(Meal meal) {
        planPresenter.deleteMealFromDay(meal);
    }
}