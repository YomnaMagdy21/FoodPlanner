package com.example.foodplanner.MealDetails.view;

import android.content.Context;
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
import com.example.foodplanner.R;
import com.example.foodplanner.categories.modelC.Categories;
import com.example.foodplanner.categories.view.CategoriesAdapter;
import com.example.foodplanner.categories.view.CategoriesView;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class DetailsAdapter  extends RecyclerView.Adapter<DetailsAdapter.ViewHolder> {
    Context context;
    List<Meal> categories;
    List<Meal> categoriesList;

    //    // OnAllProductClickListener listener;
    DetailsView listener;
    List<String> ingredientsList;


    public DetailsAdapter(Context context, List<String> _meals, DetailsView _listener) {
        this.context = context;
        this.ingredientsList = _meals;
        this.listener = _listener;
        this.categories = new ArrayList<Meal>();
        categoriesList = new ArrayList<>();
    }
//
//    public void setList(List<Meal> updateMeals) {
//        this.categories = updateMeals;
//        notifyDataSetChanged();
//
//    }
public void setList(List<String> ingredientsList) {
    this.ingredientsList = ingredientsList;
    notifyDataSetChanged();
}

    @NonNull
    @Override
    public DetailsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.category_item, parent, false);
        DetailsAdapter.ViewHolder viewHolder = new DetailsAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull DetailsAdapter.ViewHolder holder, int position) {

        String ingredient = ingredientsList.get(position);
        holder.mealName.setText(ingredient);


//         holder.fav.setImageResource(R.drawable.black_fav);
//        holder.calender.setImageResource(R.drawable.calendar);
//        String imageUrl = meal.();
//        if (imageUrl != null && !imageUrl.isEmpty()) {
//            Glide.with(context)
//                    .load(imageUrl)
//                    .into(holder.imgCat);
//        } else {
//            Glide.with(context)
//                    .load(R.drawable.load)
//                    .into(holder.imgCat);
//            // Alternatively, you can hide the ImageView
            // holder.imgCat.setVisibility(View.GONE);
        //}





    }


    @Override
    public int getItemCount() {
        return categories.size();
    }




    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imgCat, fav, calender;

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