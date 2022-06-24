package nitj.cgpa.notesguardian;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nitj.cgpa.notesguardian.Activity.InsertNotesActivity;
import nitj.cgpa.notesguardian.Adapter.NotesAdapter;
import nitj.cgpa.notesguardian.ViewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton notesDoneButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecyclerView=findViewById(R.id.notesRecyclerView);
        notesDoneButton=findViewById(R.id.addNoteButton);
        notesViewModel =new ViewModelProvider(this).get(NotesViewModel.class);
        notesDoneButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });
        notesViewModel.getAllNotes.observe(this,notes -> {
            notesRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
            notesAdapter=new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(notesAdapter);
        });
    }
}