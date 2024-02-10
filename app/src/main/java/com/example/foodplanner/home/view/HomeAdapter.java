package com.example.foodplanner.home.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Context context;
    List<Meal> meals;

//    // OnAllProductClickListener listener;
    AllMealView listener;


    public HomeAdapter(Context context, List<Meal> _products,AllMealView _listener) {
        this.context = context;
        this.meals = _products;
        this.listener=_listener;
        meals=new ArrayList<Meal>();
    }

    public void setList(List<Meal> updateMeals){
        this.meals=updateMeals;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public HomeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.meal_item,parent,false);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.ViewHolder holder, int position) {

        Meal meal = meals.get(position);


         holder.fav.setImageResource(R.drawable.black_fav);
        holder.calender.setImageResource(R.drawable.calendar);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        holder.mealName.setText(meal.getStrMeal());



    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img,fav,calender;

        TextView mealName;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgMeal);
            mealName = itemView.findViewById(R.id.mealName);
            fav = itemView.findViewById(R.id.blackFav);
            calender = itemView.findViewById(R.id.calender);


        }
    }
}