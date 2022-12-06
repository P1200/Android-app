package com.example.myapplication.menu.create;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.R;

public class CreateMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_menu);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT)
        {
            fragmentTransaction.replace(R.id.createMenuActivity, new VerticalCreateMenu());
        }
        else
        {
            fragmentTransaction.replace(R.id.createMenuActivity, new HorizontalCreateMenu());
        }
        fragmentTransaction.commit();
    }
}