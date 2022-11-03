package com.example.myapplication.menu.create;

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

import com.example.myapplication.create.text.CreateTextNoteActivity;
import com.example.myapplication.create.text.HorizontalCreateTextNoteMenu;
import com.example.myapplication.R;
import com.example.myapplication.ReadFromFileActivity;
import com.example.myapplication.create.text.VerticalCreateTextNoteMenu;

public class HorizontalCreateMenu extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_create_menu, container, false);

        Button readFromFilesButton = view.findViewById(R.id.readNoteFromFileButton);
        readFromFilesButton.setOnClickListener(v -> openReadFromFileActivity());

        Button button = view.findViewById(R.id.createTextNoteButton);
        button.findViewById(R.id.createTextNoteButton).setOnClickListener(v -> openCreateTextNoteActivity());

        return view;
    }

    private void openReadFromFileActivity() {
        Intent intent = new Intent(getActivity(), ReadFromFileActivity.class);
        startActivity(intent);
    }

    private void openCreateTextNoteActivity() {
        Intent intent = new Intent(getActivity(), CreateTextNoteActivity.class);
        startActivity(intent);
    }
}
