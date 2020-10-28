package sheridan.climense.scales_app2.ui.Practice

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.climense.scales_app2.R

class PracticePage : Fragment() {

    companion object {
        fun newInstance() = PracticePage()
    }

    private lateinit var viewModel: PracticePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.practice_page_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PracticePageViewModel::class.java)
        // TODO: Use the ViewModel
    }

}