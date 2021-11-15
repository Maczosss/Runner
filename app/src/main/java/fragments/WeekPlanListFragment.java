package fragments;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runner.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.WeekPlanListAdapter;
import listeners.ProgressSaver;
import plan.WeeksPlan;


public class WeekPlanListFragment extends Activity implements WeekPlanListAdapter.onItemListClick {

    //TODO previous and next buttons on mediaplayer

    private RecyclerView audioList;
    private Map<ListItem, Integer> newAllWeeksPlans = new HashMap<>();
    private List<ListItem> listItems = new ArrayList<>();
    private WeeksPlan[] allWeeksPlans;
    int id = 0;


    private WeekPlanListAdapter weekPlanListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_item_list);

        audioList = findViewById(R.id.item_list_view);

        allWeeksPlans = WeeksPlan.values();


        for (WeeksPlan file : allWeeksPlans) {
            ListItem item = new ListItem(file.getWeekNumber(), file.getDay1().getDayNumber());
            ListItem item2 = new ListItem(file.getWeekNumber(), file.getDay2().getDayNumber());
            ListItem item3 = new ListItem(file.getWeekNumber(), file.getDay3().getDayNumber());

            newAllWeeksPlans.put(item, id);
            listItems.add(item);
            id++;
            newAllWeeksPlans.put(item2, id);
            listItems.add(item2);
            id++;
            newAllWeeksPlans.put(item3, id);
            listItems.add(item3);
            id++;
        }
        //todo solve this workaround
        ListItem[] temp = new ListItem[listItems.size()];

        temp = listItems.toArray(new ListItem[0]);

        weekPlanListAdapter = new WeekPlanListAdapter(temp, this);

        audioList.setHasFixedSize(true);
        audioList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        audioList.setAdapter(weekPlanListAdapter);
    }

//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getActivity().setContentView(R.layout.fragment_item_list);
//
////        playerSheet = findViewById(R.id.player_sheet);
////        bottomSheetBehavior = BottomSheetBehavior.from(playerSheet);
//        audioList = findViewById(R.id.item_list_view);
//
//        allWeeksPlans = WeeksPlan.values();
//
//        ListItem[] temp;
//
//        for (WeeksPlan file : allWeeksPlans) {
//            ProgressSaver saver = ProgressSaver.getInstance();
//            ListItem item = new ListItem(saver.getWeek(), saver.getCurrentDayOfWeek());
//            newAllWeeksPlans.put(item, id);
//            listItems.add(item);
//            id++;
//        }
//        //todo solve this workaround
//        temp = (ListItem[]) listItems.toArray();
//
//        weekPlanListAdapter = new WeekPlanListAdapter(temp, this);
//
//        audioList.setHasFixedSize(true);
//        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
//        audioList.setAdapter(weekPlanListAdapter);
//
//    }


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


    public class ListItem {
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

        //todo add specification for every day
        public String getSpecification() {
            return "Specification would be added";
        }
    }
}