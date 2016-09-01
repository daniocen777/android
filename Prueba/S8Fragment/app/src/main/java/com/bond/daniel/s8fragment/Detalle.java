package com.bond.daniel.s8fragment;


import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class Detalle extends Fragment {
    TextView tv_color;
    View view;
    String color;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = (View)inflater.inflate(R.layout.fragment_detalle, container, false);
        tv_color = (TextView)view.findViewById(R.id.txv_color);
        Intent intent = getActivity().getIntent();
        color = intent.getStringExtra("color");
        tv_color.setText(color);
        return view;
    }
}
