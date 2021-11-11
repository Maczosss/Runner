package com.example.runner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.transition.Fade;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import listeners.ProgressSaver;

public class MainActivity extends AppCompatActivity {

    ImageButton btn;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Transition transition = new Fade();
//        transition.setDuration(600);
//        transition.addTarget(R.id.image);
//
//        TransitionManager.beginDelayedTransition(, transition);
//        image.setVisibility(show ? View.VISIBLE : View.GONE);

        btn = findViewById(R.id.test);
        image = findViewById(R.id.image);


        ProgressSaver.initInstance(this.getExternalFilesDir("/").getAbsolutePath());
        ProgressSaver progressSaver = ProgressSaver.getInstance();
//                new ProgressSaver(this.getExternalFilesDir("/").getAbsolutePath());

//        System.out.println(progressSaver.getWeek());

        btn.setOnClickListener(v->{
            start();
        });

//        btn.setOnClickListener(v -> {
//
//            int NOTIFICATION_ID = 234;
//            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            String CHANNEL_ID = "my_channel_01";
//
//            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//                CHANNEL_ID = "my_channel_01";
//                CharSequence name = "my_channel";
//                String Description = "This is my channel";
//                int importance = NotificationManager.IMPORTANCE_HIGH;
//                NotificationChannel mChannel = new NotificationChannel(CHANNEL_ID, name, importance);
//                mChannel.setDescription(Description);
//                mChannel.enableLights(true);
//                mChannel.setLightColor(Color.RED);
//                mChannel.enableVibration(true);
//                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//                mChannel.setShowBadge(false);
//                notificationManager.createNotificationChannel(mChannel);
//            }
//
//            NotificationCompat.Builder builder = new NotificationCompat.Builder(this.getApplicationContext(), CHANNEL_ID)
//                    .setSmallIcon(R.mipmap.ic_launcher)
//                    .setContentTitle("title")
//                    .setContentText("mess");
//
//            Intent resultIntent = new Intent(this.getApplicationContext(), MainActivity.class);
//            TaskStackBuilder stackBuilder = TaskStackBuilder.create(this.getApplicationContext());
//            stackBuilder.addParentStack(MainActivity.class);
//            stackBuilder.addNextIntent(resultIntent);
//            PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
//            builder.setContentIntent(resultPendingIntent);
//            notificationManager.notify(NOTIFICATION_ID, builder.build());
//
//
////            NotificationCompat.Builder notificationBuilder =
////                    new NotificationCompat.Builder(TimerActivity.this, "TimerActivity");
////            notificationBuilder.setContentTitle("RUN");
////            notificationBuilder.setContentText("For next: "+"5 min");
////            notificationBuilder.setSmallIcon(R.drawable.play);
////            notificationBuilder.setAutoCancel(true);
////
////            NotificationManagerCompat managerCompat = NotificationManagerCompat.from(TimerActivity.this);
////            managerCompat.notify(1, notificationBuilder.build());
//        });



//        Intent intent;
//        intent = new Intent(getApplicationContext(), TimerActivity.class);
//        startActivity(intent);
    }

    public void start(){
        Intent intent;
//        intent = new Intent(getApplicationContext(), TimerActivity.class);
        intent = new Intent(getApplicationContext(), TestActivity.class);
        startActivity(intent);
    }
}