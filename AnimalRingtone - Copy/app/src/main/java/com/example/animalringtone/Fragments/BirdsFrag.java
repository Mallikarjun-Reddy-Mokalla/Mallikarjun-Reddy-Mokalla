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
import com.example.animalringtone.Adapter.BirdAdapter;
import com.example.animalringtone.Data.BirdUrl;
import com.example.animalringtone.R;

import java.util.ArrayList;

public class BirdsFrag extends Fragment implements AnimalRingtone.callback {
    RecyclerView recyclerView;
    BirdAdapter myBirdAdapter;
    GridLayoutManager gridLayoutManager;
    View view;
    ArrayList<BirdUrl> birdUrl = new ArrayList<>();


    String birdsSounds[] = {"A-Bird-In-A-Gilded-Cage.mp3", "Airtel-Bird-Ringtone.mp3", "Ambient-Crow-Shipwrecked.mp3",
            "Bird-Kenari.mp3", "Bird-sound-1.mp3", "bird-sound-2.mp3", "Bird-Sound-3.mp3", "Bird.mp3", "Birds-Love-Rain.mp3",
            "Blackbird.mp3", "Canary.mp3", "Cute-birds-ringtones.mp3", "Dis-Punkt.mp3", "dove.mp3", "duck-call.mp3", "Flycatcher-Bird.mp3",
            "Indian-birds-ringtones.mp3", "Insects-Cicada.mp3", "Insects.mp3", "Koko-Punkt.mp3", "Kookaburra-Bird.mp3",
            "Koyal-Bird-Ringtone.mp3", "Mentos.mp3", "Morgenfugl-Punkt.mp3", "Myna-Bird-sound.mp3", "Night-Bird.mp3",
            "Nord-Punkt.mp3", "Pigeon-Ringtone.mp3", "raven-ringtone.mp3", "rooster-crow.mp3", "Samsung-Bird-Ringtone.mp3",
            "Singing-Bird.mp3", "sparrow-ringtone.mp3", "Sweet-Bird-Ringtone.mp3", "The-Coast.mp3", "Waterfall-And-Birds.mp3",
            "Woodpecker-Ringtone.mp3"};

    String bird_names[] = {"Bird In Gilded Cage", "Airtel Bird Ringtone", "Ambient Crow Shipwrecked",
            "Bird Kenari", "Bird sound 1", "bird sound 2", "Bird Sound 3", "Bird", "BirdsLove Rain",
            "Blackbird", "Canary", "Cute birds ringtones", "Dis Punkt", "Dove", "Duck call", "Flycatcher Bird",
            "Indian birds ringtones", "Insects Cicada", "Insects.mp3", "Koko Punkt", "Kookaburra Bird",
            "Koyal Bird Ringtone.mp3", "Mentos.mp3", "Morgenfugl Punkt.mp3", "Myna Bird sound.mp3", "Night Bird.mp3",
            "Nord Punkt", "Pigeon Ringtone", "Raven ringtone", "Rooster crow.mp3", "Samsung Bird Ringtone",
            "Singing Bird", "Sparrow ringtone", "Sweet Bird Ringtone", "The Coast", "Waterfall And Birds",
            "Woodpecker Ringtone"};
    int bird_images[] = {R.drawable.birdcage, R.drawable.bird_airtel, R.drawable.ambiant_crow, R.drawable.kenari, R.drawable.bird_sound,
            R.drawable.bird_sound_1, R.drawable.birds_coast, R.drawable.birds, R.drawable.bid_in_rain, R.drawable.black_bird, R.drawable.bird_canary,
            R.drawable.bird_cute, R.drawable.bird_dis_punkt, R.drawable.bird_dove, R.drawable.duck, R.drawable.flycatcher, R.drawable.bird_indian,
            R.drawable.bird_insects_cicada, R.drawable.bird_insect_voice, R.drawable.bird_koko_punkt, R.drawable.bird_kookobura, R.drawable.bird_koyal,
            R.drawable.mentos, R.drawable.bird_morgenfugl, R.drawable.myna, R.drawable.nightbird, R.drawable.bird_nord_punkt, R.drawable.bird_pegion,
            R.drawable.raven, R.drawable.rooster_crow, R.drawable.samsungbird, R.drawable.singingbird, R.drawable.sparrow, R.drawable.bird_sweet,
            R.drawable.birds_coast,R.drawable.waterfallbird, R.drawable.woodpicker};

    public BirdsFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_birds, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        recyclerView = view.findViewById(R.id.recylerview_bird);
        recyclerView.setHasFixedSize(true);


        for (int i = 0 ; i < birdsSounds.length ; i++ ) {

            birdUrl.add(new BirdUrl(bird_names[i], birdsSounds[i], bird_images[i]));


        }

        gridLayoutManager = new GridLayoutManager(this.getActivity(), 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        myBirdAdapter = new BirdAdapter(this.getActivity(), birdUrl);
        recyclerView.setAdapter(myBirdAdapter);

    }


    @Override
    public void onPause() {
        super.onPause();
//        myBirdAdapter.onBackPressedStopPlayer(true);


    }

    @Override
    public void onStop() {
        super.onStop();
//        myBirdAdapter.stop(true);

    }

    @Override
    public void onResume() {
        super.onResume();
        myBirdAdapter.onBackPressedStopPlayer(true);

    }

    @Override
    public void onPageChanded() {
        myBirdAdapter.stop(true);
    }
}