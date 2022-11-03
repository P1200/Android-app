package com.example.myapplication.menu.main;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.menu.create.CreateMenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.show.ShowNotesActivity;

public class VerticalMenu extends Fragment {

    Button showNotesButton;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_menu, container, false);
        Button button = view.findViewById(R.id.createNoteButton);
        button.setOnClickListener(v -> openCreateMenuActivity());
        showNotesButton = view.findViewById(R.id.showNotesButton);
        showNotesButton.setOnClickListener(v -> {
            // opening a new activity via a intent.
            Intent i = new Intent(getActivity(), ShowNotesActivity.class);
            startActivity(i);
        });

        return view;
    }

    private void openCreateMenuActivity() {
        Intent intent = new Intent(getActivity(), CreateMenuActivity.class);
        startActivity(intent);
    }
}
