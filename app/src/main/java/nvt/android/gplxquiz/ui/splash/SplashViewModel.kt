package nvt.android.gplxquiz.ui.splash

import android.app.Application
import android.os.Handler
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import nvt.android.gplxquiz.repository.QuizRepository

class SplashViewModel(application: Application) : AndroidViewModel(application) {
    val loadDataDoneLiveData = MutableLiveData<Boolean>()
    private val quizRepository = QuizRepository(application)

    fun loadingData() {
        quizRepository.extractData()
        Handler().postDelayed(Runnable { loadDataDoneLiveData.value = true }, 3000)
    }
}