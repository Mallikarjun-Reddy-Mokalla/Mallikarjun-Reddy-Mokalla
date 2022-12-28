package com.example.animalringtone.Data;

public class Wildanimalsdata {
    private String wildanimalname;
    private  String wildanimalsound;
    private  int wildanimalimage;

    public Wildanimalsdata(String wildanimalname, String wildanimalsound, int wildanimalimage) {
        this.wildanimalname = wildanimalname;
        this.wildanimalsound = wildanimalsound;
        this.wildanimalimage = wildanimalimage;
    }

    public int getWildanimalimage() {
        return wildanimalimage;
    }

    public void setWildanimalimage(int wildanimalimage) {
        this.wildanimalimage = wildanimalimage;
    }

    public String getWildanimalname() {
        return wildanimalname;
    }

    public void setWildanimalname(String wildanimalname) {
        this.wildanimalname = wildanimalname;
    }

    public String getWildanimalsound() {
        return wildanimalsound;
    }

    public void setWildanimalsound(String wildanimalsound) {
        this.wildanimalsound = wildanimalsound;
    }
}
