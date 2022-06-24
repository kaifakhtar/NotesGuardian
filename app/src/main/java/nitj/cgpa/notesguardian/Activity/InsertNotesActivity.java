package nitj.cgpa.notesguardian.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

import nitj.cgpa.notesguardian.Model.Notes;
import nitj.cgpa.notesguardian.R;
import nitj.cgpa.notesguardian.ViewModel.NotesViewModel;
import nitj.cgpa.notesguardian.databinding.ActivityInsertNotesBinding;

public class InsertNotesActivity extends AppCompatActivity {
    ActivityInsertNotesBinding binding;
    String title,subTitle,notes;
    NotesViewModel notesViewModel;String priority="1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityInsertNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.greenPriority.setOnClickListener(view -> {
            priority="1";
            binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.redPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
        });
        binding.redPriority.setOnClickListener(view -> {
            priority="3";
            binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.yellowPriority.setImageResource(0);
        });

        binding.yellowPriority.setOnClickListener(view -> {
            priority="2";
            binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
            binding.greenPriority.setImageResource(0);
            binding.redPriority.setImageResource(0);

        });

        notesViewModel =new ViewModelProvider(this).get(NotesViewModel.class);
        binding.doneInsertButton.setOnClickListener(view -> {
            title=binding.notesTitle.getText().toString();
            subTitle=binding.notesSubTitle.getText().toString();
            notes=binding.notesTextBox.getText().toString();

            createNotes(title,subTitle,notes);  // call method createNotes
        });
    }

    private void createNotes(String title, String subTitle, String notes) {
        Date date=new Date();
        CharSequence sequence= DateFormat.getDateInstance().format(date);

        Notes notes1=new Notes();
        notes1.notesTitle=title;
        notes1.notesSubtitle=subTitle;
        notes1.notes=notes;
        notes1.notesPriority=priority;
        notes1.notesDate=sequence.toString();
        notesViewModel.insertNote(notes1);
        Toast.makeText(this,"Note created succesfully !",Toast.LENGTH_SHORT).show();
        finish();
    }
}