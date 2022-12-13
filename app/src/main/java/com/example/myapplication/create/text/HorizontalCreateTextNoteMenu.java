package com.example.myapplication.create.text;

import android.app.Fragment;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

import com.example.myapplication.DBHandler;
import com.example.myapplication.R;
import com.example.myapplication.show.details.ShowNoteDetailActivity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class HorizontalCreateTextNoteMenu extends Fragment {

    private static final String NOTE_TYPE = "text";

    private EditText notePath, content;
    private Button addNoteButton;
    private DBHandler dbHandler;
    private String noteName;
    private String noteContent;
    private CreateTextNoteActivity noteActivity;

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.horizontal_create_text_note, container, false);
        initializeWidgets(view);

        notePath.setText(noteName);
        content.setText(noteContent);

        dbHandler = new DBHandler(getActivity());
        addNoteButton.setOnClickListener(v -> {
            String noteName = notePath.getText().toString();
            String noteContent = content.getText().toString();

            if (noteName.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter the name of the note", Toast.LENGTH_LONG).show();
                return;
            }

            generateNoteOnSD(getActivity(), noteName, noteContent);
            Log.i("textNoteSaveToFile", "Text note has been correctly saved to file");
            dbHandler.addNewNote(noteName, NOTE_TYPE);
            Log.i("textNoteSaveToDB", "Text note has been saved to database");
            notePath.setText("");
            content.setText("");

            createAndSendNotification(noteName);
        });

        return view;
    }

    @Override
    public void onPause() {
        noteActivity.noteName = notePath.getText().toString();
        noteActivity.noteContent = content.getText().toString();
        Log.i("onPause", notePath.getText().toString());
        super.onPause();
    }

    @Override
    public void onResume() {
        getNoteDataFromActivity();
        notePath.setText(noteActivity.noteName);
        content.setText(noteActivity.noteContent);
        Log.i("onResume", noteName);
        super.onResume();
    }

    private void getNoteDataFromActivity() {
        noteName = getArguments().getString("noteName");
        noteContent = getArguments().getString("noteContent");
        noteActivity = (CreateTextNoteActivity) getActivity();
    }

    private void initializeWidgets(View view) {
        notePath = view.findViewById(R.id.noteNameEditText);
        content = view.findViewById(R.id.noteContentEditText);
        addNoteButton = view.findViewById(R.id.saveTextNoteButton);
    }

    private void generateNoteOnSD(Context context, String sFileName, String sBody) {
        try {
            File root = new File(context.getFilesDir(), "Notes");
            if (!root.exists()) {
                root.mkdirs();
            }
            File gpxfile = new File(root, sFileName);
            FileWriter writer = new FileWriter(gpxfile);
            writer.append(sBody);
            writer.flush();
            writer.close();
            Toast.makeText(context, "Saved", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createAndSendNotification(String noteName) {
        NotificationManager mNotificationManager;

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(getContext(), "note_created");
        Intent ii = new Intent(getContext(), ShowNoteDetailActivity.class);
        ii.putExtra("noteName", noteName);
        PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0, ii, 0);

        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText("Note with name `" + noteName + "` has been created.");
        bigText.setBigContentTitle("Your note has been created");
        bigText.setSummaryText("Your note has been created");

        mBuilder.setContentIntent(pendingIntent);
        mBuilder.setSmallIcon(R.mipmap.ic_launcher_round);
        mBuilder.setContentTitle("Your note has been created");
        mBuilder.setContentText("Note with name `" + noteName + "` has been created.");
        mBuilder.setPriority(Notification.PRIORITY_MAX);
        mBuilder.setStyle(bigText);

        mNotificationManager =
                (NotificationManager) getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            String channelId = "note_created";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "User's note has been created",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        mNotificationManager.notify(0, mBuilder.build());
    }
}
