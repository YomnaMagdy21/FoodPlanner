//package com.example.foodplanner.favmeal.view;
//
//import android.content.Context;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//import android.widget.ImageView;
//import android.widget.RatingBar;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.bumptech.glide.Glide;
//import com.example.foodplanner.model.Meal;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class FavoriteAdapter  extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder>{
//    Context context;
//    List<Meal> meals;
//  //  FavoriteView listener;
//
//
////    public FavoriteAdapter(Context context, List<Meal> _meals, FavoriteView _listener) {
////        this.context = context;
////        this.meals = _meals;
////        this.listener=_listener;
////        meals=new ArrayList<Meal>();
////    }
//
//    public void setList(List<Meal> updateProducts){
//        this.meals=updateProducts;
//        notifyDataSetChanged();
//
//    }
//
//
////    @NonNull
////    @Override
////    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
////        LayoutInflater inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
////        View view=inflater.inflate(R.layout.fav_item,parent,false);
////        FavoriteAdapter.ViewHolder viewHolder=new FavoriteAdapter.ViewHolder(view);
////        return viewHolder;
////
////    }
//
//    @Override
//    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
//
//        Meal meal = meals.get(position);
//
////        Log.d("Image URL", product.getThumbnail());
////        // holder.img.setImageBitmap(product.getThumbnail());
////        Glide.with(context).load(product.getThumbnail()).into(holder.img);
////        holder.title.setText(product.getTitle());
////        holder.price.setText(String.valueOf(product.getPrice()));
////        holder.brand.setText(product.getBrand());
////        holder.desc.setText(product.getDescription());
////        holder.rate.setRating(product.getRating());
////        holder.addFav.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View v) {
////                listener.removeProduct(product);
////            }
////        });
//
//
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return meals.size();
//    }
//
//    class ViewHolder extends RecyclerView.ViewHolder {
//
//        ImageView img;
//
//        TextView title, price, brand, desc;
//
//
//        RatingBar rate;
//        Button addFav;
//
//        public ViewHolder(@NonNull View itemView) {
//            super(itemView);
//            img = itemView.findViewById(R.id.img);
//            title = itemView.findViewById(R.id.titleTxt);
//            price = itemView.findViewById(R.id.priceTxt);
//            brand = itemView.findViewById(R.id.brandTxt);
//            desc = itemView.findViewById(R.id.descTxt);
//            rate = itemView.findViewById(R.id.ratingBar);
//            addFav=itemView.findViewById(R.id.btnRem);
//
//        }
//    }
//
//}