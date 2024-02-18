package com.example.foodplanner.search.view;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
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

public class SearchAdepter extends RecyclerView.Adapter<SearchAdepter.ViewHolder> {
        Context context;
        List<Meal> meals;
        List<Meal> mealList;
//    // OnAllProductClickListener listener;
        AllMealView listener;


    public SearchAdepter(Context context, List<Meal> _products,AllMealView _listener) {
            this.context = context;
            this.meals = _products;
            this.listener=_listener;
            this.meals=new ArrayList<Meal>();
            mealList=new ArrayList<>();
        }

        public void setList(List<Meal> updateMeals){
            this.meals=updateMeals;
            mealList.addAll(updateMeals);
           // notifyDataSetChanged();

        }

        @NonNull
        @Override
        public SearchAdepter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.meal_item,parent,false);
            SearchAdepter.ViewHolder viewHolder=new SearchAdepter.ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull SearchAdepter.ViewHolder holder, int position) {

            Meal meal = meals.get(position);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
            Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
            holder.mealName.setText(meal.getStrMeal());
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
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
        public Filter getFilter() {
            return filter;
        }

        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Meal> filteredList = new ArrayList<>();
                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(mealList);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();
                    for (Meal meal : mealList) {
                        if (meal.getStrMeal().toLowerCase().contains(filterPattern)) {
                            filteredList.add(meal);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                meals.clear();
                meals.addAll((List<Meal>) results.values);
                notifyDataSetChanged();
            }
        };



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
