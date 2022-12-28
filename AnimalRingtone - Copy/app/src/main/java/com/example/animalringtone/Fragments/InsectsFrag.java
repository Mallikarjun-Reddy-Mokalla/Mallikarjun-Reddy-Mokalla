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
import com.example.animalringtone.Adapter.InsectsAdapter;
import com.example.animalringtone.Data.Insectsadata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class InsectsFrag extends Fragment implements AnimalRingtone.callback {

    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    View view;
    InsectsAdapter myInsectAdapter;
    ArrayList<Insectsadata> insectsadata = new ArrayList<>();


    String insectsSounds[] = {"Bee-sound.mp3", "bestcricket.mp3", "Cricket.mp3", "Funny-Flying.mp3", "Grass-Cricket.mp3",
            "grasshopper.mp3", "Insects-1.mp3", "Insects-Cicada-1.mp3", "Insects-Cicada-2.mp3", "Insects-Cicada-3.mp3", "Insects.mp3",
            "Mosquito-Spank.mp3", "Night-Thrill.mp3", "Small-Frns-Voice.mp3", "Swarm.mp3", "Tone-Insects.mp3"};
    String insect_names[] = {"Bee sound", "bestcricket", "Cricket", "Funny Flying", "Grass Cricket",
            "grasshopper", "Insects 1", "Insects Cicada 1", "Insects Cicada 2", "Insects Cicada 3", "Insects",
            "Mosquito Spank", "Night Thrill", "Small Frns Voice", "Swarm", "Tone Insects"};
    int insect_images[] = {R.drawable.bees, R.drawable.best_cricket, R.drawable.cricket, R.drawable.housefly, R.drawable.grass_insect, R.drawable.grasshopper, R.drawable.insects,
            R.drawable.insect1, R.drawable.insects, R.drawable.insects_cicada, R.drawable.insects_cicada, R.drawable.insects, R.drawable.insect1, R.drawable.mosquito,
            R.drawable.nightthrills, R.drawable.housefly, R.drawable.swram, R.drawable.toneinsects};

    public InsectsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_insects, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_insects);
        recyclerView.setHasFixedSize(true);


        int i;
        for (i = 0 ; i < insectsSounds.length; i++ ) {

            insectsadata.add(new Insectsadata(insect_names[i], insectsSounds[i],insect_images[i]));

        }


        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myInsectAdapter = new InsectsAdapter(this.getActivity(), insectsadata);
        recyclerView.setAdapter(myInsectAdapter);
    }


    @Override
    public void onPause() {
        super.onPause();
        myInsectAdapter.onBackPressedStopPlayer(true);
    }
    @Override
    public void onStop() {
        super.onStop();
//        myInsectAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
//        myInsectAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myInsectAdapter.stop(true);
    }
}