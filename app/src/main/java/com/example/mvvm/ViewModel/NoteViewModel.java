package com.example.mvvm.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvm.Model.NoteModel;
import com.example.mvvm.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    public NoteRepository repository;
    public LiveData<List<NoteModel>> noteModel;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository=new NoteRepository(application);
        noteModel=repository.getAllNotes();
    }
    public void insertNote(NoteModel noteModel){
        repository.insert(noteModel);
    }
    public void deleteNote(NoteModel noteModel){
        repository.delete(noteModel);
    }
    public void updateNote(NoteModel noteModel){
        repository.update(noteModel);
    }
    public void deleteAllNote(){
        repository.deleteAll();
    }

    public LiveData<List<NoteModel>> getNoteModel() {
        return noteModel;
    }
}
