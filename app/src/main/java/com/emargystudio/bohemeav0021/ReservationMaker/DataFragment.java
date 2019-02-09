package com.emargystudio.bohemeav0021.ReservationMaker;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emargystudio.bohemeav0021.Common.Common;
import com.emargystudio.bohemeav0021.R;
import com.emargystudio.bohemeav0021.helperClasses.URLS;
import com.emargystudio.bohemeav0021.helperClasses.VolleyHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.Calendar;


public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";

    TextView txtData;
    ImageView backBtn;
    EditText edtDate , edtHour , edtChairs;
    FloatingActionButton nextFAB;
    TextInputLayout dataLayout,hourLayout,chairLayout;


    //var
    int chosenYear,chosenMonth,chosenDay;
    int chosenHour , chosenMinute;
    int chosenChair;

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
        dataLayout = view.findViewById(R.id.dateLayout);
        hourLayout = view.findViewById(R.id.hourLayout);
        chairLayout = view.findViewById(R.id.chairLayout);


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
                if (chosenYear == 0 || chosenMonth == 0 || chosenDay == 0) {
                    dataLayout.setError("Pick a date before");


                }else if (chosenHour == 0) {

                    hourLayout.setError("Choose an hour first");

                }else if (chosenChair == 0){

                    chairLayout.setError("Fill this field");

                }else {
                    reservationQuery();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.your_placeholder, new TableFragment());
                    ft.commit();
                }
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

        edtChairs.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                chosenChair = Integer.parseInt(edtChairs.getText().toString());
                Common.chairNumber = chosenChair;

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
                Common.year = year;
                Common.month = month+1;
                Common.day = dayOfMonth;
                dataLayout.setErrorEnabled(false);



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
                Common.startHour = formatStartingHour(hourOfDay,minute);
                Log.d(TAG, "onTimeSet: "+formatStartingHour(hourOfDay,minute));
                edtHour.setText(chosenHour+":"+chosenMinute);

            }
        },currentHour,currentMinute,false);
        timeDialog.show();
    }

    public double formatStartingHour(int hour , int minute){
        String d = "0."+minute;
        double f = Float.parseFloat(d);
        double h = hour+f;
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String stringhour = decimalFormat.format(h);
        return Double.parseDouble(stringhour);
    }

    public void reservationQuery(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLS.reservation_query+Common.year+"&month="+Common.month+"&day="+Common.day,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            JSONObject jsonObject = new JSONObject(response);

                            if(!jsonObject.getBoolean("error")){

                                JSONArray jsonArrayReservation =  jsonObject.getJSONArray("reservations");

                                Log.i("arrayReservation",jsonArrayReservation.toString());

                                for(int i = 0 ; i<jsonArrayReservation.length(); i++){
                                    JSONObject jsonObjectSingleRes = jsonArrayReservation.getJSONObject(i);
                                    Log.i("jsonObjectSingleRes",jsonObjectSingleRes.toString());

                                    double startHour = jsonObjectSingleRes.getDouble("hours");
                                    double endHour   = jsonObjectSingleRes.getDouble("end_hour");
                                   if (Common.startHour >= startHour && Common.startHour <= endHour){
                                       Common.tableArray.add(jsonObjectSingleRes.getInt("table_id"));
                                   }
                                }


                            }else{
                                Toast.makeText(getContext(), "Something want wrong try again later ", Toast.LENGTH_SHORT).show();
                                Log.d(TAG, "onResponse: " + jsonObject.getString("message"));
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
}
