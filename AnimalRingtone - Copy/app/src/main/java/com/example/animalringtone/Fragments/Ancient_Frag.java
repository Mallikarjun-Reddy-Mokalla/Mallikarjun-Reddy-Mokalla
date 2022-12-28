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
import com.example.animalringtone.Adapter.AncientAnimalAdapter;
import com.example.animalringtone.Data.Ancientanimaldata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class Ancient_Frag extends Fragment implements AnimalRingtone.callback{
    RecyclerView recyclerView;
    //    RecyclerView.Adapter myAncientAdapter;
    AncientAnimalAdapter myAncientAdapter;
    GridLayoutManager gridLayoutManager;
    View view;

    ArrayList<Ancientanimaldata> ancientanimaldata = new ArrayList<>();


    String ancientSounds[] = {"Alligator-Crocodile-Growl.mp3","Bull-Elks-Sparring.mp3","Dinosaur-Footstep.mp3",
            "Dinosaur-Growl-1.mp3","Dinosaur-Growl-2.mp3", "dinosaur-roar-sound.mp3","Dinosaur-Roar.mp3","Dinosaur.mp3",
            "Dragon-Roaring.mp3","Elk-Bellows.mp3", "Elk-Bugle-1.mp3", "Elk-Bugle-2.mp3","Halloween-Monster.mp3", "Monster-Laugh.mp3",
            "Mummy-Zombie.mp3", "Pterodactyl-Scream.mp3", "pterodactyl-screech.mp3", "Raptor-Call.mp3", "Tiger-1.mp3", "Tiger-2.mp3",
            "Tiger-3.mp3", "Tiger-4.mp3", "Tyrannosaurus-Rex-1.mp3", "Tyrannosaurus-Rex-2.mp3", "Tyrannosaurus-Rex-Roar.mp3",
            "Velociraptor-Call.mp3", "Zombie-Brain-Eater.mp3"};
    String ancient_names[] = {"Alligator Crocodile", "Bull Elks Sparring", "Dinosaur Footstep",
            "Dinosaur Growl 1", "Dinosaur Growl 2", "Dinosaur roar sound", "Dinosaur Roar", "Dinosaur",
            "Dragon Roaring", "Elk Bellows", "Elk Bugle 1", "Elk Bugle 2", "Halloween Monster", "Monster Laugh",
            "Mummy Zombie", "Pterodactyl Scream", "pterodactyl screech", "Raptor Call", "Tiger 1", "Tiger 2",
            "Tiger 3", "Tiger 4", "Tyrannosaurus Rex 1", "Tyrannosaurus Rex 2", "Tyrannosaurus Rex Roar",
            "Velociraptor Call", "Zombie Brain Eater"};
    int ancient_images[] = {R.drawable.crocodile, R.drawable.elkbulk, R.drawable.dinosaur, R.drawable.dinosaurs_roar, R.drawable.dinosaurs, R.drawable.dinosaurs_roar,
            R.drawable.dinosaurs_roar, R.drawable.dinosaur, R.drawable.dragon, R.drawable.elks_sparring, R.drawable.elkbulk, R.drawable.elkbulk, R.drawable.monster_laugh,
            R.drawable.monster_laugh, R.drawable.zombie, R.drawable.pterodactyl, R.drawable.rapotr, R.drawable.tiger, R.drawable.tiger, R.drawable.tiger, R.drawable.tiger,
            R.drawable.tiger, R.drawable.tyrannosaurus, R.drawable.tyrannosaurus,  R.drawable.tyrannosaurus,R.drawable.dragon, R.drawable.zombie};

    public Ancient_Frag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_ancient, container, false);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_ancient);
        recyclerView.setHasFixedSize(true);
        for (int i = 0;i < ancientSounds.length;i++) {
            ancientanimaldata.add(new Ancientanimaldata(ancient_names[i], ancientSounds[i], ancient_images[i]));
        }
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myAncientAdapter = new AncientAnimalAdapter(this.getActivity(), ancientanimaldata);
        recyclerView.setAdapter(myAncientAdapter);

    }


    @Override
    public void onPause() {
        super.onPause();
        myAncientAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onStop() {
        super.onStop();
        myAncientAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        myAncientAdapter.onBackPressedStopPlayer(true);


    }

    @Override
    public void onPageChanded() {
        myAncientAdapter.stop(true);

    }
}