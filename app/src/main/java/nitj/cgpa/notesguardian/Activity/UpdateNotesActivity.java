package nitj.cgpa.notesguardian.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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
    TextView yes, no;


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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.delete_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.ic_delete) {
            BottomSheetDialog sheetDialog = new BottomSheetDialog(UpdateNotesActivity.this,R.style.BottonSheetStyle);
            View v = LayoutInflater.from(UpdateNotesActivity.this).inflate(R.layout.delete_bottom_sheet,findViewById(R.id.bottom_sheet_linear_layout));
            sheetDialog.setContentView(v);
            yes = v.findViewById(R.id.yes);
            no = v.findViewById(R.id.no);


            yes.setOnClickListener(view -> {
                notesViewModel.deleteNote(iid);
                Toast.makeText(this,"Deleted",Toast.LENGTH_SHORT).show();
                finish();
            });
            no.setOnClickListener(view -> {
                sheetDialog.dismiss();
            });

            sheetDialog.show();
        }
        return true;
    }
}