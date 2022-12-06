package com.example.myapplication.create.text;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.DBHandler;
import com.example.myapplication.R;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HorizontalCreateTextNoteMenu extends Fragment {

    private static final String NOTE_TYPE = "text";

    private EditText notePath, content;
    private Button addNoteButton;
    private DBHandler dbHandler;
    private String noteName;
    private String noteContent;
    private CreateTextNoteActivity noteActivity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_create_text_note, container, false);
        initializeWidgets(view);

        notePath.setText(noteName);
        content.setText(noteContent);

        dbHandler = new DBHandler(getActivity());
        addNoteButton.setOnClickListener(v -> {
            String noteName = notePath.getText().toString();
            String noteContent = content.getText().toString();

            if (noteName.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter the name of the note", Toast.LENGTH_LONG).show();
                return;
            }

            generateNoteOnSD(getActivity(), noteName, noteContent);
            Log.i("textNoteSaveToFile", "Text note has been correctly saved to file");
            dbHandler.addNewNote(noteName, NOTE_TYPE);
            Log.i("textNoteSaveToDB", "Text note has been saved to database");
            notePath.setText("");
            content.setText("");
        });

        return view;
    }

    private void getNoteDataFromActivity() {
        noteName = getArguments().getString("noteName");
        noteContent = getArguments().getString("noteContent");
        noteActivity = (CreateTextNoteActivity) getActivity();
    }

    private void initializeWidgets(View view) {
        notePath = view.findViewById(R.id.noteNameEditText);
        content = view.findViewById(R.id.noteContentEditText);
        addNoteButton = view.findViewById(R.id.saveTextNoteButton);
    }

    @Override
    public void onPause() {
        noteActivity.noteName = notePath.getText().toString();
        noteActivity.noteContent = content.getText().toString();
        Log.i("onPause", notePath.getText().toString());
        super.onPause();
    }

    @Override
    public void onResume() {
        getNoteDataFromActivity();
        notePath.setText(noteActivity.noteName);
        content.setText(noteActivity.noteContent);
        Log.i("onResume", noteName);
        super.onResume();
    }

    private void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(context.getFilesDir(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
