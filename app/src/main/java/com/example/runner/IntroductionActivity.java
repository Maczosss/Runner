package com.example.runner;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;

import fragments.WeekPlanListFragment;
import listeners.ProgressSaver;
import plan.WeeksPlan;

public class IntroductionActivity extends AppCompatActivity {

    ImageView background, name, name2;
    LottieAnimationView lottie;
    private Button startBtn, chooseBtn;
    TextView infoAboutCurrentWorkout;
    ProgressSaver saver = ProgressSaver.getInstance();
    //TODO list for choosing workplan on application start
    WeeksPlan[] plan = WeeksPlan.values();
    ListView listOfPlans;

    Fragment fragment;

    private static final int CONTENT_VIEW_ID = 10101010;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_introduction);

        background = findViewById(R.id.backgroundIntroduction);
        name = findViewById(R.id.nameLogo);
        name2 = findViewById(R.id.nameLogo2);
        startBtn = findViewById(R.id.startBtn);
        chooseBtn = findViewById(R.id.chooseBtn);
        infoAboutCurrentWorkout = findViewById(R.id.textFieldIntroductionInformation);

        //=============================




        //============================
        listOfPlans = findViewById(R.id.list);

        String[] test = {
                "Maciej",
                "Sraciej",
                "Kutaciej",
        };

        ArrayAdapter<String> arr;
        arr
                = new ArrayAdapter<String>(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                test);
        listOfPlans.setAdapter(arr);

        //=====================

        if(saver.getCurrentDayOfWeek()!=1 && saver.getWeek()!=1) {
            infoAboutCurrentWorkout.setText(String.format("You are on week: %d, and day: %d",
                    ProgressSaver.getInstance().getWeek(),
                    ProgressSaver.getInstance().getCurrentDayOfWeek()));
        }else{
            infoAboutCurrentWorkout.setText("Lets go running, start from beginning \n" +
                    " or choose starting day");
        }


        startBtn.setOnClickListener(v -> {
            start();
        });

        chooseBtn.setOnClickListener(v->{
//            listOfPlans.setVisibility(View.VISIBLE);
//            FragmentManager fm = getSupportFragmentManager();
//            fragment = fm.findFragmentByTag("myFragmentTag");
//            if (fragment == null) {
//                FragmentTransaction ft = fm.beginTransaction();
//                fragment =new WeekPlanListFragment();
//                ft.add(android.R.id.content,fragment,"myFragmentTag");
//                ft.commit();
//            }

            Intent intent;
            intent = new Intent(getApplicationContext(), WeekPlanListFragment.class);
            startActivity(intent);
        });

        lottie = findViewById(R.id.lottie);


        //animations
        background.animate().translationY(-3600).setDuration(1500).setStartDelay(1000).alpha(0);
        name.animate().translationY(1600).setDuration(1500).setStartDelay(1000).alpha(0);

        lottie.animate().translationY(1600).setDuration(1000).setStartDelay(1000).alpha(0);

        startBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
        name2.animate().alpha(1).setDuration(1500).setStartDelay(1600);
        chooseBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
        infoAboutCurrentWorkout.animate().translationY(-100).alpha(1).setDuration(1500).setStartDelay(1400);
    }

    public void start() {
        Intent intent;
        intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
    }
}