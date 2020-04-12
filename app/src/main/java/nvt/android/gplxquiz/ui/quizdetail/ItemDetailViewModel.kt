package nvt.android.gplxquiz.ui.quizdetail

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import nvt.android.gplxquiz.db.QuizListEntity
import nvt.android.gplxquiz.repository.QuizRepository

class ItemDetailViewModel(application: Application) : AndroidViewModel(application) {
    private val quizRepository = QuizRepository(application)
    var quizListEntity = ObservableField<QuizListEntity>()
    var setDataDone: LiveData<QuizListEntity>? = null

    fun setItem(id: String) {
        setDataDone = quizRepository.getQuizListById(id)
    }
}