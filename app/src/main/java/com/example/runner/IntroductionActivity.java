package com.example.runner;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.airbnb.lottie.LottieAnimationView;

import fragments.FirstFragment;
import fragments.ThirdFragment;
import fragments.WeekPlanListFragment;
import listeners.ProgressSaver;
import plan.WeeksPlan;

public class IntroductionActivity extends AppCompatActivity {

    ImageView background, name, name2;
    LottieAnimationView lottie;
//    private Button startBtn, chooseBtn;
    TextView infoAboutCurrentWorkout;
    ProgressSaver saver = ProgressSaver.getInstance();

    //sliding fragments
    private static final int NUMBER_OF_FRAGMENTS = 3;
    private ViewPager pager;
    private ScreenSlideAdapter pagerAdapter;


    //animation

    Animation animation;

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
//        startBtn = findViewById(R.id.startBtn);
//        chooseBtn = findViewById(R.id.chooseBtn);
//        infoAboutCurrentWorkout = findViewById(R.id.textFieldIntroductionInformation);

        //liquid pager for swiping
        pager = findViewById(R.id.liquid_pager);
        pagerAdapter = new ScreenSlideAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

        //todo animation is next
        //animation = AnimationUtils.loadAnimation(this, R.anim.animation);




//        startBtn.setOnClickListener(v -> {
//            start();
//        });

//        chooseBtn.setOnClickListener(v->{
////            listOfPlans.setVisibility(View.VISIBLE);
////            FragmentManager fm = getSupportFragmentManager();
////            fragment = fm.findFragmentByTag("myFragmentTag");
////            if (fragment == null) {
////                FragmentTransaction ft = fm.beginTransaction();
////                fragment =new WeekPlanListFragment();
////                ft.add(android.R.id.content,fragment,"myFragmentTag");
////                ft.commit();
////            }
//
//            Intent intent;
//            intent = new Intent(getApplicationContext(), WeekPlanListFragment.class);
//            startActivity(intent);
//        });

        lottie = findViewById(R.id.lottie);


        //animations
        background.animate().translationY(-3600).setDuration(1500).setStartDelay(1000).alpha(0);
        name.animate().translationY(1600).setDuration(1500).setStartDelay(1000).alpha(0);

        lottie.animate().translationY(1600).setDuration(1000).setStartDelay(1000).alpha(0);

//        startBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
//        name2.animate().alpha(1).setDuration(1500).setStartDelay(1600);
//        chooseBtn.animate().alpha(1).setDuration(1000).setStartDelay(1400);
//        infoAboutCurrentWorkout.animate().translationY(-100).alpha(1).setDuration(1500).setStartDelay(1400);
    }

    public void start() {
        Intent intent;
        intent = new Intent(getApplicationContext(), TimerActivity.class);
        startActivity(intent);
    }

    //todo get rid of depricated interface
    private class ScreenSlideAdapter extends FragmentStatePagerAdapter {

        public ScreenSlideAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    FirstFragment tab1 = new FirstFragment();
                    return tab1;
                case 1:
//                    SecondFragment tab2 = new SecondFragment();
                    WeekPlanListFragment tab2 = new WeekPlanListFragment();
                    return tab2;
                case 2:
                    ThirdFragment tab3 = new ThirdFragment();
                    return tab3;
                default:
                    return new Fragment();
            }
        }

        @Override
        public int getCount() {
            return NUMBER_OF_FRAGMENTS;
        }
    }
}