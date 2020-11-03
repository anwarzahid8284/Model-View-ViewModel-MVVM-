package com.example.mvvm.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.mvvm.Adapter.NoteAdapter;
import com.example.mvvm.Model.NoteModel;
import com.example.mvvm.R;
import com.example.mvvm.Repository.NoteRepository;
import com.example.mvvm.ViewModel.NoteViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNoteModel().observe(this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(@Nullable List<NoteModel> notes) {
                adapter.setNote(notes);
            }
        });
    }
}