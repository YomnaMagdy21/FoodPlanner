package com.example.foodplanner.favmeal.view;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class FavoriteAdapter  extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
    Context context;
    List<Meal> meals;
    FavoriteView listener;


    public FavoriteAdapter(Context context, List<Meal> _meals, FavoriteView _listener) {
        this.context = context;
        this.meals = _meals;
        this.listener=_listener;
        meals=new ArrayList<Meal>();
    }

    public void setList(List<Meal> updateProducts){
        this.meals=updateProducts;
        notifyDataSetChanged();

    }


    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.fav_item,parent,false);
        FavoriteAdapter.ViewHolder viewHolder=new FavoriteAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {

        Meal meal = meals.get(position);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        holder.mealName.setText(meal.getStrMeal());
        holder.fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.removeMeal(meal);
            }
        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.navigateToDetails(meal);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("selectedMeal", (Parcelable) meal);

                Intent myIntent =new Intent(context, MealDetailsActivity.class);
                myIntent.putExtra("selectedMeal",meal.getStrMeal());
                context.startActivity(myIntent);
            }
        });


    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img,fav,calender;

        TextView mealName;
        CardView cardView;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgMeal);
            mealName = itemView.findViewById(R.id.mealName);
            fav = itemView.findViewById(R.id.redFav);
//            calender = itemView.findViewById(R.id.calender);
            cardView=itemView.findViewById(R.id.cardView);


        }
    }

}