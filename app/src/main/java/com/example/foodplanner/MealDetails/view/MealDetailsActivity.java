package com.example.foodplanner.MealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenter;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenterImp;
import com.example.foodplanner.R;
import com.example.foodplanner.area.view.AreaAdapter;
import com.example.foodplanner.categories.presenter.CategoriesPresenterImp;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.home.view.HomeActivity;
import com.example.foodplanner.login.view.LoginFragment;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
import com.example.foodplanner.plan.model.MealPlan;
import com.example.foodplanner.plan.view.PlanFragment;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

public class MealDetailsActivity extends AppCompatActivity implements DetailsView{

    YouTubePlayerView video;
    String videoID;
    String nameOfMeal;
    TextView name,country,ingredients,steps;

    ImageView imgMeal;
    List<Meal> meals;
    Meal  meal;
    MealPlan mealPlan;
    DetailsPresenter detailsPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DetailsAdapter detailsAdapter;
    List<String> ingredientsList;
    ImageView fav,plan;
    DetailsView listener;
    Spinner dropdownSpinner;
     public static String d;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        video=findViewById(R.id.videoView);
        name=findViewById(R.id.nameMeal);
        country=findViewById(R.id.countryName);
        imgMeal=findViewById(R.id.mealImg);
        ingredients=findViewById(R.id.strIngredients);
        steps=findViewById(R.id.steps);
        fav=findViewById(R.id.imgFav);
        plan=findViewById(R.id.planImg);
        dropdownSpinner = findViewById(R.id.dropdown_spinner);
        List<String> options = new ArrayList<>();
        options.add("Choose day");
        options.add("Sunday");
        options.add("Monday");
        options.add("Tuesday");
        options.add("Wednesday");
        options.add("Thursday");
        options.add("Friday");
        options.add("Saturday");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdownSpinner.setAdapter(adapter);
        dropdownSpinner.setSelection(0,false);
       // dropdownSpinner.setVisibility(View.GONE);



//        recyclerView=findViewById(R.id.recViewIngredients);
//        linearLayoutManager=new LinearLayoutManager(this);
//        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView.setLayoutManager(linearLayoutManager);
//        ingredientsList=new ArrayList<>();
//        detailsAdapter=new DetailsAdapter(this,ingredientsList,this);
//        recyclerView.setAdapter(detailsAdapter);

          Bundle bundle=getIntent().getExtras();
       // meal=getIntent().getParcelableExtra("selectedMeal");
//        if(meal!= null){
//            name.setText(meal.getStrMeal());
//            country.setText(meal.getStrArea());
//            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);
//
//        }
        if(bundle!=null) {
            nameOfMeal = bundle.getString("selectedMeal");
            name.setText(nameOfMeal);

            detailsPresenter= new DetailsPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(this,"NULL")),this);

            detailsPresenter.getDetails(nameOfMeal);
          //  dropdownSpinner.setVisibility(View.VISIBLE);

            //showMealDetails(meal);
//            country.setText(meal.getStrArea());
//            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);



        }

      //  nameOfMeal= getIntent().getStringExtra("Name");




