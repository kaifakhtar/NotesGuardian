package nitj.cgpa.notesguardian.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

import nitj.cgpa.notesguardian.Dao.NotesDao;
import nitj.cgpa.notesguardian.Database.NotesDatabase;
import nitj.cgpa.notesguardian.Model.Notes;

public class NotesRepository {

    public NotesDao notesDao;
    public LiveData<List<Notes>> getallNotes;



    public NotesRepository(Application application){
        NotesDatabase database=NotesDatabase.getDatabaseInstance(application);
        notesDao=database.notesDao();
        getallNotes=notesDao.getallNotes();
    }
    public void insertNotes(Notes notes){
        notesDao.insertNotes(notes);
    }
    public void deleteNotes(int id){
        notesDao.deleteNotes(id);
    }
    public void updateNotes(Notes notes){
        notesDao.updateNotes(notes);
    }
}
