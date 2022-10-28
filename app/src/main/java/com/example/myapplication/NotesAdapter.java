package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private ArrayList<NotesModal> noteModalArrayList;
    private Context context;

    public NotesAdapter(ArrayList<NotesModal> noteModalArrayList, Context context) {
        this.noteModalArrayList = noteModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NotesModal modal = noteModalArrayList.get(position);
        holder.noteName.setText(modal.getNotePath());
        holder.noteType.setText(modal.getNoteType());
        holder.creationDate.setText(modal.getCreationDate());
        holder.modificationDate.setText(modal.getModificationDate());
    }

    @Override
    public int getItemCount() {
        return noteModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView noteName, noteType, creationDate, modificationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.noteName);
            noteType = itemView.findViewById(R.id.noteType);
            creationDate = itemView.findViewById(R.id.creationDate);
            modificationDate = itemView.findViewById(R.id.modificationDate);
        }
    }
}

