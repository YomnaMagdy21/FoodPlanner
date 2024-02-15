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
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
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
           // showMealDetails(meals);
            detailsPresenter.getDetails(nameOfMeal);


        }

      //  nameOfMeal= getIntent().getStringExtra("Name");




//        Meal meal=new Meal();
//        Uri uri=Uri.parse(meal.getStrYoutube());
//        video.setVideoURI(uri);
//        MediaController mediaController=new MediaController(this);
//        video.setMediaController(mediaController);
//        mediaController.setAnchorView(video);
//        video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//            @Override
//            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                super.onReady(youTubePlayer);
//
//                youTubePlayer.loadVideo(videoID,0);
//            }
//        });


    }

    public void showMealDetails(List<Meal> meals){
        if(meal!= null) {
            meal = meals.get(0);
            name.setText(nameOfMeal);
            country.setText(meal.getStrArea());
            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);
        }



    }
}