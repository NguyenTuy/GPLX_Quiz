package nvt.android.gplxquiz.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface QuizListDao {
    @Query("SELECT * FROM quiz_list")
    fun getAll(): List<QuizListEntity>

    @Query("SELECT * FROM quiz_list WHERE id LIKE :id LIMIT 1")
    fun findById(id: String): QuizListEntity

    @Insert
    fun insertAll(vararg quiz: QuizListEntity)

    @Delete
    fun delete(quiz: QuizListEntity)
}