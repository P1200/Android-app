package com.example.myapplication.show.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.myapplication.DBHandler;
import com.example.myapplication.R;
import com.example.myapplication.show.ShowNotesActivity;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowNoteDetailActivity extends AppCompatActivity {

    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note_detail);

        Button deleteNoteButton = findViewById(R.id.delete_note_button);
        Button editNoteButton = findViewById(R.id.edit_note_button);
        dbHandler = new DBHandler(ShowNoteDetailActivity.this);

        String noteName = getIntent().getStringExtra("noteName");
        TextView noteNameText = findViewById(R.id.note_name);
        noteNameText.setText(noteName);
        readNoteFromSD(this, noteName);

        deleteNoteButton.setOnClickListener(view -> {
            deleteNoteFromSD(ShowNoteDetailActivity.this, noteName);
            dbHandler.deleteNote(noteName);

            Intent myIntent = new Intent(ShowNoteDetailActivity.this, ShowNotesActivity.class);
            startActivity(myIntent);
        });

        editNoteButton.setOnClickListener(view -> {
            Intent myIntent = new Intent(ShowNoteDetailActivity.this, EditTextNoteActivity.class);
            myIntent.putExtra("noteName", noteName);
            startActivity(myIntent);
        });
    }

    private void readNoteFromSD(Context context, String sFileName) {
        TextView noteNameText = findViewById(R.id.note_content);
        String noteContent = "";
        try {
            InputStream instream = new FileInputStream(new File(context.getFilesDir().getAbsolutePath() + "/Notes/" + sFileName));
            InputStreamReader inputreader = new InputStreamReader(instream);
            BufferedReader buffreader = new BufferedReader(inputreader);
            String line;
            try {
                while ((line = buffreader.readLine()) != null)
                    noteContent += line;
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("Read file failed", e.getMessage());
            }
            noteNameText.setText(noteContent);
        } catch (FileNotFoundException e) {
            noteContent = "I'm sorry. Note content not found.";
            noteNameText.setText(noteContent);
            noteNameText.setTextColor(Color.RED);

        } catch (Exception e) {
            String error;
            error = e.getMessage();
            Log.e("Read file failed", error);
        }
    }

    private void deleteNoteFromSD(Context context, String sFileName) {
            File root = new File(context.getFilesDir(), "Notes");
            File gpxfile = new File(root, sFileName);
            gpxfile.delete();
    }
}