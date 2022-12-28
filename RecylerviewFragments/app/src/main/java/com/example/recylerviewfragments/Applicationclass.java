package com.example.recylerviewfragments;

import android.app.Application;
import android.content.ContentProvider;
import android.os.Build;

import java.util.ArrayList;

public class Applicationclass extends Application {
    public static ArrayList<Person> people;

    @Override
    public void onCreate() {
        super.onCreate();
        people =  new ArrayList<Person>();
        people.add(new Person("John","995864236"));
        people.add(new Person("Alex","885896742"));
        people.add(new Person("Marry","995888856"));
        people.add(new Person("Virat","995864236"));

    }
}
