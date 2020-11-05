package com.example.mvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "note_tb")
public class NoteModel implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int noteId;

    @ColumnInfo(name = "noteTitle")
    private String noteTitle;

    @ColumnInfo(name = "noteDes")
    private String noteDes;

    @ColumnInfo(name = "notePriority")
    private int notePriority;

    public NoteModel(String noteTitle, String noteDes, int notePriority) {
        this.noteTitle = noteTitle;
        this.noteDes = noteDes;
        this.notePriority = notePriority;

    }


    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public int getNoteId() {
        return noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDes() {
        return noteDes;
    }

    public int getNotePriority() {
        return notePriority;
    }


}
