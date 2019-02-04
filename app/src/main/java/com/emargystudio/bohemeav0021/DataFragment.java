package com.emargystudio.bohemeav0021;

import android.content.Context;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class DataFragment extends Fragment {

    private static final String TAG = "DataFragment";

    TextView next ,txtData;
    ImageView backbtn;
    EditText edtDate , edtHour , edtChairs;

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
        next = view.findViewById(R.id.next);
        backbtn = view.findViewById(R.id.backBtn);
        edtDate = view.findViewById(R.id.edtDate);
        edtHour = view.findViewById(R.id.edtHour);
        edtChairs = view.findViewById(R.id.edtChairs);
        txtData = view.findViewById(R.id.textView);

        //change fonts
        Typeface face = Typeface.createFromAsset(getActivity().getAssets(),"fonts/NABILA.TTF");
        txtData.setTypeface(face);
        next.setTypeface(face);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.your_placeholder, new TableFragment());
                ft.commit();
            }
        });
    }
}
