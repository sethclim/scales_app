package sheridan.climense.scales_app2.ui.routine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import sheridan.climense.scales_app2.databinding.RoutineCreatorFragmentBinding
import sheridan.climense.scales_app2.model.RoutineGenerator
import sheridan.climense.scales_app2.model.RoutineInputs
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
        binding.routineViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun practice(){
        //val test_array = arrayOf("C major", "D Major", "E Major")
        //val practice_package = practice_package("Test Package", test_array)
        getInputs()
        viewModel.generateRoutine()
        val routine =  RoutineGenerator.routine
        val practice_package = practice_package("MyPractice",routine.toTypedArray())
        val action = RoutineCreatorDirections.actionRoutineToPractice(practice_package)
        findNavController().navigate(action)
    }

    private fun getInputs(){
        RoutineInputs.apply {
            maj = binding.majCb.isChecked
            min = binding.minCb.isChecked
            dim = binding.dimCb.isChecked
            aug = binding.augCb.isChecked
            maj7 = binding.maj7Cb.isChecked
            min7 = binding.min7Cb.isChecked
            dom7 = binding.dom7Cb.isChecked
            scales = binding.scalesCb.isChecked
            arps = binding.arpCb.isChecked
            solid = binding.solidchCb.isChecked
            broken = binding.brchCb.isChecked
            octaves = binding.octCb.isChecked
            contrary = binding.conMCb.isChecked
        }
    }
}