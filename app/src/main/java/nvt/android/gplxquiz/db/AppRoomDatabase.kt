package nvt.android.gplxquiz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [QuizListEntity::class, QuizItemEntity::class], version = 1)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun quizListDao(): QuizListDao
    abstract fun quizItemDao(): QuizItemDao

    companion object {
        const val DB_NAME = "quiz_database"
        private var instance: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(context, AppRoomDatabase::class.java, DB_NAME).build()
            }

            return instance as AppRoomDatabase
        }
    }
}