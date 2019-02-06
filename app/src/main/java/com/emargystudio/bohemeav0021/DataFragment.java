package com.emargystudio.bohemeav0021;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;


public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";

    TextView txtData;
    ImageView backBtn;
    EditText edtDate , edtHour , edtChairs;
    FloatingActionButton nextFAB;


    //var
    int chosenYear,chosenMonth,chosenDay;
    int chosenHour , chosenMinute;

    public DataFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //widget
        backBtn = view.findViewById(R.id.backBtn);
        edtDate = view.findViewById(R.id.edtDate);
        edtHour = view.findViewById(R.id.edtHour);
        edtChairs = view.findViewById(R.id.edtChairs);
        txtData = view.findViewById(R.id.textView);
        nextFAB = view.findViewById(R.id.nextFAB);

        //var
        Calendar calendar = Calendar.getInstance();
        final int currentYear = calendar.get(Calendar.YEAR);
        final int currentMonth = calendar.get(Calendar.MONTH);
        final int currentDay   = calendar.get(Calendar.DAY_OF_MONTH);
        final int currentHour = calendar.get(Calendar.HOUR_OF_DAY);
        final int currentMinute = calendar.get(Calendar.MINUTE);



        //change fonts
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NABILA.TTF");
        txtData.setTypeface(face);




        nextFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.your_placeholder, new TableFragment());
                ft.commit();
            }
        });

        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                datePicker(currentYear,currentMonth,currentDay);

            }
        });

        edtHour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePicker(currentHour,currentMinute);
            }
        });
    }

    public void datePicker(int currentYear, int currentMonth, int currentDay){

        DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                chosenYear = year;
                chosenMonth = month+1;
                chosenDay = dayOfMonth;
                edtDate.setText(chosenYear +"-"+chosenMonth+"-"+chosenDay);

            }
        }, currentYear, currentMonth, currentDay);
        dialog.show();

    }

    public void timePicker(int currentHour,int currentMinute){

        TimePickerDialog timeDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                chosenHour = hourOfDay;
                chosenMinute = minute;
                edtHour.setText(chosenHour+":"+chosenMinute);
            }
        },currentHour,currentMinute,false);
        timeDialog.show();
    }
}
