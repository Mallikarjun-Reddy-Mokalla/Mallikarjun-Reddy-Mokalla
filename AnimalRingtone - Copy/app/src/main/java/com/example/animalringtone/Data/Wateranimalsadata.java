package com.example.animalringtone.Data;

public class Wateranimalsadata {
    private String wateranimalname;
    private String wateranimalsound;
    private  int wateranimalimage;

    public Wateranimalsadata(String wateranimalname, String wateranimalsound, int wateranimalimage) {
        this.wateranimalname = wateranimalname;
        this.wateranimalsound = wateranimalsound;
        this.wateranimalimage = wateranimalimage;
    }

    public String getWateranimalname() {
        return wateranimalname;
    }

    public void setWateranimalname(String wateranimalname) {
        this.wateranimalname = wateranimalname;
    }

    public String getWateranimalsound() {
        return wateranimalsound;
    }

    public void setWateranimalsound(String wateranimalsound) {
        this.wateranimalsound = wateranimalsound;
    }

    public int getWateranimalimage() {
        return wateranimalimage;
    }

    public void setWateranimalimage(int wateranimalimage) {
        this.wateranimalimage = wateranimalimage;
    }
}
