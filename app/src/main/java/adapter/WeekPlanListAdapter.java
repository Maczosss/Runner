package adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.runner.R;

import java.io.File;

import plan.WeeksPlan;

public class WeekPlanListAdapter extends RecyclerView.Adapter<WeekPlanListAdapter.WeekPlanViewHolder> {

    private WeeksPlan[] allFiles;
    private onItemListClick onItemListClick;

    public WeekPlanListAdapter(WeeksPlan[] allFiles, onItemListClick onItemListClick) {
        this.allFiles = allFiles;
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
        holder.list_title.setText(allFiles[position].getName());
        holder.list_date.setText(timeAgo.getTimeAgo(allFiles[position].lastModified()));
    }

    @Override
    public int getItemCount() {
        return allFiles.length;
    }

    public class WeekPlanViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView list_image;
        private TextView list_title;
        private TextView list_date;

        public WeekPlanViewHolder(@NonNull View itemView) {
            super(itemView);

            list_image = itemView.findViewById(R.id.list_image_view);
            list_title = itemView.findViewById(R.id.list_title);
            list_date = itemView.findViewById(R.id.list_date);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onItemListClick.onClickListener(allFiles[getAdapterPosition()], getAdapterPosition());
        }
    }

    public interface onItemListClick {
        void onClickListener(File file, int position);
    }
}
