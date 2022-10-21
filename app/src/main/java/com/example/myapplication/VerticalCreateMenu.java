package com.example.myapplication;

import android.app.Fragment;
import android.content.Intent;
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

        Button button = (Button) view.findViewById(R.id.readNoteFromFileButton);
        button.setOnClickListener(v -> openReadFromFileActivity());

        return view;
    }

    private void openReadFromFileActivity() {
        Intent intent = new Intent(getActivity(), ReadFromFileActivity.class);
        startActivity(intent);
    }
}
