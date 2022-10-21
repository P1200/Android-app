package com.example.myapplication;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class ReadFromFileActivity extends AppCompatActivity {
    private VideoView videoView;
    MediaController mediaControls;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_from_file_activity);
        videoView = findViewById(R.id.videoView);
        Button button = findViewById(R.id.button);
        mediaControls = new MediaController(ReadFromFileActivity.this);
        mediaControls.setAnchorView(videoView);
        ActivityResultLauncher<String> fileChooser = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                result -> {
                    videoView.setVideoURI(result);
                    videoView.setVisibility(View.VISIBLE);
                    videoView.setMediaController(mediaControls);
                    videoView.start();
                }
        );
        button.setOnClickListener(view -> fileChooser.launch("video/mp4"));
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(arg0 -> {
            //Start new activity class
            Intent myIntent = new Intent(ReadFromFileActivity.this, Image.class);
            startActivity(myIntent);
        });
    }
}
