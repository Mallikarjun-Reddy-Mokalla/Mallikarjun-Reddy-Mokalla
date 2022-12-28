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
import com.example.animalringtone.Adapter.EndangeredSpeciesAdapter;
import com.example.animalringtone.Data.Endangeredspeciesdata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class Endangered_species_Frag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<Endangeredspeciesdata> endangeredspeciesdata = new ArrayList<>();
    EndangeredSpeciesAdapter myEndangeredspeciesAdapter;

    String endangeredSounds[] = {"African-Elephant.mp3", "American-Alligator.mp3", "American-Pika.mp3", "Arroyo-Toad.mp3", "Beluga.mp3",
            "Black-Toad.mp3", "Bob-Cat.mp3", "California-Red-Legged-Frog.mp3", "California-Spotted-Owl.mp3", "Coyotes.mp3",
            "Crested-Toad.mp3", "Dugong.mp3", "Elephant-Seal-Pup.mp3", "Gila-Monster.mp3", "Grizzly-Bear.mp3", "Humpback.mp3",
            "Mexican-Wolf.mp3", "Orca.mp3", "Pacific-Walrus.mp3", "Panda.mp3", "Polar-Bear.mp3", "Prairie-Dog.mp3", "Puma.mp3",
            "rare-Jaguar.mp3", "Vaquita.mp3"};
    String endangered_species_names[] = {"African Elephant", "American Alligator", "American Pika", "Arroyo Toad", "Beluga",
            "Black Toad", "Bob Cat", "California Red Legged Frog", "California Spotted Owl", "Coyotes",
            "Crested Toad", "Dugong", "Elephant Seal Pup", "Gila Monster", "Grizzly Bear", "Humpback",
            "Mexican Wolf", "Orca", "Pacific Walrus", "Panda", "Polar Bear", "Prairie Dog", "Puma",
            "Rare Jaguar", "Vaquita"};
    int endangered_species_images[] = {R.drawable.african_elephant, R.drawable.ameriacn_alligator, R.drawable.american_pika, R.drawable.arroya_toad,
            R.drawable.beluga, R.drawable.black_toad, R.drawable.bob_cat, R.drawable.red_frog, R.drawable.owl, R.drawable.coyotes, R.drawable.crested_toad, R.drawable.dugong, R.drawable.elephant_seal_pup,
            R.drawable.gilla_monster, R.drawable.grizzly_bear, R.drawable.humpback, R.drawable.fox, R.drawable.orca, R.drawable.pacific_walrus, R.drawable.panda, R.drawable.polar_bear,
            R.drawable.prairie_dog, R.drawable.puma, R.drawable.jaguar_roar, R.drawable.vaqutia};


    public Endangered_species_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_endangered_species_, container, false);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = view.findViewById(R.id.recylerview_endangered_species);
        recyclerView.setHasFixedSize(true);
        for (int i = 0; i < endangeredSounds.length; i++) {

            endangeredspeciesdata.add(new Endangeredspeciesdata(endangered_species_names[i], endangeredSounds[i], endangered_species_images[i]));
        }
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myEndangeredspeciesAdapter = new EndangeredSpeciesAdapter(this.getActivity(), endangeredspeciesdata);
        recyclerView.setAdapter(myEndangeredspeciesAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        myEndangeredspeciesAdapter.onBackPressedStopPlayer(true);
    }

    @Override
    public void onStop() {
        super.onStop();
//        myEndangeredspeciesAdapter.stop(true);
    }

    @Override
    public void onResume() {
        super.onResume();
//        myEndangeredspeciesAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myEndangeredspeciesAdapter.stop(true);
    }
}