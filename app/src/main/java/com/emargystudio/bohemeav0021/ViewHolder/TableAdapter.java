package com.emargystudio.bohemeav0021.ViewHolder;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.emargystudio.bohemeav0021.InterFace.ItemClickListener;
import com.emargystudio.bohemeav0021.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TableAdapter extends RecyclerView.Adapter<TableViewHolder> {

    private static final String TAG = "TableAdapter";

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImages = new ArrayList<>();
    private Context context;


    public TableAdapter(Context context ,ArrayList<String> mNames, ArrayList<String> mImages) {
        this.mNames = mNames;
        this.mImages = mImages;
        this.context = context;
    }

    @NonNull
    @Override
    public TableViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.table_item, viewGroup, false);
        return new TableViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TableViewHolder holder, final int position) {

        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(mImages.get(position))
                .apply(requestOptions)
                .into(holder.table_image);

        holder.table_text.setText(mNames.get(position));
        holder.table_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               alertTable(mImages.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return mImages.size();
    }

    public void alertTable(String imageUrl){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        LayoutInflater li = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View alertLayout = li.inflate(R.layout.alert_table, null);
        ImageView table_item_image = alertLayout.findViewById(R.id.table_item_image);


        // this is set the view from XML inside AlertDialog
        alert.setView(alertLayout);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.mipmap.ic_launcher);
        Glide.with(context)
                .load(imageUrl)
                .apply(requestOptions)
                .into(table_item_image);
        AlertDialog dialog = alert.create();
        dialog.show();


    }
}
