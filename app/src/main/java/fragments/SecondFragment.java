package fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.runner.R;
import com.example.runner.TimerActivity;

public class SecondFragment extends Fragment {

    Button fragmenstStartBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_second, container, false);

        fragmenstStartBtn = rootView.findViewById(R.id.fragmentStartBtn);

        fragmenstStartBtn.setOnClickListener(v->{
            start();
        });
        return rootView;
    }

    public void start() {
        Intent intent;
        intent = new Intent(getActivity(), TimerActivity.class);
        startActivity(intent);
    }
}