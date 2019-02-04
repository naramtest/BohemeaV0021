package com.emargystudio.bohemeav0021.helperClasses;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.emargystudio.bohemeav0021.R;
import com.squareup.picasso.Picasso;

public class ViewPagerAdapter extends PagerAdapter {

    Activity activity ;
    String[] images;
    LayoutInflater inflater;

    public ViewPagerAdapter(Activity activity, String[] images) {
        this.activity = activity;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view==o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        inflater = (LayoutInflater)activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View itemView = inflater.inflate(R.layout.table_item,container,false);

        ImageView image;
        image = itemView.findViewById(R.id.tableImage);
        DisplayMetrics dis = new DisplayMetrics();
        try {
            Picasso.get().load(images[position])
                    .placeholder(R.mipmap.ic_launcher)
                    .into(image);
        }catch (Exception e){

        }

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Assad", "onClick: "+position);
            }
        });

        container.addView(itemView);
        return itemView;

     }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        ((ViewPager) container).removeView((View)object);
    }
}
