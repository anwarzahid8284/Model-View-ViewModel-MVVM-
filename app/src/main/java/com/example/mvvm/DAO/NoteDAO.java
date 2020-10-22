package com.example.mvvm.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mvvm.Model.NoteModel;

import java.util.List;

@Dao
public interface NoteDAO {
    @Insert
    void insert(NoteModel noteModel);

    @Update
    void update(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);

    @Query("Delete from note_tb")
    void deleteAllNote();

    @Query("select * from note_tb  ORDER BY notePriority Desc")
    LiveData<List<NoteModel>> getAllNotes();

}
