package nitj.cgpa.notesguardian;

import static androidx.recyclerview.widget.RecyclerView.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.google.android.material.chip.Chip;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import nitj.cgpa.notesguardian.Activity.InsertNotesActivity;
import nitj.cgpa.notesguardian.Adapter.NotesAdapter;
import nitj.cgpa.notesguardian.Model.Notes;
import nitj.cgpa.notesguardian.ViewModel.NotesViewModel;

public class MainActivity extends AppCompatActivity {
    ExtendedFloatingActionButton notesDoneButton;
    NotesViewModel notesViewModel;
    RecyclerView notesRecyclerView;
    NotesAdapter notesAdapter;
    Chip unfilterChip, HighToLow, LowToHigh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notesRecyclerView = findViewById(R.id.notesRecyclerView);
        notesDoneButton = findViewById(R.id.addNoteButton);
        notesViewModel = new ViewModelProvider(this).get(NotesViewModel.class);
        notesDoneButton.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, InsertNotesActivity.class));
        });

        unfilterChip = findViewById(R.id.unfilter_chip);
        HighToLow = findViewById(R.id.high_to_low_chip);
        LowToHigh = findViewById(R.id.low_to_high_chip);


        notesRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    notesDoneButton.shrink();
                } else
                    notesDoneButton.extend();
            }
        });


        unfilterChip.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                loadData(0);
            }
        });

        HighToLow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                loadData(1);
            }
        });
        LowToHigh.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                loadData(2);
            }
        });
        notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
            @Override
            public void onChanged(List<Notes> notes) {
                setAdapter(notes);
            }
        });

    }


    void loadData(int i) {
        if (i == 0) {
            notesViewModel.getAllNotes.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                }
            });

        } else if (i == 1) {
            notesViewModel.highTolow.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                }
            });
        } else if (i == 2) {
            notesViewModel.lowTohigh.observe(this, new Observer<List<Notes>>() {
                @Override
                public void onChanged(List<Notes> notes) {
                    setAdapter(notes);
                }
            });
        }

    }


    void setAdapter(List<Notes> notes) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, VERTICAL);
        notesRecyclerView.setLayoutManager(staggeredGridLayoutManager);
        notesAdapter = new NotesAdapter(MainActivity.this, notes);
        notesRecyclerView.setAdapter(notesAdapter);
        notesRecyclerView.setHasFixedSize(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_act_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.about_us) {

        }
        return true;
    }
}