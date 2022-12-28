package com.example.animalringtone.Data;

public class Ancientanimaldata {
    private String ancientname;
    private String ancientsound;
    private  int ancientimage;

    public Ancientanimaldata(String ancientname, String ancientsound, int ancientimage) {
        this.ancientname = ancientname;
        this.ancientsound = ancientsound;
        this.ancientimage = ancientimage;
    }

    public String getAncientname() {
        return ancientname;
    }

    public void setAncientname(String ancientname) {
        this.ancientname = ancientname;
    }

    public String getAncientsound() {
        return ancientsound;
    }

    public void setAncientsound(String ancientsound) {
        this.ancientsound = ancientsound;
    }

    public int getAncientimage() {
        return ancientimage;
    }

    public void setAncientimage(int ancientimage) {
        this.ancientimage = ancientimage;
    }
}
