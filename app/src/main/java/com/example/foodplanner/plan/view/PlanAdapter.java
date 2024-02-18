package com.example.foodplanner.plan.view;

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
import com.example.foodplanner.favmeal.view.FavoriteAdapter;
import com.example.foodplanner.favmeal.view.FavoriteView;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.ViewHolder>{
    Context context;
    List<Meal> meals;
    PlanView listener;


    public PlanAdapter(Context context, List<Meal> _meals, PlanView _listener) {
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
    public PlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.plan_item,parent,false);
        PlanAdapter.ViewHolder viewHolder=new PlanAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlanAdapter.ViewHolder holder, int position) {

        Meal meal = meals.get(position);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.img);
        holder.mealName.setText(meal.getStrMeal());
//        holder.fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                listener.removeMeal(meal);
////                meal.setFav(false);
//            }
//        });
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //listener.navigateToDetails(meal);
//                Bundle bundle = new Bundle();
//                bundle.putParcelable("selectedMeal", (Parcelable) meal);

                //DetailsFragment.newInstance(meal.getStrMeal());
                Intent myIntent =new Intent(context, MealDetailsActivity.class);
                myIntent.putExtra("selectedMeal",meal.getStrMeal());
                context.startActivity(myIntent);
            }
        });
        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteFromPlan(meal);
            }
        });


    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView img,fav,calender,cancel;

        TextView mealName;
        CardView cardView;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgMeal);
            mealName = itemView.findViewById(R.id.mealName);
         //   fav = itemView.findViewById(R.id.redFav);
//            calender = itemView.findViewById(R.id.calender);
            cardView=itemView.findViewById(R.id.cardView);
            cancel=itemView.findViewById(R.id.deleteImg);


        }
    }

}
