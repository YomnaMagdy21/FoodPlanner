package com.example.foodplanner.categories.view;

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
import com.example.foodplanner.home.view.AllMealView;
import com.example.foodplanner.home.view.HomeAdapter;
import com.example.foodplanner.model.Meal;

import java.util.ArrayList;
import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.ViewHolder> {
        Context context;
        List<Categories> categories;
    List<Categories> categoriesList;

//    // OnAllProductClickListener listener;
        CategoriesView listener;



    public CategoriesAdapter(Context context, List<Categories> _meals,CategoriesView _listener) {
            this.context = context;
            this.categories = _meals;
            this.listener=_listener;
        this.categories=new ArrayList<Categories>();
        categoriesList=new ArrayList<>();
        }

        public void setList(List<Categories> updateMeals){
            this.categories=updateMeals;
            categoriesList.addAll(updateMeals);
           // notifyDataSetChanged();

        }

        @NonNull
        @Override
        public CategoriesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view=inflater.inflate(R.layout.category_item,parent,false);
            CategoriesAdapter.ViewHolder viewHolder=new CategoriesAdapter.ViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull CategoriesAdapter.ViewHolder holder, int position) {

            Categories meal = categories.get(position);


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
            return categories.size();
        }

    public Filter getFilter() {
        return filter;
    }

    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<Categories> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(categoriesList); // Add all meals if the search query is empty
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (Categories meal : categoriesList) {
                    if (meal.getStrCategory().toLowerCase().contains(filterPattern)) {
                        filteredList.add(meal); // Add meal to filtered list if its name contains the search query
                    }
                }
            }
            FilterResults filterResults = new FilterResults();
            filterResults.values = filteredList;
            return filterResults;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            categories.clear(); // Clear current list of meals
            categories.addAll((List<Categories>) results.values); // Add filtered list to current list
            notifyDataSetChanged(); // Notify adapter of data change
        }
    };


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
