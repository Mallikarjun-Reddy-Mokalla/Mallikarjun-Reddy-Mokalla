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
import com.example.animalringtone.Adapter.WaterAnimalsAdapter;
import com.example.animalringtone.Data.Wateranimalsadata;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class WaterAnimals_Frag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    WaterAnimalsAdapter mywaterAdapter;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<Wateranimalsadata> wateranimalsadata = new ArrayList<>();


    String waterSounds[] = {"Amazon-Catfish-Underwater.mp3", "dolphin-ringtone.mp3", "Eleph-Seal.mp3", "Fish-Splashing.mp3",
            "Fishtank-Bubbles.mp3", "Harp-Seals.mp3", "hump2.mp3", "penguin.mp3", "penguin3.mp3", "Rough-Toothed-Dolphin.mp3",
            "seal-1.mp3", "seal-2.mp3", "Seal-Call.mp3", "sealion.mp3", "sealions.mp3", "whaleclicks.mp3", "whales-talking.mp3"};
    String wateranimalnames[] = {"Amazon Catfish Underwater", "Dolphin ringtone", "Eleph Seal", "Fish Splashing",
            "Fishtank Bubbles", "Harp Seals", "Hump2", "penguin", "Penguin3", "Rough Toothed Dolphi",
            "seal 1", "seal 2", "Seal Call", "sealion", "sealions", "whaleclicks", "Whales talking"};
    int water_animals_images[] = {R.drawable.cat_fish, R.drawable.dolphin, R.drawable.eleph_seal, R.drawable.fish_splashing, R.drawable.fish_tank_bubbels,
            R.drawable.harp_seal, R.drawable.duck, R.drawable.penguin, R.drawable.penguin_3, R.drawable.toothed_dolphi, R.drawable.seal1, R.drawable.seal2,
            R.drawable.seal_call, R.drawable.sealion, R.drawable.sealions, R.drawable.whales, R.drawable.whales2};

    public WaterAnimals_Frag() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_water, container, false);
        return view;

    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_water);
        recyclerView.setHasFixedSize(true);
        for (int i = 0; i < waterSounds.length; i++) {

            wateranimalsadata.add(new Wateranimalsadata(wateranimalnames[i], waterSounds[i],  water_animals_images[i]));

        }
        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        mywaterAdapter = new WaterAnimalsAdapter(this.getActivity(), wateranimalsadata);
        recyclerView.setAdapter(mywaterAdapter);

    }


    @Override
    public void onPause() {
        super.onPause();
        mywaterAdapter.onBackPressedStopPlayer(true);
    } @Override
    public void onStop() {
        super.onStop();
//        mywaterAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
//        mywaterAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        mywaterAdapter.stop(true);
    }
}