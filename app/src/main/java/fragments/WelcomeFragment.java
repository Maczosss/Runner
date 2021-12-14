package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.runner.R;

import listeners.ProgressSaver;

public class WelcomeFragment extends Fragment {

    ImageView background;

    TextView firstFragmentMaintext, infoAboutCurrentWorkout;
    ProgressSaver saver = ProgressSaver.getInstance();


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_first, container, false);
        firstFragmentMaintext = rootView.findViewById(R.id.firstFragmentMaintext);
        infoAboutCurrentWorkout = rootView.findViewById(R.id.firstFragmentSecondText);

        if(saver.getCurrentDayOfWeek()!=1 && saver.getWeek()!=1) {
            infoAboutCurrentWorkout.setText(String.format("You are on week: %d, and day: %d",
                    ProgressSaver.getInstance().getWeek(),
                    ProgressSaver.getInstance().getCurrentDayOfWeek()));
        }else{
            infoAboutCurrentWorkout.setText("Lets go running," +"\n"+ "start from beginning \nor swipe left and \nchoose starting day");
        }

        return rootView;
    }
}