package com.example.animalringtone.FragmentViewAdapter;

import android.content.SyncAdapterType;
import android.media.MediaPlayer;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.animalringtone.Fragments.Ancient_Frag;
import com.example.animalringtone.Fragments.BirdsFrag;
import com.example.animalringtone.Fragments.Endangered_species_Frag;
import com.example.animalringtone.Fragments.InsectsFrag;
import com.example.animalringtone.Fragments.LandAnimalsFrag;
import com.example.animalringtone.Fragments.PetAnimalsFrag;
import com.example.animalringtone.Fragments.ReptilesAnphibiansFrag;
import com.example.animalringtone.Fragments.WaterAnimals_Frag;

import java.util.ArrayList;

public class ViewPageAdapter extends FragmentPagerAdapter {
    MediaPlayer oldmediaPlayer;
    MediaPlayer mediaPlayer;
    RecyclerView.ViewHolder oldHolder;


    private final ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
    private final ArrayList<String> fragmentTitle = new ArrayList<>();


    public ViewPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        return fragmentArrayList.get(position);
//        Fragment fragment = null;
//        if (position== 0)
//            fragment = new Ancient_Frag();
//        else if (position==1)
//          fragment  =new Endangered_species_Frag();
//        else if (position==2)
//            fragment = new BirdsFrag();
//        else if (position ==3)
//            fragment = new WaterAnimals_Frag();
//
//          else if (position ==4)
//            fragment = new ReptilesAnphibiansFrag();
//
//          else if (position ==5)
//            fragment = new PetAnimalsFrag();
//
//          else if (position ==6)
//            fragment = new LandAnimalsFrag();
//        else if (position == 7)
//            fragment = new InsectsFrag();
//        System.out.println(fragment);
//        return fragment;
    }

    @Override
    public int getCount() {
        return fragmentArrayList.size();
    }

    public void addFragment(Fragment fragment, String title) {
        fragmentArrayList.add(fragment);
        fragmentTitle.add(title);

    }


    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {

        return fragmentTitle.get(position);
//        String title = null;
//        if (position == 0)
//            title = "Ancient";
//        else if (position == 1)
//            title = "Endangered species";
//        else if (position == 2)
//            title = "Birds";
//        if (position == 3)
//            title = "Water animals";
//        else if (position == 4)
//            title = "Reptiles and Anphlibians";
//        else if (position == 5)
//            title = "Pet Animals";
//        else if (position == 6)
//            title = "Land Animals";
//        else if (position == 7)
//            title = "Insects";
//        System.out.println(title);
//        return title;

    }


}
