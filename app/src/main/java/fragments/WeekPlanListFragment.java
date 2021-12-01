package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runner.R;
import com.example.runner.TimerActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.WeekPlanListAdapter;
import listeners.ProgressSaver;
import plan.WeeksPlan;
import visual.RecyclerListDecoration;


public class WeekPlanListFragment extends Fragment implements WeekPlanListAdapter.onItemListClick {

    private RecyclerView audioList;
    private Map<ListItem, Integer> newAllWeeksPlans = new HashMap<>();
    private List<ListItem> listItems = new ArrayList<>();
    private WeeksPlan[] allWeeksPlans;
    int id = 0;


    private WeekPlanListAdapter weekPlanListAdapter;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_item_list, container, false);

        audioList = rootView.findViewById(R.id.item_list_view);

        allWeeksPlans = WeeksPlan.values();


        for (WeeksPlan file : allWeeksPlans) {
            ListItem item = new ListItem(file.getWeekNumber(), file.getDay1().getDayNumber(), file.getDay1().getSpecification());
            ListItem item2 = new ListItem(file.getWeekNumber(), file.getDay2().getDayNumber(), file.getDay2().getSpecification());
            ListItem item3 = new ListItem(file.getWeekNumber(), file.getDay3().getDayNumber(), file.getDay3().getSpecification());

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
        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
        audioList.setItemAnimator(new DefaultItemAnimator());
        audioList.setAdapter(weekPlanListAdapter);

        //decorations
        audioList.addItemDecoration(new RecyclerListDecoration(10, 10));
        return rootView;
    }

    @Override
    public void onClickListener(ListItem item, int position) {
        ProgressSaver.getInstance().setWeek(item.getWeekNumber());
        ProgressSaver.getInstance().setCurrentDayOfWeek(item.getDayNumber());
        Intent intent = new Intent(getContext(), TimerActivity.class);
        startActivity(intent);
    }


    public class ListItem {
        private int weekNumber;
        private int dayNumber;
        private String specification;

        public ListItem(int weekNumber, int dayNumber) {
            this.weekNumber = weekNumber;
            this.dayNumber = dayNumber;
        }

        public ListItem(int weekNumber, int dayNumber, String specification) {
            this.weekNumber = weekNumber;
            this.dayNumber = dayNumber;
            this.specification = specification;
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

        public String getSpecification() {
            return specification;
        }
    }
}