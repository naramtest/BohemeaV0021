package com.emargystudio.bohemeav0021.ViewHolder;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emargystudio.bohemeav0021.InterFace.ItemClickListener;
import com.emargystudio.bohemeav0021.Model.FoodCategory;
import com.emargystudio.bohemeav0021.R;

import java.util.ArrayList;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.FoodCategoryViewHolder>{

    private ArrayList<FoodCategory> foodCategories;
    private Context context;

    public FoodCategoryAdapter(Context context,ArrayList<FoodCategory> foodCategories) {
        this.foodCategories = foodCategories;
        this.context = context;
    }

    @NonNull
    @Override
    public FoodCategoryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.food_category_item, viewGroup, false);
        return new FoodCategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryViewHolder holder, int i) {
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(foodCategories.get(i).getImage_url())
                .apply(requestOptions)
                .into(holder.category_image);

        holder.category_text.setText(foodCategories.get(i).getName());
        holder.category_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });
    }

    @Override
    public int getItemCount() {
        return foodCategories.size();
    }

    class FoodCategoryViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ImageView category_image;
        TextView category_text;
        private ItemClickListener itemClickListener;


        public FoodCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            category_image = itemView.findViewById(R.id.category_image);
            category_text  = itemView.findViewById(R.id.category_number);
            itemView.setOnClickListener(this);
        }
        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }
        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }
    }
}
