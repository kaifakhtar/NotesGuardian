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
import nitj.cgpa.notesguardian.databinding.ActivityUpdateNotesBinding;

public class UpdateNotesActivity extends AppCompatActivity {
        ActivityUpdateNotesBinding binding;
        String priority="1";// to be same as previous notes priority
        String stitle,ssubtitle,snotes;
        NotesViewModel notesViewModel;
        int iid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityUpdateNotesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        notesViewModel =new ViewModelProvider(this).get(NotesViewModel.class);
        iid=getIntent().getIntExtra("id",0);
        stitle=getIntent().getStringExtra("title");
        ssubtitle=getIntent().getStringExtra("subtitle");
        snotes=getIntent().getStringExtra("notes");
        priority=getIntent().getStringExtra("priority");

        binding.updateTitle.setText(stitle);
        binding.updatesubTitle.setText(ssubtitle);
        binding.updateNoteTextBox.setText(snotes);

        if(priority.equals("1"))binding.greenPriority.setImageResource(R.drawable.ic_baseline_done_24);
        else if(priority.equals("2"))binding.yellowPriority.setImageResource(R.drawable.ic_baseline_done_24);
        else if(priority.equals("3"))binding.redPriority.setImageResource(R.drawable.ic_baseline_done_24);

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

        binding.doneUpdateButton.setOnClickListener(view -> {
            String title=binding.updateTitle.getText().toString();
            String subTitle=binding.updatesubTitle.getText().toString();
            String notes=binding.updateNoteTextBox.getText().toString();

            updateNotes(title,subTitle,notes);
        });
    }

    private void updateNotes(String title, String subTitle, String notes) {
        Date date=new Date();
        CharSequence sequence= DateFormat.getDateInstance().format(date);
        Notes updatedNotes=new Notes();
        updatedNotes.id=iid;
        updatedNotes.notesTitle=title;
        updatedNotes.notesSubtitle=subTitle;
        updatedNotes.notes=notes;
        updatedNotes.notesPriority=priority;//ye change krni hai
        updatedNotes.notesDate=sequence.toString();

        notesViewModel.updateNote(updatedNotes);
        Toast.makeText(this,"Note updated!",Toast.LENGTH_SHORT).show();
        finish();
    }
}