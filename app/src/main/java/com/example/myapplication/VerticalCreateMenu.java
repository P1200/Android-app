package com.example.myapplication;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class VerticalCreateMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_create_menu, container, false);

        Button readFromFilesButton = (Button) view.findViewById(R.id.readNoteFromFileButton);
        readFromFilesButton.setOnClickListener(v -> openReadFromFileActivity());

        Button button = view.findViewById(R.id.createTextNoteButton);
        button.findViewById(R.id.createTextNoteButton).setOnClickListener(v -> {
            super.onCreate(savedInstanceState);
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            if (getResources().getConfiguration().orientation ==
                    Configuration.ORIENTATION_PORTRAIT)
            {
                fragmentTransaction.replace(R.id.linlay, new VerticalCreateTextNoteMenu());
            }
            else
            {
                fragmentTransaction.replace(R.id.linlay, new HorizontalCreateTextNoteMenu());
            }
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
        });

        return view;
    }

    private void openReadFromFileActivity() {
        Intent intent = new Intent(getActivity(), ReadFromFileActivity.class);
        startActivity(intent);
    }
}
