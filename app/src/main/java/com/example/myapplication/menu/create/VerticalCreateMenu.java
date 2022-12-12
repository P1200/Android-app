package com.example.myapplication.menu.create;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.myapplication.create.map.CreateMapNoteActivity;
import com.example.myapplication.create.text.CreateTextNoteActivity;
import com.example.myapplication.R;

public class VerticalCreateMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.vertical_create_menu, container, false);

        Button readFromFilesButton = view.findViewById(R.id.createMapNoteButton);
        readFromFilesButton.setOnClickListener(v -> openCreateMapNoteActivity());

        Button button = view.findViewById(R.id.createTextNoteButton);
        button.findViewById(R.id.createTextNoteButton).setOnClickListener(v -> openCreateTextNoteActivity());

        return view;
    }

    private void openCreateMapNoteActivity() {
        Intent intent = new Intent(getActivity(), CreateMapNoteActivity.class);
        startActivity(intent);
    }

    private void openCreateTextNoteActivity() {
        Intent intent = new Intent(getActivity(), CreateTextNoteActivity.class);
        startActivity(intent);
    }
}
