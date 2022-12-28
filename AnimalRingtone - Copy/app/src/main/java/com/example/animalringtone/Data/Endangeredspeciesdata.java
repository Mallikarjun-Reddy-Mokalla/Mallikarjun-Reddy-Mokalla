package com.example.animalringtone.Data;

public class Endangeredspeciesdata {
    private String endangeredspecies_name;
    private String endangeredspecies_sound;
    private int endangeredspecies_image;

    public Endangeredspeciesdata(String endangeredspecies_name, String endangeredspecies_sound, int endangeredspecies_image) {
        this.endangeredspecies_name = endangeredspecies_name;
        this.endangeredspecies_sound = endangeredspecies_sound;
        this.endangeredspecies_image = endangeredspecies_image;
    }

    public String getEndangeredspecies_name() {
        return endangeredspecies_name;
    }

    public void setEndangeredspecies_name(String endangeredspecies_name) {
        this.endangeredspecies_name = endangeredspecies_name;
    }

    public String getEndangeredspecies_sound() {
        return endangeredspecies_sound;
    }

    public void setEndangeredspecies_sound(String endangeredspecies_sound) {
        this.endangeredspecies_sound = endangeredspecies_sound;
    }

    public int getEndangeredspecies_image() {
        return endangeredspecies_image;
    }

    public void setEndangeredspecies_image(int endangeredspecies_image) {
        this.endangeredspecies_image = endangeredspecies_image;
    }
}
