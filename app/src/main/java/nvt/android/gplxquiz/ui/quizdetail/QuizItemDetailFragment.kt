package nvt.android.gplxquiz.ui.quizdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import nvt.android.gplxquiz.R
import nvt.android.gplxquiz.databinding.FragmentQuizItemDetailBinding
import nvt.android.gplxquiz.db.QuizItemEntity

class QuizItemDetailFragment: Fragment() {
    companion object {
        private const val ARG_QUIZ_ITEM = "ARG_QUIZ_ITEM"
        fun getInstance(quizItem: QuizItemEntity): QuizItemDetailFragment = QuizItemDetailFragment().apply {
            arguments = Bundle().apply { putSerializable(ARG_QUIZ_ITEM, quizItem) }
        }
    }
    var dataBinding: FragmentQuizItemDetailBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_item_detail, container, false)
        if (arguments?.containsKey(ARG_QUIZ_ITEM) == true) {
            dataBinding?.quizItem = arguments?.getSerializable(ARG_QUIZ_ITEM) as QuizItemEntity
        }
        return dataBinding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}