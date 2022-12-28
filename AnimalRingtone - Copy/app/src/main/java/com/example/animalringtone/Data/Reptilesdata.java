package com.example.animalringtone.Data;

public class Reptilesdata {
    private String data;
    private String reptilename;
    int reptileimage;

    public Reptilesdata(String data, String reptilename, int reptileimage ) {
        this.data = data;
        this.reptilename = reptilename;
        this.reptileimage = reptileimage;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getReptilename() {
        return reptilename;
    }

    public void setReptilename(String reptilename) {
        this.reptilename = reptilename;
    }

    public int getReptileimage() {
        return reptileimage;
    }

    public void setReptileimage(int reptileimage) {
        this.reptileimage = reptileimage;
    }
}
