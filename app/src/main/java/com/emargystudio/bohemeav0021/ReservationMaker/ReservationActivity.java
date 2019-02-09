package com.emargystudio.bohemeav0021.ReservationMaker;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.emargystudio.bohemeav0021.R;
import com.emargystudio.bohemeav0021.ReservationMaker.DataFragment;

public class ReservationActivity extends AppCompatActivity {

    private static final String TAG = "ReservationActivity";



    ImageView next;






    //var

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reservation);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.your_placeholder, new DataFragment());
        ft.commit();







    }





}
