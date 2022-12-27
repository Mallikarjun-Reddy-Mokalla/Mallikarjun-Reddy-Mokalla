package com.example.samplemodule;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.toasty.Toastgety;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toastgety.simpletoast(this,"SAMPLE MODULE DONE SUCCESSFULLY");
    }
}