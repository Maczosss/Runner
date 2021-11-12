package com.example.runner;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class IntroductionActivity extends AppCompatActivity {

    ImageView background, name, name2;
    LottieAnimationView lottie;
    private Button startBtn, chooseBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        background = findViewById(R.id.backgroundIntroduction);
        name = findViewById(R.id.nameLogo);
        name2 = findViewById(R.id.nameLogo2);
        startBtn = findViewById(R.id.startBtn);
        chooseBtn = findViewById(R.id.chooseBtn);

        startBtn.setOnClickListener(v -> {
            start();
        });

        lottie = findViewById(R.id.lottie);

        background.animate().translationY(-3600).setDuration(1500).setStartDelay(1000).alpha(0);
        name.animate().translationY(1600).setDuration(1500).setStartDelay(1000).alpha(0);

        lottie.animate().translationY(1600).setDuration(1000).setStartDelay(1000).alpha(0);

        startBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
        name2.animate().alpha(1).setDuration(1500).setStartDelay(1600);
        chooseBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
    }

    public void start() {
        Intent intent;
        intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
    }
}