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
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);




//        recyclerViewSun=view.findViewById(R.id.recViewSun);
//        recyclerViewMon=view.findViewById(R.id.recViewMon);
//        recyclerViewTue=view.findViewById(R.id.recViewTue);
//        recyclerViewWed=view.findViewById(R.id.recViewWed);
//        recyclerViewThu=view.findViewById(R.id.recViewThu);
//        recyclerViewFri=view.findViewById(R.id.recViewFri);
//        recyclerViewSat=view.findViewById(R.id.recViewSat);

        // cardView=view.findViewById(R.id.cardView);
//        linearLayoutManager=new LinearLayoutManager(view.getContext());
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerViewSun.setLayoutManager(linearLayoutManager);
//        linearLayoutManager2=new LinearLayoutManager(view.getContext());
//        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerViewMon.setLayoutManager(linearLayoutManager2);
//        linearLayoutManager3=new LinearLayoutManager(view.getContext());
//        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewTue.setLayoutManager(linearLayoutManager3);
//        linearLayoutManager4=new LinearLayoutManager(view.getContext());
//        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewWed.setLayoutManager(linearLayoutManager4);
//        linearLayoutManager5=new LinearLayoutManager(view.getContext());
//        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewThu.setLayoutManager(linearLayoutManager5);
//        linearLayoutManager6=new LinearLayoutManager(view.getContext());
//        linearLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewFri.setLayoutManager(linearLayoutManager6);
//        linearLayoutManager7=new LinearLayoutManager(view.getContext());
//        linearLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);
//
//        recyclerViewSat.setLayoutManager(linearLayoutManager7);


        planAdapter=new PlanAdapter(view.getContext(),new ArrayList<>(),this);

        planPresenter=new PlanPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"NULL")));
       // recyclerViewSun.setAdapter(planAdapter);
        mealsList=new ArrayList<Meal>();
        Observable<List<Meal>> observable=Observable.fromArray(mealsList);
       // planPresenter.getPlan(day);
//        Bundle bundle=getArguments();
//        if(bundle!=null) {
//            day= bundle.getString("plan");
//            Log.i(TAG, "onCreate1: "+ day);
//        }
//        Log.i(TAG, "onCreate2: "+ day);
//
//
//        if (getArguments() != null) {
//            // Retrieve the plan string from the arguments bundle
//            day = getArguments().getString("plan");
//            Log.i(TAG, "onCreate11: " + day);
//        } else {
//            // Handle the case where arguments are null
//            Log.i(TAG, "No arguments found in bundle11");
//        }



        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Sunday")));
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
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Monday")));
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
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Tuesday")));
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
                   // if("Wednesday".equals(MealDetailsActivity.d)) {
                        planPresenter.getPlan("Wednesday");
                        recyclerView.setAdapter(planAdapter);
                        getAllPlans(observable);
                   // }
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
                        planAdapter.setList(meal);
                        planAdapter.notifyDataSetChanged();
//

                    });


        }
//        meals.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Observer<List<Meal>>() {
//                    @Override
//                    public void onSubscribe(Disposable d) {
//                        // You can ignore this method
//                    }
//
//                    @Override
//                    public void onNext(List<Meal> mealList) {
//                        // Check if the list is not empty
//                        if (!mealList.isEmpty()) {
//                            // Retrieve the first meal from the list
//                            Meal meal = mealList.get(0);
//                            Log.i(TAG, "onNext: "+meal.getDay()+" "+meal.getStrMeal());
//                                sunday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        //  planPresenter.getPlan("Sunday");
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Sunday")));
//                                        if(planPresenter != null) {
//                                            if (meal.getDay().equals("Sunday")) {
//                                                planPresenter.getPlan("Sunday");
//
//
//                                            }
//                                        }
//                                    }
//                                });
//
//
//
//                                monday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Monday")));
//                                        if(planPresenter != null) {
//                                            if (meal.getDay().equals("Monday")) {
//                                                planPresenter.getPlan("Monday");
//                                                recyclerViewMon.setAdapter(planAdapter);
//                                            }
//                                        }
//
//                                    }
//                                });
//
//
//                                tuesday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"Tuesday")));
//                                        if(planPresenter != null) {
//                                            if (meal.getDay().equals("Tuesday")) {
//                                                planPresenter.getPlan("Tuesday");
//                                                recyclerViewTue.setAdapter(planAdapter);
//                                            }
//                                        }
//
//                                    }
//                                });
//
//                            }
//                         else {
//                            // Handle the case when the list is empty
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        // Handle error if any occurs during data retrieval
//                        Log.e(TAG, "Error retrieving meals", e);
//
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        // You can ignore this method
//                    }
//                });


      //  return meals;

    }

    @Override
    public void getAllPlansMon(Observable<List<Meal>> meals) {
//        if (meals != null) {
//            meals.subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
//                    .subscribe(meal -> {
//
//                        monAdapter.setList(meal);
//                        monAdapter.notifyDataSetChanged();
//
//                    });
//        }
    }

    @Override
    public void deleteFromPlan(Meal meal) {

    }
}