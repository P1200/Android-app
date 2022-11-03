package com.example.myapplication.create.text;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.R;
import com.example.myapplication.menu.main.HorizontalMenu;
import com.example.myapplication.menu.main.VerticalMenu;

public class CreateTextNoteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_text_note);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT)
        {
            fragmentTransaction.replace(R.id.createTextNoteActivity, new VerticalCreateTextNoteMenu());
        }
        else
        {
            fragmentTransaction.replace(R.id.createTextNoteActivity, new HorizontalCreateTextNoteMenu());
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}