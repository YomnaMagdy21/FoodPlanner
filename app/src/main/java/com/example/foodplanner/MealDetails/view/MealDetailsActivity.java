package com.example.foodplanner.MealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenter;
import com.example.foodplanner.MealDetails.presenter.DetailsPresenterImp;
import com.example.foodplanner.R;
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
    TextView name,country;

    ImageView imgMeal;
    List<Meal> meals;
    Meal  meal;
    DetailsPresenter detailsPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        video=findViewById(R.id.videoView);
        name=findViewById(R.id.nameMeal);
        country=findViewById(R.id.countryName);
        imgMeal=findViewById(R.id.mealImg);

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
}