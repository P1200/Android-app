package com.example.myapplication.menu.main;

import androidx.appcompat.app.AppCompatActivity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.res.Configuration;
import android.os.Bundle;

import com.example.myapplication.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction =
                fragmentManager.beginTransaction();
        if (getResources().getConfiguration().orientation ==
                Configuration.ORIENTATION_PORTRAIT)
        {
            fragmentTransaction.replace(R.id.linlay, new VerticalMenu());
        }
        else
        {
            fragmentTransaction.replace(R.id.linlay, new HorizontalMenu());
        }
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}