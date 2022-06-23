package nitj.cgpa.notesguardian.Dao;

import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import nitj.cgpa.notesguardian.Model.Notes;

@androidx.room.Dao
public interface NotesDao {

    @Query("SELECT * FROM Notes_Database")
    List<Notes> getallNotes();

    @Insert
    void insertNotes(Notes... notes);

    @Query("DELETE FROM Notes_Database WHERE id =:id")
    void deleteNotes(int id);

    @Update
    void insertNotes(Notes notes);
}