//        Meal meal=new Meal();
//        Uri uri=Uri.parse(meal.getStrYoutube());
//        video.setVideoURI(uri);
//        MediaController mediaController=new MediaController(this);
//        video.setMediaController(mediaController);
//        mediaController.setAnchorView(video);



    }

    public void showMealDetails(List<Meal> meals) {
        meal = meals.get(0);

        if (meal != null) {

            name.setText(nameOfMeal);
            country.setText(meal.getStrArea());
            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);
//            if (LoginFragment.flag) {
//                fav.setVisibility(View.GONE);
//                plan.setVisibility(View.GONE);
//                dropdownSpinner.setVisibility(View.GONE);
//
//            } else {
            fav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (LoginFragment.flag) {
                        Toast.makeText(MealDetailsActivity.this, "Not Available for guest", Toast.LENGTH_LONG).show();

                    } else {

                        if (!meal.isFav()) {
                            fav.setImageResource(R.drawable.red_fav);
                            if (detailsPresenter != null) {
                                detailsPresenter.addToFav(meal);
                                meal.setFav(true);
                                meal.setDay("NoDay");
                            } else {
                                Log.e("YourClassName", "DetailsPresenterImp object is null!");
                            }
                        } else {
                            fav.setImageResource(R.drawable.black_fav);
                            meal.setFav(false);

                        }
                    }
                }
            });
            plan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Toggle visibility of spinner
                    if (LoginFragment.flag) {
                        Toast.makeText(MealDetailsActivity.this, "Not Available for guest", Toast.LENGTH_LONG).show();

                    } else {
                        if (dropdownSpinner.getVisibility() == View.VISIBLE) {
                            dropdownSpinner.setVisibility(View.GONE);
                        } else {
                            dropdownSpinner.setVisibility(View.VISIBLE);
                            dropdownSpinner.performClick(); // Trigger the default spinner behavior
                        }
                    }
                }
            });


            // dropdownSpinner.setOnClickListener(null);

            dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    if (LoginFragment.flag) {
                        Toast.makeText(MealDetailsActivity.this, "Not Available for guest", Toast.LENGTH_LONG).show();

                    } else {
                        // Handle selection
                        if (position != 0) {
                            String selectedOption = (String) parent.getItemAtPosition(position);
                            Toast.makeText(MealDetailsActivity.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
                            meal.setDay(selectedOption);
                            d = meal.getDay();
                            Log.i("TAG", "onItemSelected: " + d);
                           if(!meal.isFav()) {
                               detailsPresenter.addPlan(meal);

                           }


                            PlanFragment planFragment = new PlanFragment();
//                        Bundle bundle=new Bundle();
//                        bundle.getString("plan",selectedOption);
//                        planFragment.setArguments(bundle);
                            PlanFragment.newInstance(selectedOption);
                        }
//                        FragmentManager fragmentManager = getSupportFragmentManager(); // For AppCompatActivity
//
//// Begin FragmentTransaction
//                        FragmentTransaction transaction = fragmentManager.beginTransaction();
//
//// Add the fragment to the container
//                        transaction.add(R.id.homeFragment, planFragment); // R.id.fragment_container is the ID of the container in your activity's layout
//
//// Commit the transaction
//                        transaction.commit();
//                        Intent myIntent =new Intent(MealDetailsActivity.this, HomeActivity.class);
//                        myIntent.putExtra("plan",selectedOption);
//                        startActivity(myIntent);
                    }


                    // dropdownSpinner.setVisibility(View.GONE);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    // Handle no selection
                }
            });

            ingredients.append(meal.getStrIngredient1() + " \n" + meal.getStrIngredient2() + "\n");
            ingredients.append(meal.getStrIngredient3() + " \n" + meal.getStrIngredient4() + "\n");
            ingredients.append(meal.getStrIngredient5() + " \n" + meal.getStrIngredient6() + "\n");
            ingredients.append(meal.getStrIngredient7() + " \n" + meal.getStrIngredient8() + "\n");
            ingredients.append(meal.getStrIngredient9() + " \n" + meal.getStrIngredient10() + "\n");
            ingredients.append(meal.getStrIngredient11() + " \n" + meal.getStrIngredient12() + "\n");
            ingredients.append(meal.getStrIngredient13() + " \n" + meal.getStrIngredient14() + "\n");
            ingredients.append(meal.getStrIngredient15() + " \n" + meal.getStrIngredient16() + "\n");
            ingredients.append(meal.getStrIngredient17() + " \n" + meal.getStrIngredient18() + "\n");
            ingredients.append(meal.getStrIngredient19() + " \n" + meal.getStrIngredient20() + "\n");
            steps.setText(meal.getStrInstructions());


            String youtubeLink = meal.getStrYoutube();
            if (youtubeLink != null && !youtubeLink.isEmpty()) {
                Uri uri = Uri.parse(youtubeLink);
                String videoId = uri.getQueryParameter("v");

                video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
                    @Override
                    public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                        super.onReady(youTubePlayer);
                        if (videoId != null && !videoId.isEmpty()) {
                            youTubePlayer.loadVideo(videoId, 0);
                        }
                    }
                });
            }

        }
    }

    public void handlePlan(List<MealPlan> meals){
        mealPlan=meals.get(0);
        if(mealPlan!=null) {
//            plan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Toggle visibility of spinner
//                    if (LoginFragment.flag) {
//                        Toast.makeText(MealDetailsActivity.this, "Not Available for guest", Toast.LENGTH_LONG).show();
//
//                    } else {
//                        if (dropdownSpinner.getVisibility() == View.VISIBLE) {
//                            dropdownSpinner.setVisibility(View.GONE);
//                        } else {
//                            dropdownSpinner.setVisibility(View.VISIBLE);
//                            dropdownSpinner.performClick(); // Trigger the default spinner behavior
//                        }
//                    }
//                }
//            });
//
//
//            // dropdownSpinner.setOnClickListener(null);
//
//            dropdownSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//                @Override
//                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                    if (LoginFragment.flag) {
//                        Toast.makeText(MealDetailsActivity.this, "Not Available for guest", Toast.LENGTH_LONG).show();
//
//                    } else {
//                        // Handle selection
//                        if (position != 0) {
//                            String selectedOption = (String) parent.getItemAtPosition(position);
//                            Toast.makeText(MealDetailsActivity.this, "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
//                            d = mealPlan.getDay();
//                            Log.i("TAG", "onItemSelected: " + d);
//
//                                detailsPresenter.addPlan(mealPlan);
//                                mealPlan.setDay(selectedOption);
//
//
//                            PlanFragment planFragment = new PlanFragment();
////                        Bundle bundle=new Bundle();
////                        bundle.getString("plan",selectedOption);
////                        planFragment.setArguments(bundle);
//                            PlanFragment.newInstance(selectedOption);
//                        }
////                        FragmentManager fragmentManager = getSupportFragmentManager(); // For AppCompatActivity
////
////// Begin FragmentTransaction
////                        FragmentTransaction transaction = fragmentManager.beginTransaction();
////
////// Add the fragment to the container
////                        transaction.add(R.id.homeFragment, planFragment); // R.id.fragment_container is the ID of the container in your activity's layout
////
////// Commit the transaction
////                        transaction.commit();
////                        Intent myIntent =new Intent(MealDetailsActivity.this, HomeActivity.class);
////                        myIntent.putExtra("plan",selectedOption);
////                        startActivity(myIntent);
//                    }
//
//
//                    // dropdownSpinner.setVisibility(View.GONE);
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // Handle no selection
//                }
//            });
        }


    }

    }


