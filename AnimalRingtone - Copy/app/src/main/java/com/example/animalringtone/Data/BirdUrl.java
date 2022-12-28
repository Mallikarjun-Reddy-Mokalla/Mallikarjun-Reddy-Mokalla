package com.example.animalringtone.Data;

public class BirdUrl {
    private String URL;
    private String birdname;
    private int birdimage;

    public BirdUrl(String birdname, String URL,   int birdimage) {
        this.birdname = birdname;
        this.URL = URL;
        this.birdimage = birdimage;

    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getBirdname() {
        return birdname;
    }

    public void setBirdname(String birdname) {
        this.birdname = birdname;
    }



    public int getBirdimage() {
        return birdimage;
    }

    public void setBirdimage(int birdimage) {
        this.birdimage = birdimage;
    }
}
