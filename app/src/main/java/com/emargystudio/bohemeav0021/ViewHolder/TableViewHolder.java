package com.emargystudio.bohemeav0021.ViewHolder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.emargystudio.bohemeav0021.InterFace.ItemClickListener;
import com.emargystudio.bohemeav0021.R;

public class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public ImageView food_table;
    private ItemClickListener itemClickListener;

    public TableViewHolder(@NonNull View itemView) {
        super(itemView);
        food_table = itemView.findViewById(R.id.tableImage);
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
