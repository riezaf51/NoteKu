package id.kodesumsi.noteku.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.kodesumsi.noteku.dao.NoteDao
import id.kodesumsi.noteku.model.Note

@Database(entities = arrayOf(Note::class), version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun noteDao() : NoteDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder( context.applicationContext, AppDatabase::class.java, "DB_NOTE").allowMainThreadQueries().build()
    }

}