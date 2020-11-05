package com.example.mvvm.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.mvvm.Adapter.NoteAdapter;
import com.example.mvvm.Model.NoteModel;
import com.example.mvvm.R;
import com.example.mvvm.ViewModel.NoteViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private NoteViewModel noteViewModel;

    public static final int ADD_NOTE_REQUEST = 1;
    public static final int EDIT_NOTE_REQUEST = 2;

    NoteAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        FloatingActionButton buttonAddNote = findViewById(R.id.fb_id);
        buttonAddNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, AddNoteActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);
            }
        });
         adapter= new NoteAdapter();
        recyclerView.setAdapter(adapter);
        noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNoteModel().observe(this, new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(@Nullable List<NoteModel> notes) {
                adapter.setNote(notes);
            }
        });
       new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
           @Override
           public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
               return false;
           }

           @Override
           public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
               noteViewModel.deleteNote(adapter.getNoteAtPosition(viewHolder.getAdapterPosition()));
               Toast.makeText(HomeActivity.this, "Note deleted", Toast.LENGTH_SHORT).show();

           }
       }).attachToRecyclerView(recyclerView);
       adapter.setonItemClickListener(new NoteAdapter.OnItemClickListener() {
           @Override
           public void onItemClick(NoteModel noteModel) {
                Intent intentToAddNote=new Intent(HomeActivity.this,AddNoteActivity.class);
                intentToAddNote.putExtra(AddNoteActivity.EXTRA_ID,noteModel.getNoteId());
                intentToAddNote.putExtra(AddNoteActivity.EXTRA_TITLE,noteModel.getNoteTitle());
                intentToAddNote.putExtra(AddNoteActivity.EXTRA_DESCRIPTION,noteModel.getNoteDes());
                intentToAddNote.putExtra(AddNoteActivity.EXTRA_PRIORITY,noteModel.getNotePriority());
                startActivityForResult(intentToAddNote,EDIT_NOTE_REQUEST);
           }
       });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int prio = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);
            NoteModel noteModel = new NoteModel(title, desc, prio);
            noteViewModel.insertNote(noteModel);
            Toast.makeText(this,"Note Saved",Toast.LENGTH_SHORT).show();
        }else if(requestCode==2){
            int id=data.getIntExtra(AddNoteActivity.EXTRA_ID,-1);
            if(id==-1){
                Toast.makeText(this,"Note cannot be updated",Toast.LENGTH_SHORT).show();
                return;

            }
            String title = data.getStringExtra(AddNoteActivity.EXTRA_TITLE);
            String desc = data.getStringExtra(AddNoteActivity.EXTRA_DESCRIPTION);
            int prio = data.getIntExtra(AddNoteActivity.EXTRA_PRIORITY, 1);
            NoteModel noteModel = new NoteModel(title, desc, prio);
            noteModel.setNoteId(id);
            noteViewModel.updateNote(noteModel);
            Toast.makeText(this,"Note updated",Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this,"Note not Saved",Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.main_menu_id:
                noteViewModel.deleteAllNote();
                Toast.makeText(this, "All notes deleted", Toast.LENGTH_SHORT).show();
                return true;
            default:  return super.onOptionsItemSelected(item);
        }

    }
}