package com.danicode.platzigram3.view.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.danicode.platzigram3.R;
import com.danicode.platzigram3.adapter.PictureAdapterRecyclerView;
import com.danicode.platzigram3.model.Picture;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        showToolbar(getResources().getString(R.string.tab_home), false, view);
        RecyclerView recyclerView = view.findViewById(R.id.pictureRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);


        PictureAdapterRecyclerView pictureAdapterRecyclerView = new PictureAdapterRecyclerView(
                buidPictures(), R.layout.cardview_picture, getActivity());

        recyclerView.setAdapter(pictureAdapterRecyclerView);
        return view;
    }

    // Devolver un arreglo de pictures
    public ArrayList<Picture> buidPictures() {
        ArrayList<Picture>  pictures = new ArrayList<>();
        pictures.add(new Picture(
                "https://image.freepik.com/foto-gratis/fondo-universo-purpura_1017-3753.jpg",
                "Y. Daniel", "5 días", "3 Me gusta"));
        pictures.add(new Picture(
                "https://images.eltiempo.digital/files/article_main/uploads/2017/05/16/591b56ab0a802.jpeg",
                "Paola", "3 días", "19 Me gusta"));
        pictures.add(new Picture(
                "https://cdnmundo2.img.sputniknews.com/images/107330/32/1073303215.jpg",
                "Milenna", "9 días", "13 Me gusta"));
        return pictures;
    }

    // Mostar el toolbar
    public void showToolbar(String title, boolean uppButton, View view) {
        Toolbar toolbar = view.findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(uppButton);
    }

}
