package com.example.animalringtone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.animalringtone.FragmentViewAdapter.ViewPageAdapter;
import com.example.animalringtone.Fragments.Ancient_Frag;
import com.example.animalringtone.Fragments.BirdsFrag;
import com.example.animalringtone.Fragments.Endangered_species_Frag;
import com.example.animalringtone.Fragments.InsectsFrag;
import com.example.animalringtone.Fragments.LandAnimalsFrag;
import com.example.animalringtone.Fragments.PetAnimalsFrag;
import com.example.animalringtone.Fragments.ReptilesAnphibiansFrag;

import com.example.animalringtone.Fragments.WaterAnimals_Frag;
import com.example.animalringtone.R;
import com.google.android.material.tabs.TabLayout;

public class AnimalRingtone extends AppCompatActivity {

    private ViewPager viewPager;
    TabLayout tabLayout;
    Fragment fragment = null;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    ImageView Settings;
    Toolbar toolbar;
    TextView textView;
    //    TabItem tab1, tab2, tab3, tab4, tab5, tab6, tab7, tab8;
    ViewPageAdapter viewPageAdapter;
    MediaPlayer mp;
    MediaPlayer oldmediaPlayer;
    MediaPlayer mediaPlayer;
    private int currentPage = 0;


    public interface callback{
        void onPageChanded();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animal_ringtone);
        tabLayout = findViewById(R.id.Tablayout);
        viewPager = findViewById(R.id.viewpager);
//        frameLayout = findViewById(R.id.framelayout);
//        Settings = findViewById(R.id.setting_bar);
        textView = findViewById(R.id.TvAR);
//        tab1 = findViewById(R.id.Ancient);
//        tab2 = findViewById(R.id.Endangered_species);
//        tab3 = findViewById(R.id.birds);
//        tab4 = findViewById(R.id.water_animals);
//        tab5 = findViewById(R.id.reptiles_anphilians);
//        tab6 = findViewById(R.id.pet_animals);
//        tab7 = findViewById(R.id.land_animals);
//        tab8 = findViewById(R.id.insects);


        viewPageAdapter = new ViewPageAdapter(getSupportFragmentManager());
        viewPageAdapter.addFragment(new Ancient_Frag(),"Ancient");
        viewPageAdapter.addFragment(new Endangered_species_Frag(),"Endangered species");
        viewPageAdapter.addFragment(new BirdsFrag(),"Birds");
        viewPageAdapter.addFragment(new WaterAnimals_Frag(),"Water animals");
        viewPageAdapter.addFragment(new ReptilesAnphibiansFrag(),"Reptiles and Anphlibians");
        viewPageAdapter.addFragment(new PetAnimalsFrag(),"Pet Animals");
        viewPageAdapter.addFragment(new LandAnimalsFrag(),"Land Animals");
        viewPageAdapter.addFragment(new InsectsFrag(),"Insects");
        viewPager.setAdapter(viewPageAdapter);
        tabLayout.setupWithViewPager(viewPager);
//        setSupportActionBar(toolbar);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                    if (mp !=null){
//                        mp.stop();
//                        mp.release();
//                        mp = null;
//
//                    }else return;

                }

                @Override
                public void onPageSelected(int position) {
                    fragment =viewPageAdapter.getItem(currentPage);
                    if (fragment instanceof callback && currentPage != position){
                        ((callback)viewPageAdapter.getItem(currentPage)).onPageChanded();
                    }
                    currentPage = position;

                }

                @Override
                public void onPageScrollStateChanged(int state) {

                }
            });

//        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                viewPager.setCurrentItem(tab.getPosition());
//                switch (tab.getPosition()) {
//                    case 0:
//                    fragment = new Ancient_Frag();
//                        break;
//                    case 1:
//                        fragment = new Endangered_species_Frag();
//                        break;
//                    case 2:
//                        fragment = new BirdsFrag();
//                        break;
//                    case 3:
//                        fragment = new WaterAnimals_Frag();
//                        break;
//                    case 4:
//                        fragment = new ReptilesAnphibiansFrag();
//                        break;
//                    case 5:
//                        fragment = new PetAnimalsFrag();
//                        break;
//                    case 6:
//                        fragment = new LandAnimalsFrag();
//                        break;
//                    case 7:
//                        fragment = new InsectsFrag();
//                        break;
//                }
//
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

//        Settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(AnimalRingtone.this, SettingsActivity.class);
//
//                startActivity(intent);
//
//            }
//        });

//        fragment = new Ancient_Frag();
//        fragmentManager = getSupportFragmentManager();
//        fragmentTransaction = fragmentManager.beginTransaction();
////        fragmentTransaction.replace(R.id.framelayout, fragment);
//        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//        fragmentTransaction.commit();
//        setSupportActionBar(toolbar);


//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                switch (tab.getPosition()) {
//                    case 0:
//                        fragment = new Ancient_Frag();
//                        break;
//                    case 1:
//                        fragment = new Endangered_species_Frag();
//                        break;
//                    case 2:
//                        fragment = new BirdsFrag();
//                        break;
//                    case 3:
//                        fragment = new WaterAnimals_Frag();
//                        break;
//                    case 4:
//                        fragment = new ReptilesAnphibiansFrag();
//                        break;
//                    case 5:
//                        fragment = new PetAnimalsFrag();
//                        break;
//                    case 6:
//                        fragment = new LandAnimalsFrag();
//                        break;
//                    case 7:
//                        fragment = new InsectsFrag();
//                        break;
//                }
//
//                FragmentManager fm = getSupportFragmentManager();
//                FragmentTransaction ft = fm.beginTransaction();
//                ft.replace(R.id.framelayout, fragment);
//                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
//                ft.commit();
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });

    }

    int count = 0;

    @Override
    public void onBackPressed() {

        count++;
        if (count == 2)
            super.onBackPressed();

    }

}
