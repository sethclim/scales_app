package sheridan.climense.scales_app2.ui.practice

import android.graphics.Color
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.navArgs
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import sheridan.climense.scales_app2.model.PracticeCycler
import sheridan.climense.scales_app2.model.RoutineGenerator

class PracticePage : Fragment() {

    private val safeArgs: PracticePageArgs by navArgs()
    private val viewModel: PracticePageViewModel by viewModels()
    private lateinit var binding : PracticePageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = PracticePageFragmentBinding.inflate(inflater, container, false)
        binding.practiceViewModel = viewModel
        binding.lifecycleOwner = this

        val practiceArray = safeArgs.PracticePackage.practice_array
        val totalLength = safeArgs.PracticePackage.total

        PracticeCycler.practiceArray = practiceArray.toMutableList()

        viewModel._msg.value = "Click Next to Begin"
        binding.nextBt.setOnClickListener { next(totalLength) }
        binding.favBt.setOnClickListener { viewModel.handleFav() }

        viewModel.isFav.observe(viewLifecycleOwner, {
            if(it == true){binding.favBt.setColorFilter(Color.argb(96,245,243,56))}else{binding.favBt.setColorFilter(Color.WHITE)}
        })

        viewModel.isEnd.observe(viewLifecycleOwner, {
            if(it == false){binding.favBt.visibility = View.GONE}else{binding.favBt.visibility = View.VISIBLE}
        })

        return binding.root
    }

    private fun next(size : Int){
        viewModel.next()
        viewModel.getProgress(size)
        if(viewModel.done){binding.nextBt.isVisible = false}

    }

    override fun onPause() {
        super.onPause()
        viewModel.saveRecord()

        if(safeArgs.PracticePackage.savedPractice){
            viewModel.updatedSavedProgress(
                    safeArgs.PracticePackage.key,
                    safeArgs.PracticePackage.routine_name,
                    safeArgs.PracticePackage.practice_array,
                    PracticeCycler.practiceArray.toTypedArray(),
                    safeArgs.PracticePackage.total,
                    safeArgs.PracticePackage.date
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadRecord()
    }
}