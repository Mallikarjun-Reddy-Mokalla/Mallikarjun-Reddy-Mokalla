package com.example.animalringtone.Data;

public class Petanimalsdata {
    private String petname;
    private String petsound;
    private int petimage;

    public Petanimalsdata(String petname, String petsound, int petimage) {
        this.petname = petname;
        this.petsound = petsound;
        this.petimage = petimage;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetsound() {
        return petsound;
    }

    public void setPetsound(String petsound) {
        this.petsound = petsound;
    }

    public int getPetimage() {
        return petimage;
    }

    public void setPetimage(int petimage) {
        this.petimage = petimage;
    }
}
