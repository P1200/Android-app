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

    private static String NOTE_TYPE = "text";

    private EditText notePath, content;
    private Button addNoteButton;
    private DBHandler dbHandler;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_create_text_note_menu, container, false);

        // initializing all our variables.
        notePath = view.findViewById(R.id.noteNameEditText);
        content = view.findViewById(R.id.noteContentEditText);
        addNoteButton = view.findViewById(R.id.saveTextNoteButton);

        // creating a new dbhandler class
        // and passing our context to it.
        dbHandler = new DBHandler(getActivity());

        // below line is to add on click listener for our add course button.
        addNoteButton.setOnClickListener(v -> {

            // below line is to get data from all edit text fields.
            String noteName = notePath.getText().toString();
            String noteContent = content.getText().toString();

            // validating if the text fields are empty or not.
            if (noteName.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter the name of the note", Toast.LENGTH_LONG).show();
                return;
            }

            // on below line we are calling a method to add new
            // course to sqlite data and pass all our values to it.


            generateNoteOnSD(getActivity(), noteName, noteContent);
            Log.i("textNoteSaveToFile", "Text note has been correctly saved to file");
            dbHandler.addNewNote(noteName, NOTE_TYPE);
            Log.i("textNoteSaveToDB", "Text note has been saved to database");
            // after adding the data we are displaying a toast message.
            notePath.setText("");
            content.setText("");
        });

        return view;
    }

    public void generateNoteOnSD(Context context, String sFileName, String sBody) {
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
