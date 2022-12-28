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
import com.example.animalringtone.Adapter.WildAnimalsAdapter;
import com.example.animalringtone.Data.Wildanimalsdata;
import com.example.animalringtone.R;

import java.util.ArrayList;


public class LandAnimalsFrag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    WildAnimalsAdapter myWildanimalAdapter;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<Wildanimalsdata> wildanimalsdata = new ArrayList<>();


    String wildSounds[] = {"alligator.mp3", "bear-sounds.mp3", "bison.mp3", "cobra.mp3", "coyotes.mp3", "Dog-Barking.mp3",
            "eagle-ringtone.mp3", "Elefant.mp3", "elk.mp3", "fox-calls.mp3", "geier.mp3", "Gorilla.mp3", "jaguar-roar.mp3",
            "Kapuzineraffe.mp3", "Leopard.mp3", "lev.mp3", "ljagushka.mp3", "Lowe.mp3", "owl.mp3", "Puma.mp3", "Rhinozerus.mp3",
            "roar.mp3", "Schimpanse.mp3", "shimpanze.mp3", "Tiger.mp3", "wild-turkey-sound.mp3", "wolf-1.mp3", "Wolf-2.mp3",
            "wolf-howl-sound.mp3"};
    String wild_animals_names[] = {"Alligator", "Bear sounds", "Bison", "Cobra", "Coyotes", "Dog Barking",
            "eagle ringtone", "Elephant", "elk", "Fox calls", "Geier", "Gorilla", "Jaguar roar",
            "Kapuzineraffe", "Leopard", "Lev", "Ljagushka", "Lowe", "Owl", "Puma", "Rhinozerus",
            "Roar", "Schimpanse", "Shimpanze", "Tiger", "wild turkey sound", "Wolf 1", "Wolf 2",
            "Wolf howl sound"};
    int wild_animals_images[] = {R.drawable.crocodile, R.drawable.bear, R.drawable.bison, R.drawable.cobra, R.drawable.coyotes, R.drawable.dog, R.drawable.eagle,
            R.drawable.elephant, R.drawable.elks_sparring, R.drawable.fox, R.drawable.geier, R.drawable.gorilla, R.drawable.jaguar_roar, R.drawable.lion, R.drawable.leopard,
            R.drawable.lion, R.drawable.deser_rain_frog, R.drawable.lion, R.drawable.owl, R.drawable.puma, R.drawable.rhinozerous, R.drawable.lion,
            R.drawable.shimpanze, R.drawable.shimpanze, R.drawable.tiger, R.drawable.wildturkey, R.drawable.wolf, R.drawable.wolf2, R.drawable.fox, R.drawable.wolf3};

    public LandAnimalsFrag() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_land_animals, container, false);
        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_wildanimals);
        recyclerView.setHasFixedSize(true);
        for (int i = 0 ; i < wildSounds.length ; i++ ) {
            wildanimalsdata.add(new Wildanimalsdata(wild_animals_names[i], wildSounds[i], wild_animals_images[i]));
        }


        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myWildanimalAdapter = new WildAnimalsAdapter(this.getActivity(), wildanimalsdata);
        recyclerView.setAdapter(myWildanimalAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        myWildanimalAdapter.onBackPressedStopPlayer(true);
    }
    @Override
    public void onStop() {
        super.onStop();
//        myWildanimalAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
//        myWildanimalAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myWildanimalAdapter.stop(true);
    }
}