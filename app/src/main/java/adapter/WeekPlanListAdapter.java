package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runner.R;

import fragments.WeekPlanListFragment;

public class WeekPlanListAdapter extends RecyclerView.Adapter<WeekPlanListAdapter.WeekPlanViewHolder> {

    private WeekPlanListFragment.ListItem[] allItems;
    private onItemListClick onItemListClick;

    public WeekPlanListAdapter(WeekPlanListFragment.ListItem[] allItems, onItemListClick onItemListClick) {
        this.allItems = allItems;
        this.onItemListClick = onItemListClick;
    }

    @NonNull
    @Override
    public WeekPlanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item, parent, false);
        return new WeekPlanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekPlanViewHolder holder, int position) {
        holder.week.setText(String.valueOf("Week: " + allItems[position].getWeekNumber()));
        holder.week_day.setText(String.valueOf("Day: " + allItems[position].getDayNumber()));
        holder.intervals.setText("Intervals:\n"+ String.valueOf(allItems[position].getIntervals())+ "m");
        holder.timeToWalk.setText("walk for:\n"+ String.valueOf(allItems[position].getTimeToWalk())+ "m");
        holder.timeToRun.setText("run for:\n"+ String.valueOf(allItems[position].getTimeToRun())+ "m");
    }

    @Override
    public int getItemCount() {
        return allItems.length;
    }

    public class WeekPlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView list_image;
        private TextView week, week_day, intervals, timeToWalk, timeToRun;

        public WeekPlanViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image = itemView.findViewById(R.id.list_image_view);
            week = itemView.findViewById(R.id.week);
            week_day = itemView.findViewById(R.id.week_day);
            intervals = itemView.findViewById(R.id.intervals);
            timeToWalk = itemView.findViewById(R.id.timeToWalk);
            timeToRun = itemView.findViewById(R.id.timeToRun);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListClick.onClickListener(allItems[getBindingAdapterPosition()], getBindingAdapterPosition());
        }
    }

    public interface onItemListClick {
        void onClickListener(WeekPlanListFragment.ListItem item, int position);
    }
}
