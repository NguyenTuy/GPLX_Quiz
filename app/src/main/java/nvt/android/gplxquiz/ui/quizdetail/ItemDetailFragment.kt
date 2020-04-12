package nvt.android.gplxquiz.ui.quizdetail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import nvt.android.gplxquiz.dummy.DummyContent
import kotlinx.android.synthetic.main.activity_item_detail.*
import kotlinx.android.synthetic.main.item_detail.view.*
import nvt.android.gplxquiz.R
import nvt.android.gplxquiz.databinding.ItemDetailBinding
import nvt.android.gplxquiz.db.QuizListEntity

class ItemDetailFragment : Fragment() {
    private var dataBinding: ItemDetailBinding? = null
    private var viewModel: ItemDetailViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ItemDetailViewModel::class.java)

        arguments?.let {
            if (it.containsKey(ARG_ITEM_ID)) {
                it.getString(ARG_ITEM_ID)?.let { id -> viewModel?.setItem(id) }
            }
        }

        viewModel?.setDataDone?.observe(this, Observer { activity?.toolbar_layout?.title = it.title
            viewModel?.quizListEntity?.set(it)
        })
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.item_detail, container, false)
        dataBinding?.viewModel = viewModel
        return dataBinding?.root
    }

    companion object {
        const val ARG_ITEM_ID = "item_id"
    }
}
