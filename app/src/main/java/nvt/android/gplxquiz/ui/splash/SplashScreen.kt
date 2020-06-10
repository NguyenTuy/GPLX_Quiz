package nvt.android.gplxquiz.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import nvt.android.gplxquiz.R
import nvt.android.gplxquiz.ui.quizlist.QuizListActivity

class SplashScreen : AppCompatActivity() {
    private lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splash)

        splashViewModel = ViewModelProviders.of(this).get(SplashViewModel::class.java)
        splashViewModel.loadDataDoneLiveData.observe(this, Observer {
            startActivity(Intent(this, QuizListActivity::class.java))
            finish()
        })

        splashViewModel.loadingData()
    }
}