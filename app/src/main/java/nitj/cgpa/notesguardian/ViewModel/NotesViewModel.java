package nitj.cgpa.notesguardian.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import nitj.cgpa.notesguardian.Model.Notes;
import nitj.cgpa.notesguardian.Repository.NotesRepository;

public class NotesViewModel  extends AndroidViewModel {
    public NotesRepository repository;
    public LiveData<List<Notes>> getAllNotes;
    public LiveData<List<Notes>> highTolow;
    public LiveData<List<Notes>> lowTohigh;

    public NotesViewModel(Application application) {
        super(application);
        repository = new NotesRepository(application);
        getAllNotes=repository.getallNotes;
        highTolow=repository.highTolow;
        lowTohigh=repository.lowTohigh;
    }
    public void insertNote(Notes notes){
        repository.insertNotes(notes);
    }
    public void deleteNote(int id){
        repository.deleteNotes(id);
    }
    public void updateNote(Notes notes){
        repository.updateNotes(notes);
    }
}
