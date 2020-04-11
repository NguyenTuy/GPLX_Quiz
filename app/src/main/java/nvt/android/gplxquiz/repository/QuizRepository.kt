package nvt.android.gplxquiz.repository

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import nvt.android.gplxquiz.db.*
import kotlin.coroutines.CoroutineContext

class QuizRepository(private val application: Application) : CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    private var quizItemDao: QuizItemDao
    private var quizListDao: QuizListDao
    private val gs = Gson()

    init {
        val db = AppRoomDatabase.getInstance(application)
        quizItemDao = db.quizItemDao()
        quizListDao = db.quizListDao()
    }

    fun extractData() {
        if (doesDatabaseExist())
            return

        launch (Dispatchers.Default) {
            val quizList = XmlReader(application).readXml()
            quizList.forEach { item ->
                quizListDao.insertAll(
                    QuizListEntity(
                        item.id,
                        item.title,
                        item.description,
                        item.score,
                        item.count
                    )
                )
                Log.i("TuyNV", "Inset quiz list ${item.id}")
                item.items.forEach { quiz ->
                    Log.i("TuyNV", "Inset quiz ${quiz.id}")
                    quizItemDao.insertAll(
                        QuizItemEntity(
                            quiz.id,
                            item.id,
                            quiz.title,
                            gs.toJson(quiz.answer, List::class.java),
                            quiz.result,
                            quiz.tip,
                            quiz.multiAnswer
                        )
                    )
                }
            }
        }
    }

    private fun doesDatabaseExist(): Boolean {
        val file = application.getDatabasePath(AppRoomDatabase.DB_NAME)
        return file.exists()
    }
}