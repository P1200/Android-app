package com.example.myapplication.create.text;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.R;

public class CreateTextNoteActivity extends AppCompatActivity {

    String noteName = "";
    String noteContent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text_note);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        Bundle args = new Bundle();

        SharedPreferences note = getSharedPreferences("note", 0);
        noteName = note.getString("name", "");
        noteContent = note.getString("content", "");

        args.putString("noteName", noteName);
        args.putString("noteContent", noteContent);
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT)
        {
            VerticalCreateTextNoteMenu fragment = new VerticalCreateTextNoteMenu();
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.createTextNoteActivity, fragment);
        }
        else
        {
            HorizontalCreateTextNoteMenu fragment = new HorizontalCreateTextNoteMenu();
            fragment.setArguments(args);
            fragmentTransaction.replace(R.id.createTextNoteActivity, fragment);
        }
        fragmentTransaction.commit();
    }

    @Override
    protected void onPause() {
        SharedPreferences note = getSharedPreferences("note", 0);
        SharedPreferences.Editor editor = note.edit();
        editor.putString("name", noteName);
        editor.putString("content", noteContent);
        editor.apply();
        super.onPause();
    }
}