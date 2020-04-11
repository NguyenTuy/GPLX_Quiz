package nvt.android.gplxquiz.dto

data class QuizItem(val id: String, val title: String, val answer: List<String>, val result: Int, val tip: String, val multiAnswer: Boolean)

data class QuizList(val id: String, val title: String,
                    val description: String, val score: Int, val count: Int, val items: List<QuizItem>)
