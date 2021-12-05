package com.example.runner;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import listeners.ProgressSaver;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressSaver.initInstance(this.getExternalFilesDir("/").getAbsolutePath());
        ProgressSaver progressSaver = ProgressSaver.getInstance();

        start();
    }

    public void start() {
        Intent intent;
        intent = new Intent(getApplicationContext(), IntroductionActivity.class);
        startActivity(intent);
    }
}