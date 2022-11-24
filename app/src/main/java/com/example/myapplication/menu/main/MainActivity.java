package com.example.myapplication.menu.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;

import com.example.myapplication.R;
import com.example.myapplication.AppIntro;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AppIntro canva = new AppIntro(this);
        setContentView(canva);
        Intent intent = new Intent(this, MainMenu.class);
        new CountDownTimer(3000, 1000) { // 5000 = 5 sec

            public void onTick(long millisUntilFinished) {
            }

            public void onFinish() {
                startActivity(intent);
            }
        }.start();
    }
}