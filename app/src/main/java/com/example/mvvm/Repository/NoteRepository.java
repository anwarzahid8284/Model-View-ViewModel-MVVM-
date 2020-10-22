package com.example.mvvm.Repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mvvm.DAO.NoteDAO;
import com.example.mvvm.DataBase.NoteDataBase;
import com.example.mvvm.Model.NoteModel;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;
    private LiveData<List<NoteModel>> getAllNotes;

    public NoteRepository(Application application) {
        NoteDataBase noteDataBase = NoteDataBase.getInstance(application);
        noteDAO = noteDataBase.noteModel();
        getAllNotes = noteDAO.getAllNotes();
    }

    private void insert(NoteModel noteModel) {
        InsertNoteAsyncTask insertNoteAsyncTask = new InsertNoteAsyncTask(noteDAO);
        insertNoteAsyncTask.execute(noteModel);
    }

    private void update(NoteModel noteModel) {
        UpdateNoteAsyncTask updateNoteAsyncTask = new UpdateNoteAsyncTask(noteDAO);
        updateNoteAsyncTask.execute(noteModel);
    }

    // methods
    private void delete(NoteModel noteModel) {
        DeletNoteAsyncTask deletNoteAsyncTask = new DeletNoteAsyncTask(noteDAO);
        deletNoteAsyncTask.execute(noteModel);
    }

    private void deleteAll(NoteModel noteModel) {
        DeleteAllNotesAsyncTask deleteAllNotesAsyncTask = new DeleteAllNotesAsyncTask(noteDAO);
        deleteAllNotesAsyncTask.execute();
    }

    private LiveData<List<NoteModel>> getAllNotes(NoteModel noteModel) {
        return getAllNotes;
    }


    // Live Data for insert
    private static class InsertNoteAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDAO noteDao;

        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    // Live Data for update
    private static class UpdateNoteAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDAO noteDao;

        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    // Live Data for delete
    private static class DeletNoteAsyncTask extends AsyncTask<NoteModel, Void, Void> {
        private NoteDAO noteDao;

        private DeletNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    // Live Data for delete all
    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private DeleteAllNotesAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNote();
            return null;
        }
    }


}
