package com.example.foodplanner.MealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenter;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenterImp;
import com.example.foodplanner.R;
import com.example.foodplanner.area.view.AreaAdapter;
import com.example.foodplanner.categories.presenter.CategoriesPresenterImp;
import com.example.foodplanner.database.MealLocalDataSourceImp;
import com.example.foodplanner.model.Meal;
import com.example.foodplanner.model.MealsRepositoryImp;
import com.example.foodplanner.network.MealsRemoteDataSourceImp;
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
    DetailsPresenter detailsPresenter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    DetailsAdapter detailsAdapter;
    List<String> ingredientsList;

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

            detailsPresenter= new DetailsPresenterImp(this, MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance()));

            detailsPresenter.getDetails(nameOfMeal);
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

    public void showMealDetails(List<Meal> meals){
        meal = meals.get(0);
        if(meal!= null) {

            name.setText(nameOfMeal);
            country.setText(meal.getStrArea());
            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);
            ingredients.append(meal.getStrIngredient1()+" \n"+meal.getStrIngredient2()+"\n");
            ingredients.append(meal.getStrIngredient3()+" \n"+meal.getStrIngredient4()+"\n");
            ingredients.append(meal.getStrIngredient5()+" \n"+meal.getStrIngredient6()+"\n");
            ingredients.append(meal.getStrIngredient7()+" \n"+meal.getStrIngredient8()+"\n");
            ingredients.append(meal.getStrIngredient9()+" \n"+meal.getStrIngredient10()+"\n");
            ingredients.append(meal.getStrIngredient11()+" \n"+meal.getStrIngredient12()+"\n");
            ingredients.append(meal.getStrIngredient13()+" \n"+meal.getStrIngredient14()+"\n");
            ingredients.append(meal.getStrIngredient15()+" \n"+meal.getStrIngredient16()+"\n");
            ingredients.append(meal.getStrIngredient17()+" \n"+meal.getStrIngredient18()+"\n");
            ingredients.append(meal.getStrIngredient19()+" \n"+meal.getStrIngredient20()+"\n");
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
//            populateIngredientsList(meal);
//            Log.d("Ingredients", "Size: " + ingredientsList.size());

//            detailsAdapter.notifyDataSetChanged();
//            detailsAdapter.setList(ingredientsList);




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
}