//    private void populateIngredientsList(Meal meal) {
//        ingredientsList.clear();
//        // Add ingredients and measures to the list
////        for (int i = 1; i <= 20; i++) {
//            //String measure = meal.getIngredient();
//           // String measure = meal.getMeasure(i);
//
////                String ingredientDetail = "strIngredient"+ i;
////                Log.d("Ingredients", "Size: " + ingredientDetail);
////            if ( ingredientDetail != null && !ingredientDetail.isEmpty()) {
////                ingredientsList.add(ingredientDetail);
////            }
//       // }
//        ingredientsList.add(meal.getStrIngredient1());
//        ingredientsList.add(meal.getStrIngredient2());
//        ingredientsList.add(meal.getStrIngredient3());
//        ingredientsList.add(meal.getStrIngredient4());
//        ingredientsList.add(meal.getStrIngredient5());
//        ingredientsList.add(meal.getStrIngredient6());
//        ingredientsList.add(meal.getStrIngredient7());
//        ingredientsList.add(meal.getStrIngredient8());
//        ingredientsList.add(meal.getStrIngredient9());
//        ingredientsList.add(meal.getStrIngredient10());
//        ingredientsList.add(meal.getStrIngredient11());
//        ingredientsList.add(meal.getStrIngredient12());
//        ingredientsList.add(meal.getStrIngredient13());
//        ingredientsList.add(meal.getStrIngredient14());
//        ingredientsList.add(meal.getStrIngredient15());
//        ingredientsList.add(meal.getStrIngredient16());
//        ingredientsList.add(meal.getStrIngredient17());
//        ingredientsList.add(meal.getStrIngredient18());
//        ingredientsList.add(meal.getStrIngredient19());
//        ingredientsList.add(meal.getStrIngredient20());
//
//
//        Log.d("Ingredients", "List: " + ingredientsList.toString());
//    }
