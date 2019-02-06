package com.emargystudio.bohemeav0021;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.TextView;

import com.emargystudio.bohemeav0021.ViewHolder.TableAdapter;

import java.util.ArrayList;


public class TableFragment extends Fragment {

    private static final String TAG = "TableFragment";

    //widget
    TextView textView;



    //var
    private static final int NUM_COLUMNS = 2;
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mNames = new ArrayList<>();


    public TableFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_table, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        textView = view.findViewById(R.id.textView);
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NABILA.TTF");
        textView.setTypeface(face);
        initImageBitmaps(view);


    }

    private void initImageBitmaps(View view) {
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://images.pexels.com/photos/207962/pexels-photo-207962.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        mNames.add("1");

        mImageUrls.add("https://images.unsplash.com/photo-1531804055935-76f44d7c3621?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&w=1000&q=80");
        mNames.add("2");

        mImageUrls.add("https://iso.500px.com/wp-content/uploads/2016/11/stock-photo-159533631-1500x1000.jpg");
        mNames.add("3");

        mImageUrls.add("https://www.odt.co.nz/sites/default/files/styles/odt_story_slideshow/public/slideshow/node-1245328/2017/05/mike_wilkinson_0.jpg?itok=696xZmVu");
        mNames.add("4");


        mImageUrls.add("https://www.shutterfly.com/ideas/wp-content/plugins/sfly_seniorpics/img/senior-photo-ideas-108.jpg");
        mNames.add("5");

        mImageUrls.add("https://www.bigstockphoto.com/images/homepage/module-4.jpg");
        mNames.add("6");


        mImageUrls.add("https://i.imgur.com/jDWuXwK.jpg");
        mNames.add("7");

        mImageUrls.add("https://cdn.pixabay.com/photo/2017/05/21/15/14/balloon-2331488_960_720.jpg");
        mNames.add("8");

        mImageUrls.add("https://i.pinimg.com/736x/c5/2f/c9/c52fc99b6fecac8e6bc20a8ccc83a7e1--photo-instagram-belles-photos.jpg");
        mNames.add("9");

        initRecyclerView(view);

    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycle_table);
        TableAdapter tableAdapter = new TableAdapter(getContext(),mNames,mImageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(tableAdapter);
    }
}
