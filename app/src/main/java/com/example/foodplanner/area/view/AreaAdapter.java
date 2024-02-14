package com.example.foodplanner.area.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.categories.modelC.Categories;
import com.example.foodplanner.categories.view.CategoriesAdapter;
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    Context context;
    List<Categories> meals;

    //    // OnAllProductClickListener listener;
    AllMealView listener;


    public AreaAdapter(Context context, List<Categories> _meals,AllMealView _listener) {
        this.context = context;
        this.meals = _meals;
        this.listener=_listener;
        meals=new ArrayList<Categories>();
    }

    public void setList(List<Categories> updateMeals){
        this.meals=updateMeals;
        notifyDataSetChanged();

    }

    @NonNull
    @Override
    public AreaAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=inflater.inflate(R.layout.category_item,parent,false);
        AreaAdapter.ViewHolder viewHolder=new AreaAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AreaAdapter.ViewHolder holder, int position) {

        Categories meal = meals.get(position);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
        String imageUrl = meal.getStrCategoryThumb();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            Glide.with(context)
                    .load(imageUrl)
                    .into(holder.imgCat);
        } else {
            Glide.with(context)
                    .load(R.drawable.load)
                    .into(holder.imgCat);
            // Alternatively, you can hide the ImageView
            // holder.imgCat.setVisibility(View.GONE);
        }            holder.mealName.setText(meal.getStrCategory());
//            holder.cardView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    listener.navigateToDetails(meal);
//                }
//            });



    }


    @Override
    public int getItemCount() {
        return meals.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCat,fav,calender;

        TextView mealName;
        CardView cardView;




        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCat = itemView.findViewById(R.id.imgMealCategories);
            mealName = itemView.findViewById(R.id.nameMealCategories);
//            fav = itemView.findViewById(R.id.blackFav);
//            calender = itemView.findViewById(R.id.calender);
            //   cardView=itemView.findViewById(R.id.cardView);


        }
    }

}

