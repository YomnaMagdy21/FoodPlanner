package com.example.foodplanner.meal.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MealDetails.view.MealDetailsActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.ViewHolder> {
    Context context;
    List<Meal> meals;

    //    // OnAllProductClickListener listener;
    MealView listener;


    public MealAdapter(Context context, List<Meal> _products,MealView _listener) {
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
    public MealAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.meal_item,parent,false);
        MealAdapter.ViewHolder viewHolder=new MealAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.ViewHolder holder, int position) {

        Meal meal = meals.get(position);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        holder.mealName.setText(meal.getStrMeal());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.navigateToDetails(meal);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("selectedMeal", (Parcelable) meal);

                Intent myIntent =new Intent(context, MealDetailsActivity.class);
                myIntent.putExtra("Name",meal.getStrMeal());
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
//            fav = itemView.findViewById(R.id.blackFav);
//            calender = itemView.findViewById(R.id.calender);
            cardView=itemView.findViewById(R.id.cardView);


        }
    }
}