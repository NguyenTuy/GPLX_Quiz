package nvt.android.gplxquiz.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "quiz_item")
data class QuizItemEntity(@PrimaryKey val id: String, val quizListId: String,
                          val title: String, val answer: String, val result: Int, val tip: String, val multiAnswer: Boolean) : Serializable

@Entity(tableName = "quiz_list")
data class QuizListEntity(@PrimaryKey val id: String, val title: String,
                    val description: String, val score: Int, val count: Int)