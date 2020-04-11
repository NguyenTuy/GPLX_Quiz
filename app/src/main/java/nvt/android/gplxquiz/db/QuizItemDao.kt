package nvt.android.gplxquiz.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizItemDao {
    @Query("SELECT * FROM quiz_item")
    fun getAll(): List<QuizItemEntity>

    @Query("SELECT * FROM quiz_item WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): QuizItemEntity

    @Insert
    fun insertAll(vararg quiz: QuizItemEntity)

    @Delete
    fun delete(quiz: QuizItemEntity)
}