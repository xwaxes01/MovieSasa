package bajeti.susac.co.ke.room.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import bajeti.susac.co.ke.room.dao.MovieDao
import com.murerwa.moviesasa.models.Movie


@Database(entities = [Movie::class], version = 2010, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract val movieDao: MovieDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance = INSTANCE

                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "movie_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}