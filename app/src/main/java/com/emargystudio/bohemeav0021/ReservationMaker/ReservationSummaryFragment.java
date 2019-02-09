package com.emargystudio.bohemeav0021.ReservationMaker;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.emargystudio.bohemeav0021.Common.Common;
import com.emargystudio.bohemeav0021.Model.Reservation;
import com.emargystudio.bohemeav0021.Model.User;
import com.emargystudio.bohemeav0021.R;
import com.emargystudio.bohemeav0021.helperClasses.SharedPreferenceManger;
import com.emargystudio.bohemeav0021.helperClasses.URLS;
import com.emargystudio.bohemeav0021.helperClasses.VolleyHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ReservationSummaryFragment extends Fragment {

    private static final String TAG = "ReservationSummaryFragm";

    //widget
    TextView txtDate ;
    TextView txtHour ;
    TextView txtChair ;
    TextView txtTable ;

    public ReservationSummaryFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservation_summary, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {


         txtDate = view.findViewById(R.id.date_txt);
         txtHour = view.findViewById(R.id.hour_txt);
         txtChair = view.findViewById(R.id.chair_txt);
         txtTable = view.findViewById(R.id.table_txt);



        User user = SharedPreferenceManger.getInstance(getContext()).getUserData();
        int user_id = user.getUserId();
        if (Common.tableNumber !=0 && Common.year !=0 && Common.month !=0
                && Common.day !=0 && Common.startHour !=0 && Common.chairNumber !=0) {


            double end_hour = Common.startHour+2;
            Log.d(TAG, "onViewCreated: "+end_hour);
            sendReservation(user_id,end_hour);


        }else {
            Toast.makeText(getContext(), "Something want wrong please try again later", Toast.LENGTH_SHORT).show();
        }


    }

    private void sendReservation(final int user_id, final double end_hour){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URLS.send_reservation,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            if (!jsonObject.getBoolean("error")){
                                JSONObject jsonObjectReservation =  jsonObject.getJSONObject("reservation");
                                Log.d(TAG, "onResponse: "+jsonObjectReservation);

                                Reservation reservation = new Reservation(jsonObjectReservation.getInt("res_id"),
                                        jsonObjectReservation.getInt("user_id"),
                                        jsonObjectReservation.getInt("table_id"),
                                        jsonObjectReservation.getInt("year"),
                                        jsonObjectReservation.getInt("month"),
                                        jsonObjectReservation.getInt("day"),
                                        jsonObjectReservation.getDouble("hours"),
                                        jsonObjectReservation.getDouble("end_hour"),
                                        jsonObjectReservation.getInt("chairNumber"));


                                txtDate.setText("DATE : " +reservation.getYear()+"-"+reservation.getMonth()+"-"+reservation.getDay());
                               txtHour.setText("START AT : "+reservation.getHours());
                                txtChair.setText("RESERVATION FOR : "+reservation.getChairNumber());
                                txtTable.setText("TABLE NUMBER : "+reservation.getTable_id());

                            }else {
                                JSONObject jsonObjectReservation =  jsonObject.getJSONObject("reservation");

                                Log.d(TAG, "onResponse: "+jsonObjectReservation.getString("message"));
                            }

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onErrorResponse: "+error.getMessage());
                    }
                }
        ){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map ReservationData = new HashMap<>();
                ReservationData.put("user_id",String.valueOf(user_id));
                ReservationData.put("table_id",String.valueOf(Common.tableNumber));
                ReservationData.put("year",String.valueOf(Common.year));
                ReservationData.put("month",String.valueOf(Common.month));
                ReservationData.put("day",String.valueOf(Common.day));
                ReservationData.put("hours",String.valueOf(Common.startHour));
                ReservationData.put("end_hour",String.valueOf(end_hour));
                ReservationData.put("chairNumber",String.valueOf(Common.chairNumber));

                return  ReservationData;
            }
        };//end of string Request

        VolleyHandler.getInstance(getContext()).addRequetToQueue(stringRequest);





    }
}
