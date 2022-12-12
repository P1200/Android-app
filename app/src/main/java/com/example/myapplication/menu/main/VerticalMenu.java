package com.example.myapplication.menu.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.AppInfoActivity;
import com.example.myapplication.menu.create.CreateMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.show.ShowNotesActivity;

public class VerticalMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_menu, container, false);
        Button button = view.findViewById(R.id.createNoteButton);
        button.setOnClickListener(v -> openCreateMenuActivity());
        Button showNotesButton = view.findViewById(R.id.showNotesButton);
        showNotesButton.setOnClickListener(v -> openShowNotesActivity());
        Button showAppInfoButton = view.findViewById(R.id.appInfoButton);
        showAppInfoButton.setOnClickListener(v -> openAppInfoActivity());

        return view;
    }

    private void openShowNotesActivity() {
        Intent i = new Intent(getActivity(), ShowNotesActivity.class);
        startActivity(i);
    }

    private void openCreateMenuActivity() {
        Intent intent = new Intent(getActivity(), CreateMenuActivity.class);
        startActivity(intent);
    }

    private void openAppInfoActivity() {
        Intent intent = new Intent(getActivity(), AppInfoActivity.class);
        startActivity(intent);
    }
}
