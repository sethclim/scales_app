package sheridan.climense.scales_app2.ui.Routine

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import sheridan.climense.scales_app2.R

class RoutineCreator : Fragment() {

    companion object {
        fun newInstance() = RoutineCreator()
    }

    private lateinit var viewModel: RoutineCreatorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.routine_creator_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(RoutineCreatorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}