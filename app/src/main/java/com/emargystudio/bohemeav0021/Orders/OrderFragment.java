package com.emargystudio.bohemeav0021.Orders;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emargystudio.bohemeav0021.Model.FoodCategory;
import com.emargystudio.bohemeav0021.Model.Table;
import com.emargystudio.bohemeav0021.R;
import com.emargystudio.bohemeav0021.ViewHolder.FoodCategoryAdapter;
import com.emargystudio.bohemeav0021.ViewHolder.TableAdapter;
import com.emargystudio.bohemeav0021.helperClasses.URLS;
import com.emargystudio.bohemeav0021.helperClasses.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class OrderFragment extends Fragment {

    FoodCategoryAdapter foodCategoryAdapter;
    ArrayList<FoodCategory> foodCategories ;

    public OrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        foodCategories = new ArrayList<>();
        categoryQuery();
        initRecyclerView(view);

    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycle_food_category);
        foodCategoryAdapter = new FoodCategoryAdapter(getContext(),foodCategories);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(foodCategoryAdapter);
    }

    public void categoryQuery(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLS.food_category_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(!jsonObject.getBoolean("error")){

                                JSONArray jsonObjectCategory =  jsonObject.getJSONArray("categorys");


                                for(int i = 0 ; i<jsonObjectCategory.length(); i++){
                                    JSONObject jsonObjectSingleCategory = jsonObjectCategory.getJSONObject(i);
                                    Log.i("jsonSingleStory",jsonObjectSingleCategory.toString());

                                    foodCategories.add(new FoodCategory(jsonObjectSingleCategory.getInt("id"),
                                            jsonObjectSingleCategory.getString("name"),
                                            jsonObjectSingleCategory.getString("image_url")));
                                }

                                foodCategoryAdapter.notifyDataSetChanged();
                            }else{

                                Toast.makeText(getContext(),getString(R.string.internet_off),Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),getString(R.string.internet_off),Toast.LENGTH_LONG).show();
                    }
                }
        );
        VolleyHandler.getInstance(getContext()).addRequetToQueue(stringRequest);
    }

}
