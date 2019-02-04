package com.emargystudio.bohemeav0021;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

import com.emargystudio.bohemeav0021.ViewHolder.TableAdapter;
import com.emargystudio.bohemeav0021.helperClasses.ViewPagerAdapter;

import java.util.Calendar;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "ReservationActivity";

//    RecyclerView recycler_table;
//    RecyclerView.LayoutManager layoutManager;
//    TableAdapter adapter;

    ImageView next;


    private String[] images = {
            "https://media.conforama.fr/Medias/600000/60000/9000/000/00/G_669002_A.jpg",
            "https://cdn.shopify.com/s/files/1/2660/5106/products/cmiqwlrueowxmjtosgrx_800x.jpg?v=1539054401",
            "https://cdn.shopify.com/s/files/1/2660/5106/products/wisz8mrpd67l6pss3crw_2b63262e-9b7a-4bbb-8f4f-d2da5d3cc57f_800x.jpg?v=1539039199"
    };



    //var
    int chosenYear,chosenMonth,chosenDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new DataFragment());
        ft.commit();




//        recycler_table = (RecyclerView) findViewById(R.id.recycler_table);
//        recycler_table.setHasFixedSize(true);
//        layoutManager = new LinearLayoutManager(this);
//        recycler_table.setLayoutManager(layoutManager);
//
//        // specify an adapter (see also next example)
//        adapter = new TableAdapter(images);
//        recycler_table.setAdapter(adapter);



    }


    public void datePicker(int currentYear, int currentMonth, int currentDay){

        DatePickerDialog dialog = new DatePickerDialog(ReservationActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                chosenYear = year;
                chosenMonth = month+1;
                chosenDay = dayOfMonth;

            }
        }, currentYear, currentMonth, currentDay);
        dialog.show();

    }


}
