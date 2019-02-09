package com.emargystudio.bohemeav0021.ReservationMaker;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emargystudio.bohemeav0021.Common.Common;
import com.emargystudio.bohemeav0021.Model.Table;
import com.emargystudio.bohemeav0021.R;
import com.emargystudio.bohemeav0021.ViewHolder.TableAdapter;
import com.emargystudio.bohemeav0021.helperClasses.URLS;
import com.emargystudio.bohemeav0021.helperClasses.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;


public class TableFragment extends Fragment {

    private static final String TAG = "TableFragment";

    //widget
    TextView textView;
    TableAdapter tableAdapter;



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
        initRecyclerView(view);
        initImageBitmaps();

    }

    private void initImageBitmaps() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLS.tables_query,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(!jsonObject.getBoolean("error")){

                                JSONArray jsonObjectTables =  jsonObject.getJSONArray("tabels");

                                Log.i("arrayComments",jsonObjectTables.toString());

                                for(int i = 0 ; i<jsonObjectTables.length(); i++){
                                    JSONObject jsonObjectSingleTable = jsonObjectTables.getJSONObject(i);
                                    Log.i("jsonSingleStory",jsonObjectSingleTable.toString());

                                    Table table = new Table(jsonObjectSingleTable.getInt("table_number"),
                                            jsonObjectSingleTable.getInt("chair_number"),
                                            jsonObjectSingleTable.getString("table_image"));

                                    if (!Common.tableArray.contains(table.getTable_number())) {
                                        mImageUrls.add(table.getTable_image());
                                        mNames.add(String.valueOf(table.getTable_number()));

                                    }
                                }
                                tableAdapter.notifyDataSetChanged();
                                Common.tableArray.clear();
                            }else{

                                Toast.makeText(getContext(),jsonObject.getString("message"),Toast.LENGTH_LONG).show();
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );
        VolleyHandler.getInstance(getContext()).addRequetToQueue(stringRequest);



    }

    private void initRecyclerView(View view){
        RecyclerView recyclerView = view.findViewById(R.id.recycle_table);
        tableAdapter = new TableAdapter(getContext(),mNames,mImageUrls);
        StaggeredGridLayoutManager staggeredGridLayoutManager =
                new StaggeredGridLayoutManager(NUM_COLUMNS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(tableAdapter);
    }

}
