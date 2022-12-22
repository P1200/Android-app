package com.example.myapplication.show.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.DBHandler;
import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class EditTextNoteActivity extends AppCompatActivity {

    private EditText notePath, content;
    private Button updateNoteButton;
    private DBHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_text_note_activity);

        Intent intent = getIntent();
        String oldNoteName = intent.getStringExtra("noteName");

        readAndSetNoteContentFromSD(getApplicationContext(), oldNoteName);
        notePath = findViewById(R.id.noteNameEditText);
        notePath.setText(oldNoteName);

        initializeWidgets();

        dbHandler = new DBHandler(getApplicationContext());

        updateNoteButton.setOnClickListener(v -> {
            String newNoteName = notePath.getText().toString();
            String newNoteContent = content.getText().toString();

            if (newNoteName.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please enter the name of the note", Toast.LENGTH_LONG).show();
                return;
            }
            editNoteFile(getApplicationContext(), oldNoteName, newNoteName, newNoteContent);
            Log.i("textNoteSaveToFile", "Text note has been correctly saved to file");
            dbHandler.editNote(oldNoteName, newNoteName);
            Log.i("textNoteSaveToDB", "Text note has been saved to database");
            Intent myIntent = new Intent(EditTextNoteActivity.this, ShowNoteDetailActivity.class);
            myIntent.putExtra("noteName", newNoteName);
            startActivity(myIntent);
        });
    }

    private void initializeWidgets() {
        notePath = findViewById(R.id.noteNameEditText);
        content = findViewById(R.id.noteContentEditText);
        updateNoteButton = findViewById(R.id.saveTextNoteButton);
    }

    private void editNoteFile(Context context, String oldFileName, String newFileName, String newContent) {
        try {
            File root = new File(context.getFilesDir(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, oldFileName);
            gpxfile.renameTo(new File(root, newFileName));
            FileWriter writer = new FileWriter(gpxfile);
            writer.write(newContent);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Zapisano", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readAndSetNoteContentFromSD(Context context, String sFileName) {
        content = findViewById(R.id.noteContentEditText);
        String noteContent = "";
        try {
            InputStream instream =
                    new FileInputStream(new File(context.getFilesDir()
                                                        .getAbsolutePath() + "/Notes/" + sFileName));
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
            content.setText(noteContent);
        } catch (FileNotFoundException e) {
            noteContent = "I'm sorry. Note content not found.";
            content.setText(noteContent);
            content.setTextColor(Color.RED);

        } catch (Exception e) {
            String error;
            error = e.getMessage();
            Log.e("Read file failed", error);
        }
    }
}