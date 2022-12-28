package com.example.animalringtone.Data;

public class Insectsadata {
    private String insectname;
    private String insectsound;
    private int insectimage;

    public Insectsadata(String insectname, String insectsound, int insectimage) {
        this.insectname = insectname;
        this.insectsound = insectsound;
        this.insectimage = insectimage;
    }

    public String getInsectname() {
        return insectname;
    }

    public void setInsectname(String insectname) {
        this.insectname = insectname;
    }

    public String getInsectsound() {
        return insectsound;
    }

    public void setInsectsound(String insectsound) {
        this.insectsound = insectsound;
    }

    public int getInsectimage() {
        return insectimage;
    }

    public void setInsectimage(int insectimage) {
        this.insectimage = insectimage;
    }
}
