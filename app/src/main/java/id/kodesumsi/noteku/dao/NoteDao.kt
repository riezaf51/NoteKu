package id.kodesumsi.noteku.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import id.kodesumsi.noteku.model.Note

@Dao
interface NoteDao {
    @Query("SELECT * FROM note")
    fun getAll(): List<Note>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNewNote(note: Note)

    @Query("DELETE FROM note WHERE id = :id")
    fun deleteNote(id : Int)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateNote(note : Note)
}