//package com.example.foodplanner.MealDetails.view;
//
//import android.net.Uri;
//import android.os.Bundle;
//
//import androidx.annotation.NonNull;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.Spinner;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import com.bumptech.glide.Glide;
//import com.example.foodplanner.MealDetails.presenter.DetailsPresenter;
//import com.example.foodplanner.MealDetails.presenter.DetailsPresenterImp;
//import com.example.foodplanner.R;
//import com.example.foodplanner.database.MealLocalDataSourceImp;
//import com.example.foodplanner.login.view.LoginFragment;
//import com.example.foodplanner.model.Meal;
//import com.example.foodplanner.model.MealsRepositoryImp;
//import com.example.foodplanner.network.MealsRemoteDataSourceImp;
//import com.example.foodplanner.plan.view.PlanFragment;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
//import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;
//
//import java.util.ArrayList;
//import java.util.List;
//
//
//public class DetailsFragment extends Fragment implements DetailsView{
//
//    // TODO: Rename parameter arguments, choose names that match
//    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//    private static final String ARG_PARAM1 = "param1";
//    private static final String ARG_PARAM2 = "param2";
//
//    // TODO: Rename and change types of parameters
//    private String mParam1;
//    private String mParam2;
//    YouTubePlayerView video;
//    String videoID;
//    String nameOfMeal;
//    TextView name,country,ingredients,steps;
//
//    ImageView imgMeal;
//    List<Meal> meals;
//    Meal  meal;
//    DetailsPresenter detailsPresenter;
//    RecyclerView recyclerView;
//    LinearLayoutManager linearLayoutManager;
//    DetailsAdapter detailsAdapter;
//    List<String> ingredientsList;
//    ImageView fav,plan;
//    DetailsView listener;
//    Spinner dropdownSpinner;
//
//    public DetailsFragment() {
//        // Required empty public constructor
//    }
//
//
//    public static DetailsFragment newInstance(String param1) {
//        DetailsFragment fragment = new DetailsFragment();
//        Bundle args = new Bundle();
//        args.putString("selectedMeal", param1);
//        fragment.setArguments(args);
//        return fragment;
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            nameOfMeal = getArguments().getString("selectedMeal");
//            name.setText(nameOfMeal);
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        // Inflate the layout for this fragment
//        View view=inflater.inflate(R.layout.fragment_details, container, false);
//        video=view.findViewById(R.id.videoView);
//        name=view.findViewById(R.id.nameMeal);
//        country=view.findViewById(R.id.countryName);
//        imgMeal=view.findViewById(R.id.mealImg);
//        ingredients=view.findViewById(R.id.strIngredients);
//        steps=view.findViewById(R.id.steps);
//        fav=view.findViewById(R.id.imgFav);
//        plan=view.findViewById(R.id.planImg);
//        dropdownSpinner = view.findViewById(R.id.dropdown_spinner);
//        List<String> options = new ArrayList<>();
//        options.add("Choose day");
//        options.add("Sunday");
//        options.add("Monday");
//        options.add("Tuesday");
//        options.add("Wednesday");
//        options.add("Thursday");
//        options.add("Friday");
//        options.add("Saturday");
//
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, options);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        dropdownSpinner.setAdapter(adapter);
//        dropdownSpinner.setSelection(0,false);
//        detailsPresenter= new DetailsPresenterImp(getContext(), MealsRepositoryImp.getInstance(MealsRemoteDataSourceImp.getInstance(), MealLocalDataSourceImp.getInstance(getContext(),"NULL")),this);
//
//        detailsPresenter.getDetails(nameOfMeal);
//        return view;
//    }
//
//    @Override
//    public void showMealDetails(List<Meal> mealList) {
//        meal = meals.get(0);
//        if(meal!= null) {
//
//            name.setText(nameOfMeal);
//            country.setText(meal.getStrArea());
//            Glide.with(this).load(meal.getStrMealThumb()).into(imgMeal);
////            if (LoginFragment.flag) {
////                fav.setVisibility(View.GONE);
////                plan.setVisibility(View.GONE);
////                dropdownSpinner.setVisibility(View.GONE);
////
////            } else {
//            fav.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    if(LoginFragment.flag){
//                        Toast.makeText(getContext(),"Not Available for guest",Toast.LENGTH_LONG).show();
//
//                    }else{
//
//                        if (!meal.isFav()) {
//                            fav.setImageResource(R.drawable.red_fav);
//                            if (detailsPresenter != null) {
//                                detailsPresenter.addToFav(meal);
//                                meal.setFav(true);
//                                meal.setDay("NoDay");
//                            } else {
//                                Log.e("YourClassName", "DetailsPresenterImp object is null!");
//                            }
//                        } else {
//                            fav.setImageResource(R.drawable.black_fav);
//                            meal.setFav(false);
//                            detailsPresenter.removeFromFav(meal);
//
//
//                        }
//                    }}
//            });
//            plan.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    // Toggle visibility of spinner
//                    if (LoginFragment.flag) {
//                        Toast.makeText(getContext(), "Not Available for guest", Toast.LENGTH_LONG).show();
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
//                    if(LoginFragment.flag){
//                        Toast.makeText(getContext(),"Not Available for guest",Toast.LENGTH_LONG).show();
//
//                    }else {
//                        // Handle selection
//                        if (position != 0) {
//                            String selectedOption = (String) parent.getItemAtPosition(position);
//                            Toast.makeText(getContext(), "Selected: " + selectedOption, Toast.LENGTH_SHORT).show();
//                            meal.setDay(selectedOption);
//                            if (!meal.isFav()) {
//                                detailsPresenter.addPlan(meal,selectedOption);
//                            }
//                            PlanFragment planFragment = new PlanFragment();
////                        Bundle bundle=new Bundle();
////                        bundle.getString("plan",selectedOption);
////                        planFragment.setArguments(bundle);
//                            PlanFragment.newInstance(selectedOption);
//                        }
////
//                    }
//                }
//
//                @Override
//                public void onNothingSelected(AdapterView<?> parent) {
//                    // Handle no selection
//                }
//            });
//
//        }
//        ingredients.append(meal.getStrIngredient1() + " \n" + meal.getStrIngredient2() + "\n");
//        ingredients.append(meal.getStrIngredient3() + " \n" + meal.getStrIngredient4() + "\n");
//        ingredients.append(meal.getStrIngredient5() + " \n" + meal.getStrIngredient6() + "\n");
//        ingredients.append(meal.getStrIngredient7() + " \n" + meal.getStrIngredient8() + "\n");
//        ingredients.append(meal.getStrIngredient9() + " \n" + meal.getStrIngredient10() + "\n");
//        ingredients.append(meal.getStrIngredient11() + " \n" + meal.getStrIngredient12() + "\n");
//        ingredients.append(meal.getStrIngredient13() + " \n" + meal.getStrIngredient14() + "\n");
//        ingredients.append(meal.getStrIngredient15() + " \n" + meal.getStrIngredient16() + "\n");
//        ingredients.append(meal.getStrIngredient17() + " \n" + meal.getStrIngredient18() + "\n");
//        ingredients.append(meal.getStrIngredient19() + " \n" + meal.getStrIngredient20() + "\n");
//        steps.setText(meal.getStrInstructions());
//
//
//        String youtubeLink = meal.getStrYoutube();
//        if (youtubeLink != null && !youtubeLink.isEmpty()) {
//            Uri uri = Uri.parse(youtubeLink);
//            String videoId = uri.getQueryParameter("v");
//
//            video.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
//                @Override
//                public void onReady(@NonNull YouTubePlayer youTubePlayer) {
//                    super.onReady(youTubePlayer);
//                    if (videoId != null && !videoId.isEmpty()) {
//                        youTubePlayer.loadVideo(videoId, 0);
//                    }
//                }
//            });
//        }
//
//    }
//
//}
//
//
//
