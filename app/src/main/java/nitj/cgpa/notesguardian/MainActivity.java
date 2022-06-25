package nitj.cgpa.notesguardian;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import nitj.cgpa.notesguardian.Activity.InsertNotesActivity;
import nitj.cgpa.notesguardian.Adapter.NotesAdapter;
import nitj.cgpa.notesguardian.ViewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity {
    ExtendedFloatingActionButton notesDoneButton;
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



        //notesDoneButton.extend();
        notesViewModel.getAllNotes.observe(this,notes -> {
            StaggeredGridLayoutManager staggeredGridLayoutManager=new StaggeredGridLayoutManager(2,VERTICAL);
            notesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
            notesAdapter=new NotesAdapter(MainActivity.this,notes);
            notesRecyclerView.setAdapter(notesAdapter);
            notesRecyclerView.setHasFixedSize(true);
        });

        notesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener(){
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if(dy>0){
                    notesDoneButton.shrink();
                }else
                    notesDoneButton.extend();
            }
        });
    }
}