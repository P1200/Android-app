package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class ShowNotesActivity extends AppCompatActivity {

    private ArrayList<NotesModal> notesModalArrayList;
    private DBHandler dbHandler;
    private NotesAdapter notesAdapter;
    private RecyclerView notesRV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_notes);

        notesModalArrayList = new ArrayList<>();
        dbHandler = new DBHandler(ShowNotesActivity.this);

        notesModalArrayList = dbHandler.readNotes();

        notesAdapter = new NotesAdapter(notesModalArrayList, ShowNotesActivity.this);
        notesRV = findViewById(R.id.showNotesView);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(ShowNotesActivity.this, RecyclerView.VERTICAL, false);
        notesRV.setLayoutManager(linearLayoutManager);

        notesRV.setAdapter(notesAdapter);
    }
}