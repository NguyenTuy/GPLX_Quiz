package nvt.android.gplxquiz.ui.quizlist

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import nvt.android.gplxquiz.db.QuizListEntity
import nvt.android.gplxquiz.repository.QuizRepository

class QuizListViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository = QuizRepository(application)
    val getDatLiveData: LiveData<List<QuizListEntity>>

    init {
        getDatLiveData = quizRepository.getAllQuizList()
    }
}