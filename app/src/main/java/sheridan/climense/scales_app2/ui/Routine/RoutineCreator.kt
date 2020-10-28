package sheridan.climense.scales_app2.ui.Routine

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelStore
import androidx.navigation.fragment.findNavController
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.RoutineCreatorFragmentBinding
import sheridan.climense.scales_app2.model.practice_package

class RoutineCreator : Fragment() {

    private val viewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding : RoutineCreatorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = RoutineCreatorFragmentBinding.inflate(inflater, container, false)
        binding.practiceBt.setOnClickListener { practice() }

        return binding.root
    }

    private fun practice(){
        val test_array = arrayOf("C major", "D Major", "E Major")
        val practice_package = practice_package("Test Package", test_array)
        val action = RoutineCreatorDirections.actionRoutineToPractice(practice_package)
        findNavController().navigate(action)
    }


}