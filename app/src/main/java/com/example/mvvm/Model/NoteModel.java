package com.example.mvvm.Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_tb")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    public int noteId;

    @ColumnInfo(name = "noteTitle")
    private String noteTitle;

    @ColumnInfo(name = "noteDes")
    private String noteDes;

    @ColumnInfo(name = "notePriority")
    private String notePriority;

    public NoteModel(String noteTitle, String noteDes, String notePriority) {
        this.noteTitle = noteTitle;
        this.noteDes = noteDes;
        this.notePriority = notePriority;
    }

    public void setNoteId(int noteId) {
        this.noteId = noteId;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public String getNoteDes() {
        return noteDes;
    }

    public String getNotePriority() {
        return notePriority;
    }
}
