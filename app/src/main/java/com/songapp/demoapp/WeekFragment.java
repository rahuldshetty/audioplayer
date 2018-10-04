package com.songapp.demoapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.songapp.demoapp.texts.TextOpen;


/**
 * A simple {@link Fragment} subclass.
 */
public class WeekFragment extends Fragment {

    public static CardView c1,c2,c3,c4;

    View mView;

    public WeekFragment() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_week, container, false);

        c1=mView.findViewById(R.id.cardView1);
        c2=mView.findViewById(R.id.cardView2);
        c3=mView.findViewById(R.id.cardView3);
        c4=mView.findViewById(R.id.cardView4);

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectGroup=1;
                Intent act=new Intent(getContext(),TextOpen.class);
                startActivity(act);
            }
        });

        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectGroup=2;
                Intent act=new Intent(getContext(),TextOpen.class);
                startActivity(act);
            }
        });


        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectGroup=3;
                Intent act=new Intent(getContext(),TextOpen.class);
                startActivity(act);
            }
        });


        c4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.selectGroup=4;
                Intent act=new Intent(getContext(),TextOpen.class);
                startActivity(act);
            }
        });



        return mView;

    }

}
