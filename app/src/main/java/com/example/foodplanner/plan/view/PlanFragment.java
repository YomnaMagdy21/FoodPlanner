package com.example.foodplanner.plan.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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

    TextView sunday,monday,tuesday,wednesday,thursday,friday,saturday;
    PlanPresenter planPresenter;
    RecyclerView recyclerViewSun,recyclerViewMon,recyclerViewTue,recyclerViewWed,recyclerViewThu,recyclerViewFri,recyclerViewSat;
    LinearLayoutManager linearLayoutManager, linearLayoutManager2,linearLayoutManager3,linearLayoutManager4,linearLayoutManager5,linearLayoutManager6,linearLayoutManager7;
    PlanAdapter planAdapter;
    Observable<List<Meal>> mealList;
    Meal meal;


    public PlanFragment() {
        // Required empty public constructor
    }

       public static PlanFragment newInstance() {
        PlanFragment fragment = new PlanFragment();
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
        View view=inflater.inflate(R.layout.fragment_plan, container, false);
        sunday=view.findViewById(R.id.sunday);
        monday=view.findViewById(R.id.monday);
        tuesday=view.findViewById(R.id.tuesday);
        wednesday=view.findViewById(R.id.wednesday);
        thursday=view.findViewById(R.id.thursday);
        friday=view.findViewById(R.id.friday);
        saturday=view.findViewById(R.id.saturday);

        recyclerViewSun=view.findViewById(R.id.recViewSun);
        recyclerViewMon=view.findViewById(R.id.recViewMon);
        recyclerViewTue=view.findViewById(R.id.recViewTue);
        recyclerViewWed=view.findViewById(R.id.recViewWed);
        recyclerViewThu=view.findViewById(R.id.recViewThu);
        recyclerViewFri=view.findViewById(R.id.recViewFri);
        recyclerViewSat=view.findViewById(R.id.recViewSat);

        // cardView=view.findViewById(R.id.cardView);
        linearLayoutManager=new LinearLayoutManager(view.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewSun.setLayoutManager(linearLayoutManager);
        linearLayoutManager2=new LinearLayoutManager(view.getContext());
        linearLayoutManager2.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerViewMon.setLayoutManager(linearLayoutManager2);
        linearLayoutManager3=new LinearLayoutManager(view.getContext());
        linearLayoutManager3.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewTue.setLayoutManager(linearLayoutManager3);
        linearLayoutManager4=new LinearLayoutManager(view.getContext());
        linearLayoutManager4.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewWed.setLayoutManager(linearLayoutManager4);
        linearLayoutManager5=new LinearLayoutManager(view.getContext());
        linearLayoutManager5.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewThu.setLayoutManager(linearLayoutManager5);
        linearLayoutManager6=new LinearLayoutManager(view.getContext());
        linearLayoutManager6.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewFri.setLayoutManager(linearLayoutManager6);
        linearLayoutManager7=new LinearLayoutManager(view.getContext());
        linearLayoutManager7.setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerViewSat.setLayoutManager(linearLayoutManager7);


        planAdapter=new PlanAdapter(view.getContext(),new ArrayList<>(),this);

        planPresenter=new PlanPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
        recyclerViewSun.setAdapter(planAdapter);



        sunday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //  planPresenter.getPlan("Sunday");
                  planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                  planPresenter.getPlan("Sunday");


              }

            }
        });
        monday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Monday");
                    recyclerViewMon.setAdapter(planAdapter);
                }

            }
        });
        tuesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Tuesday");
                    recyclerViewTue.setAdapter(planAdapter);
                }

            }
        });
        wednesday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Wednesday");
                    recyclerViewWed.setAdapter(planAdapter);
                }

            }
        });
        thursday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Thursday");
                    recyclerViewThu.setAdapter(planAdapter);
                }

            }
        });
        friday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Friday");
                    recyclerViewFri.setAdapter(planAdapter);
                }

            }
        });
        saturday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
                if(planPresenter != null) {
                    planPresenter.getPlan("Saturday");
                    recyclerViewSat.setAdapter(planAdapter);
                }

            }
        });


        return view;
    }

    @Override
    public void getAllPlans(Observable<List<Meal>> meals) {
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
//                            if(meal.getDay().equals("Sunday")){
//                                sunday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//
//                                        //  planPresenter.getPlan("Sunday");
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
//                                        if(planPresenter != null) {
//                                            planPresenter.getPlan("Sunday");
//
//
//                                        }
//
//                                    }
//                                });
//
//                            }
//                            else if (meal.getDay().equals("Monday")){
//                                monday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
//                                        if(planPresenter != null) {
//                                            planPresenter.getPlan("Monday");
//                                            recyclerViewMon.setAdapter(planAdapter);
//                                        }
//
//                                    }
//                                });
//
//                            } else if (meal.getDay().equals("Tuesday")) {
//                                tuesday.setOnClickListener(new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        planPresenter=new PlanPresenterImp(PlanFragment.this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext())));
//                                        if(planPresenter != null) {
//                                            planPresenter.getPlan("Tuesday");
//                                            recyclerViewTue.setAdapter(planAdapter);
//                                        }
//
//                                    }
//                                });
//
//                            }
//                        } else {
//                            // Handle the case when the list is empty
//                        }
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        // Handle error if any occurs during data retrieval
//                    }
//
//                    @Override
//                    public void onComplete() {
//                        // You can ignore this method
//                    }
//                });

        if (meals != null) {
            meals.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(meal -> {
                        planAdapter.setList(meal);
                        planAdapter.notifyDataSetChanged();
                    });
        }
      //  return meals;

    }

    @Override
    public void deleteFromPlan(Meal meal) {

    }
}