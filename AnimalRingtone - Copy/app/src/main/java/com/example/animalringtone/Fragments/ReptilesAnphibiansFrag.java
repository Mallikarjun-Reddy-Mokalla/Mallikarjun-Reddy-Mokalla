package com.example.animalringtone.Fragments;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.animalringtone.Activity.AnimalRingtone;
import com.example.animalringtone.Adapter.ReptilesAdapter;
import com.example.animalringtone.Data.Reptilesdata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class ReptilesAnphibiansFrag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    ReptilesAdapter myReptileAdapter;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<Reptilesdata> reptilesdata = new ArrayList<>();

    String reptilesSounds[] = {"deser-rain-frog.mp3", "dog-barking-ringtone.mp3", "Frog-Sms.mp3", "koyal-ringtone.mp3", "ljagushka.mp3",
            "rattlesnake-1.mp3", "rattlesnake-2.mp3", "rattlesnake-3.mp3", "rattlesnake-4.mp3", "rattlesnake-5.mp3", "rattlesnake-6.mp3",
            "rattlesnakerattle.mp3", "snakeatt.mp3", "snakehiss.mp3", "snakehit-1.mp3", "snakehit-2.mp3"};
    String reptiles_names[] = {"Deser rain frog", "Dog barking ringtone", "Frog Sms", "koyal ringtone", "ljagushka",
            "rattlesnake 1", "rattlesnake 2", "rattlesnake 3", "rattlesnake 4", "rattlesnake 5", "rattlesnake 6",
            "rattlesnakerattle", "snakeatt", "snakehiss", "snakehit 1", "snakehit 2"};
    int reptiles_images[] = {R.drawable.deser_rain_frog, R.drawable.dog, R.drawable.red_frog, R.drawable.snake_hit, R.drawable.guska, R.drawable.rattle_snake,
            R.drawable.rattle_snake, R.drawable.rattle_snake, R.drawable.rattle_snake, R.drawable.rattle_snake, R.drawable.rattle_snake, R.drawable.rattle_snake,
            R.drawable.snake_hit, R.drawable.snake_hiss, R.drawable.snake_hit, R.drawable.snake_hit};

    public ReptilesAnphibiansFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_reptiles_anphibians, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recylerview_reptiles);
        recyclerView.setHasFixedSize(true);

        for (int i = 0 ; i < reptilesSounds.length ; i++) {

            reptilesdata.add(new Reptilesdata(reptilesSounds[i], reptiles_names[i], reptiles_images[i]));

        }
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myReptileAdapter = new ReptilesAdapter(this.getActivity(), reptilesdata);
        recyclerView.setAdapter(myReptileAdapter);

    }

    @Override
    public void onPause() {
        super.onPause();
        myReptileAdapter.onBackPressedStopPlayer(true);
    }
    @Override
    public void onStop() {
        super.onStop();
//        myReptileAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
//        myReptileAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myReptileAdapter.stop(true);
    }
}