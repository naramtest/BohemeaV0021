package com.emargystudio.bohemeav0021.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.emargystudio.bohemeav0021.InterFace.ItemClickListener;
import com.emargystudio.bohemeav0021.R;
import com.squareup.picasso.Picasso;

public class TableAdapter extends RecyclerView.Adapter<TableViewHolder> {

    String[] images;

    public TableAdapter(String[] images) {
        this.images = images;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.table_item, viewGroup, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TableViewHolder holder, int position) {

        Picasso.get().load(images[position]).into(holder.food_table);
        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLongClick) {
                Log.d("Table Number", "onClick: "+position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return images.length;
    }
}
