package com.example.myapplication.show;

import static org.junit.jupiter.api.Assertions.*;

import android.content.Context;

import com.example.myapplication.NotesModal;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.ArrayList;

public class NotesAdapterTest {

    private static final int NOTES_COUNT = 1;
    private static NotesAdapter notesAdapter;

    @Before
    public void before() {
        notesAdapter = new NotesAdapter(prepareNotesArray(), Mockito.mock(Context.class));
    }

    @Test
    public void getItemCountTest() {
        assertEquals(NOTES_COUNT, notesAdapter.getItemCount());
    }

    private ArrayList<NotesModal> prepareNotesArray() {
        ArrayList<NotesModal> notes = new ArrayList<>();
        notes.add(new NotesModal("Przypomnienie",
                                 "Text",
                               null,
                            null));
        return notes;
    }
}