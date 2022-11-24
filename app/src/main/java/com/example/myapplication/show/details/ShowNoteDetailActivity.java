package com.example.myapplication.show.details;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.myapplication.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShowNoteDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_note_detail);
        String noteName = getIntent().getStringExtra("noteName");
        TextView noteNameText = findViewById(R.id.note_name);
        noteNameText.setText(noteName);
        readNoteFromSD(this, noteName);
    }

    public void readNoteFromSD(Context context, String sFileName) {
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
}