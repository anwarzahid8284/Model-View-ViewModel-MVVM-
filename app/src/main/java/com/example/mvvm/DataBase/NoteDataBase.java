package com.example.mvvm.DataBase;

import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mvvm.DAO.NoteDAO;
import com.example.mvvm.Model.NoteModel;

import io.reactivex.annotations.NonNull;

@Database(entities = {NoteModel.class},version = 1)
public abstract class NoteDataBase extends RoomDatabase {
    public static NoteDataBase instance;
    public abstract NoteDAO noteModel();
    public static synchronized NoteDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),NoteDataBase.class,"note_tb")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();

        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;
        private PopulateDbAsyncTask(NoteDataBase db) {
            noteDao = db.noteModel();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new NoteModel("Title 1", "Description 1", 1));
            noteDao.insert(new NoteModel("Title 2", "Description 2", 2));
            noteDao.insert(new NoteModel("Title 3", "Description 3", 4));
            return null;
        }
    }
}
