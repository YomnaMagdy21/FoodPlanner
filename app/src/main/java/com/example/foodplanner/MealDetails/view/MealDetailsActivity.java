package com.example.foodplanner.MealDetails.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

public class MealDetailsActivity extends AppCompatActivity {

    YouTubePlayerView video;
    String videoID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal_details);

        video=findViewById(R.id.videoView);
//        Meal meal=new Meal();
//        Uri uri=Uri.parse(meal.getStrYoutube());
//        video.setVideoURI(uri);
//        MediaController mediaController=new MediaController(this);
//        video.setMediaController(mediaController);
//        mediaController.setAnchorView(video);
        video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                super.onReady(youTubePlayer);

                youTubePlayer.loadVideo(videoID,0);
            }
        });


    }
}