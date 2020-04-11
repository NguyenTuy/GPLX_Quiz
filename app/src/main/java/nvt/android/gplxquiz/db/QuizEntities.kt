package nvt.android.gplxquiz.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quiz_item")
data class QuizItemEntity(@PrimaryKey val id: String, val quizListId: String,
                          val title: String, val answer: String, val result: Int, val tip: String, val multiAnswer: Boolean)

@Entity(tableName = "quiz_list")
data class QuizListEntity(@PrimaryKey val id: String, val title: String,
                    val description: String, val score: Int, val count: Int)