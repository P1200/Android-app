package com.example.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HorizontalMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_menu, container, false);
        Button button = view.findViewById(R.id.createNoteButton);
        button.findViewById(R.id.createNoteButton).setOnClickListener(v -> {
            super.onCreate(savedInstanceState);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            if (getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_PORTRAIT)
            {
                fragmentTransaction.replace(R.id.linlay, new VerticalCreateMenu());
            }
            else
            {
                fragmentTransaction.replace(R.id.linlay, new HorizontalCreateMenu());
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });
        return view;
    }
}
