package com.example.mvvm.DataBase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvm.DAO.NoteDAO;
import com.example.mvvm.Model.NoteModel;

@Database(entities = {NoteModel.class},version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    public static NoteDataBase instance;
    public abstract NoteDAO noteModel();
    public static synchronized NoteDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_tb")
                    .fallbackToDestructiveMigration().build();

        }
        return instance;
    }
}
