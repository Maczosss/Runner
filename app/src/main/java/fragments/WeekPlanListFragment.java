package fragments;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.SeekBar;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import adapter.WeekPlanListAdapter;
import listeners.ProgressSaver;
import plan.WeeksPlan;

import com.example.runner.R;
import com.google.android.material.bottomsheet.BottomSheetBehavior;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



public class WeekPlanListFragment extends Fragment implements WeekPlanListAdapter.onItemListClick {

    //TODO previous and next buttons on mediaplayer

    private RecyclerView audioList;
    private Map<ListItem, Integer> newAllWeeksPlans = new HashMap<>();
    private WeeksPlan[] allWeeksPlans;
    int id = 0;


    private WeekPlanListAdapter weekPlanListAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setContentView(R.layout.activity_audio_list);

//        playerSheet = findViewById(R.id.player_sheet);
//        bottomSheetBehavior = BottomSheetBehavior.from(playerSheet);
        audioList = findViewById(R.id.audio_list_view);

        allWeeksPlans = WeeksPlan.values();

        for (WeeksPlan file : allWeeksPlans) {
            ProgressSaver saver = ProgressSaver.getInstance();
            ListItem item = new ListItem(saver.getWeek(), saver.getCurrentDayOfWeek());
            newAllWeeksPlans.put(item, id);
            id++;
        }

        weekPlanListAdapter = new WeekPlanListAdapter(allWeeksPlans, this);

        audioList.setHasFixedSize(true);
        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
        audioList.setAdapter(weekPlanListAdapter);

    }


    @Override
    public void onClickListener(ListItem item, int position) {
        ProgressSaver.getInstance().setWeek(item.getWeekNumber());
        ProgressSaver.getInstance().setCurrentDayOfWeek(item.getDayNumber());
    }

//    @Override
//    public void onClickListener(File file, int position) {
//        fileToPlay = file;
//        if (isPlaying) {
//            stopAudio();
//            playAudio(fileToPlay);
//        } else {
//            playAudio(fileToPlay);
//        }
//    }



    protected class ListItem{
        private int weekNumber;
        private int dayNumber;

        public ListItem(int weekNumber, int dayNumber) {
            this.weekNumber = weekNumber;
            this.dayNumber = dayNumber;
        }

        public int getWeekNumber() {
            return weekNumber;
        }

        public int getDayNumber() {
            return dayNumber;
        }

        public void setWeekNumber(int weekNumber) {
            this.weekNumber = weekNumber;
        }

        public void setDayNumber(int dayNumber) {
            this.dayNumber = dayNumber;
        }
    }
}