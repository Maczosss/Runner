package com.example.runner;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import java.util.Locale;

import listeners.ProgressSaver;
import plan.DayOfWeek;

public class TimerActivity extends AppCompatActivity {
    //for testing
    private static final long START_TIME_FOR_RUN_IN_MILLIS = 5000; // 5 seconds sec3
    private static final long START_TIME_FOR_WALK_IN_MILLIS = 10000; //10 seconds sec9
    private int numberOfSeries = 4;
    //

    private DayOfWeek dayOfWeek =
            ProgressSaver
                    .getInstance()
                    .getWeekPlan()
                    .get()
                    .getDay(
                            ProgressSaver
                                    .getInstance()
                                    .getCurrentDayOfWeek());

//    private final long START_TIME_FOR_RUN_IN_MILLIS = dayOfWeek.getTimeToRun(); // 1 minute sec3
//    private final long START_TIME_FOR_WALK_IN_MILLIS = dayOfWeek.getTimeToWalk(); //5 minutes sec9
//    private final int BASE_NUMBER_OF_SERIES = dayOfWeek.getNumberOfIntervals();
//    private int numberOfSeries = BASE_NUMBER_OF_SERIES * 2;//multiplayng becouse it decreased for every action, not after walk and run


    private TextView timer;
    private CountDownTimer countDownTimer;
    private boolean timerCounting;
    private long timeLeftInMillis = START_TIME_FOR_WALK_IN_MILLIS;

    private ActivityType activityType = ActivityType.RUN;

    private ImageButton startBtn;
    private ImageButton resetBtn;
    private TextView textView;

    //todo name of file for storing data
//    private ProgressSaver progressSaver =
//            new ProgressSaver(this.getExternalFilesDir("/").getAbsolutePath());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        this.timer = findViewById(R.id.timer);
        this.startBtn = findViewById(R.id.startBtn);
        this.textView = findViewById(R.id.textField);
        this.resetBtn = findViewById(R.id.resetBtn);

        textView.setText("Number of intervals: " + numberOfSeries
                + "\nrunning for: " + (START_TIME_FOR_RUN_IN_MILLIS / 1000) / 60
                + "\nwalking time: " + (START_TIME_FOR_WALK_IN_MILLIS / 1000) % 60);

        startBtn.setOnClickListener(v -> {
            if (timerCounting) {
                pauseTimer();
                startBtn.setImageDrawable(getResources().getDrawable(R.drawable.play, null));
            } else {
                sendNotification(activityType.getType(),
                        String.format("Now You have to: %s for %s min",
                                activityType.getType(),
                                timer.getText().toString()));
                startTimer();
                startBtn.setImageDrawable(getResources().getDrawable(R.drawable.pause, null));
            }
//            sendNotification("","");
        });

        resetBtn.setOnClickListener(v -> {
//            sendNotification("","");
            if (timerCounting) {
                Toast.makeText(this, "First, pause the counter", Toast.LENGTH_LONG).show();
            } else {
                resetTimer();
            }
        });

        updateCountDown();
    }

    public void startTimer() {
//        for (int i = numberOfSeries; i < 0; i--) {

        countDownTimer = new CountDownTimer(timeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateCountDown();
            }

            @Override
            public void onFinish() {

                timerCounting = false;
                if (activityType == ActivityType.RUN) {
                    activityType = ActivityType.WALK;
                } else {
                    activityType = ActivityType.RUN;
                }
                resetTimer();
                sendNotification(
                        activityType.getType(),
                        String.format("Now You have to: %s for %s min",
                                activityType.getType(),
                                timer.getText().toString()));
                numberOfSeries--;
                if (numberOfSeries > 0)
                    startTimer();
                else {
                    sendNotification("Finish", "Training finished");
                    saveProgress();
                    resetData();
                }
            }
        }.start();

        timerCounting = true;
        //Todo implement pausebutton change the icon
        //startBtn.setText();
        //resetBtn.setVisibility(View.INVISIBLE);
    }

    private void saveProgress() {
        if (dayOfWeek.getDayNumber() < 3) {
            dayOfWeek.setDayNumber(dayOfWeek.getDayNumber() + 1);
            ProgressSaver.getInstance().saveForThisWeek(dayOfWeek.getDayNumber());
        } else {
            dayOfWeek.setDayNumber(1);
            ProgressSaver.getInstance().saveNextWeek();
        }
    }

    private void resetData() {
//        numberOfSeries = BASE_NUMBER_OF_SERIES * 2;
    }

    public void updateCountDown() {
        int minutes = (int) (timeLeftInMillis / 1000) / 60;
        int seconds = (int) (timeLeftInMillis / 1000) % 60;

        String timeLeft = String.format(Locale.getDefault(), "%2d:%2d", minutes, seconds);
        timer.setText(timeLeft);
    }

    public void pauseTimer() {
        countDownTimer.cancel();
        timerCounting = false;
        //todo implement visibility change
//        startBtn.setText();
//        resetBtn.setVisibility(View.VISIBLE);


    }

    public void resetTimer() {
        switch (activityType) {
            case RUN:
                timeLeftInMillis = START_TIME_FOR_RUN_IN_MILLIS;
                break;
            case WALK:
                timeLeftInMillis = START_TIME_FOR_WALK_IN_MILLIS;
                break;
        }
//        timeLeftInMillis = START_TIME_FOR_RUN_IN_MILLIS;
        updateCountDown();
        //todo implement
        //resetBtn.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("ending choooo");
    }

    private void sendNotification(String title, String message) {

        int NOTIFICATION_ID = 234;
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String CHANNEL_ID = "my_channel_01";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CHANNEL_ID = "my_channel_01";
            CharSequence name = "my_channel";
            String Description = "This is my channel";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
            mChannel.setDescription(Description);
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            mChannel.setShowBadge(false);
            notificationManager.createNotificationChannel(mChannel);
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setContentText(message);

        Intent resultIntent = new Intent(this.getApplicationContext(), TimerActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.getApplicationContext());
        stackBuilder.addParentStack(TimerActivity.class);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(resultPendingIntent);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
