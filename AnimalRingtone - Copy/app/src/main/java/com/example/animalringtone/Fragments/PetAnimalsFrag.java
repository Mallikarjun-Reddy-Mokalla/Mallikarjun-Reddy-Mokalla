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
import com.example.animalringtone.Adapter.PetAnimalsAdapter;
import com.example.animalringtone.Data.Petanimalsdata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class PetAnimalsFrag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    PetAnimalsAdapter myPetAdapter;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<Petanimalsdata> petanimalsdata = new ArrayList<>();


    String domesticSounds[] = {"angry-cat.mp3", "birds-sounds-parrots.mp3", "camel-1.mp3", "camel-2.mp3", "Chicken-clucking.mp3",
            "chicken-ringtone.mp3", "chipmunk.mp3", "cow-sound.mp3", "dog-barking-ringtone.mp3", "dogs-talking.mp3", "donkey.mp3",
            "elephant-sound.mp3", "Ente-quackt.mp3", "Gaense.mp3", "goat-sounds.mp3", "goose-creek.mp3", "goose.mp3", "horse-whinny.mp3",
            "kitty-meow.mp3", "little-chik.mp3", "little-puppy.mp3", "monkey.mp3", "ochse.mp3", "osel.mp3", "pig.mp3", "Pony.mp3",
            "schaf.mp3", "screaming-monkey.mp3", "sheep-sounds.mp3", "shimpanze.mp3", "squirrel.mp3", "truthahn.mp3"};
    String domestic_names[] = {"Angry cat", "Birds sounds parrots", "Camel 1", "Camel 2", "Chicken clucking",
            "Chicken ringtone", "Chipmunk", "Cow sound", "Dog barking ringtone", "Dogs talking", "Donkey",
            "Elephant sound", "Ente quackt", "Gaense", "Goat sounds", "Goose creek", "Goose", "Horse whinny",
            "Kitty meow", "Little chik", "Little puppy", "Monkey", "Ochse", "Osel", "Pig", "Pony",
            "Schaf", "Screaming monkey", "Sheep sounds", "Shimpanze", "Squirrel", "Truthahn"};
    int pet_animals_images[] = {R.drawable.angry_cat, R.drawable.parrots, R.drawable.camel, R.drawable.camel2, R.drawable.hen1, R.drawable.hen2, R.drawable.chipmunk,
            R.drawable.cow, R.drawable.dog, R.drawable.dogs, R.drawable.donkey, R.drawable.elephant, R.drawable.ente_quackt, R.drawable.gaense, R.drawable.goat,
            R.drawable.goose,  R.drawable.goose, R.drawable.horse, R.drawable.kitty_meow, R.drawable.little_chick, R.drawable.little_puppy, R.drawable.monkey, R.drawable.ochse,
            R.drawable.donkey2, R.drawable.pig, R.drawable.pony, R.drawable.schaf, R.drawable.monkey, R.drawable.sheep, R.drawable.shimpanze,
            R.drawable.squirrel, R.drawable.truthahn};

    public PetAnimalsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_pet_animals, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_petanimals);
        recyclerView.setHasFixedSize(true);

        for (int i = 0 ; i < domesticSounds.length ; i++) {
            petanimalsdata.add(new Petanimalsdata(domestic_names[i], domesticSounds[i], pet_animals_images[i]));

        }
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myPetAdapter = new PetAnimalsAdapter(this.getActivity(), petanimalsdata);
        recyclerView.setAdapter(myPetAdapter);


    }


    @Override
    public void onPause() {
        super.onPause();
        myPetAdapter.onBackPressedStopPlayer(true);
    }
    @Override
    public void onStop() {
        super.onStop();
//        myPetAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
//        myPetAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myPetAdapter.stop(true);
    }
}