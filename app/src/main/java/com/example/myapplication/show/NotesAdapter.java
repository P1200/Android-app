package com.example.myapplication.show;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.NotesModal;
import com.example.myapplication.R;
import com.example.myapplication.show.details.ShowNoteDetailActivity;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.ViewHolder> {

    private final ArrayList<NotesModal> noteModalArrayList;
    private final Context context;

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

        private final TextView noteName, noteType, creationDate, modificationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            noteName = itemView.findViewById(R.id.noteName);
            noteType = itemView.findViewById(R.id.noteType);
            creationDate = itemView.findViewById(R.id.creationDate);
            modificationDate = itemView.findViewById(R.id.modificationDate);

            itemView.setOnClickListener(view -> {
                int position = getAdapterPosition();

                Intent i = new Intent(context, ShowNoteDetailActivity.class);
                i.putExtra("noteName", noteModalArrayList.get(position).getNotePath());
                context.startActivity(i);
            });
        }
    }
}

