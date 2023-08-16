package id.kodesumsi.noteku.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.kodesumsi.noteku.database.AppDatabase
import id.kodesumsi.noteku.dao.NoteDao
import id.kodesumsi.noteku.model.Note
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class NoteRepository(application: Application) {

    private val noteDao: NoteDao = AppDatabase.getDatabase(application).noteDao()
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    fun getAll(): List<Note> {
        return noteDao.getAll()
    }

    fun insertNewNote(note: Note) {
        executorService.execute{ noteDao.insertNewNote(note) }
    }

    fun deleteNote(id : Int) {
        executorService.execute{ noteDao.deleteNote(id) }
    }

    fun updateNote(note: Note) {
        executorService.execute{ noteDao.updateNote(note) }
    }

}