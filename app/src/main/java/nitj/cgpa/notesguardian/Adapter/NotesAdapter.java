package nitj.cgpa.notesguardian.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import nitj.cgpa.notesguardian.MainActivity;
import nitj.cgpa.notesguardian.Model.Notes;
import nitj.cgpa.notesguardian.R;

public class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.notesViewHolder> {
    MainActivity mainActivity;
    List<Notes> notes;
    public NotesAdapter(MainActivity mainActivity, List<Notes> notes) {
        this.mainActivity=mainActivity;
        this.notes=notes;
    }

    @NonNull
    @Override
    public NotesAdapter.notesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new notesViewHolder(LayoutInflater.from(mainActivity).inflate(R.layout.inivisual_display_note,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NotesAdapter.notesViewHolder holder, int position) {
        if(notes.get(position).notesPriority.equals("1")){
            holder.notesPriority.setBackgroundResource(R.drawable.green_shape);
        }
        else if(notes.get(position).notesPriority.equals("2")){
            holder.notesPriority.setBackgroundResource(R.drawable.yellow_shape);
        }
        else if(notes.get(position).notesPriority.equals("3")){
            holder.notesPriority.setBackgroundResource(R.drawable.red_shape);
        }

        holder.title.setText(notes.get(position).notesTitle);
        holder.subtitle.setText(notes.get(position).notesSubtitle);
        holder.notesDate.setText(notes.get(position).notesDate);
    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    static class notesViewHolder extends RecyclerView.ViewHolder {
        TextView title,subtitle, notesDate;
        View notesPriority;

        public notesViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.notesTitle);
            subtitle=itemView.findViewById(R.id.notesSubTitle);
            notesDate=itemView.findViewById(R.id.notesDate);
            notesPriority=itemView.findViewById(R.id.notesPriority);

        }
    }
}
