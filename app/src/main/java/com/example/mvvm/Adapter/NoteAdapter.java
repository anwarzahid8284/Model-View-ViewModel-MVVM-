package com.example.mvvm.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mvvm.Model.NoteModel;
import com.example.mvvm.R;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {
    List<NoteModel> noteModelList = new ArrayList<>();
    public OnItemClickListener listener;

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item, parent, false);
        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {
        NoteModel currentNote = noteModelList.get(position);
        holder.titleTextView.setText(currentNote.getNoteTitle());
        holder.descTextView.setText(currentNote.getNoteDes());
        holder.priorityTextView.setText(String.valueOf(currentNote.getNotePriority()));


    }

    @Override
    public int getItemCount() {
        return noteModelList.size();
    }

    public void setNote(List<NoteModel> noteModelList) {
        this.noteModelList = noteModelList;
        notifyDataSetChanged();
    }

    public NoteModel getNoteAtPosition(int position) {
        return noteModelList.get(position);
    }

    public class NoteHolder extends RecyclerView.ViewHolder {
        private TextView titleTextView, descTextView, priorityTextView, noteText;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);
            titleTextView = itemView.findViewById(R.id.text_view_title);
            descTextView = itemView.findViewById(R.id.text_view_description);
            priorityTextView = itemView.findViewById(R.id.priority_text_id);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(noteModelList.get(position));

                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(NoteModel noteModel);
    }

    public void setonItemClickListener(OnItemClickListener listener) {
        this.listener = listener;

    }
}
