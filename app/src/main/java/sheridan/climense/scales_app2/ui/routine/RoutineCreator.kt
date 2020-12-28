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
import sheridan.climense.scales_app2.model.PracticePackage
import sheridan.climense.scales_app2.ui.dialog.RootOptionDialog
import sheridan.climense.scales_app2.ui.dialog.SavedDialog

class RoutineCreator : Fragment() {

    private val viewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding : RoutineCreatorFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = RoutineCreatorFragmentBinding.inflate(inflater, container, false)
        binding.practiceBt.setOnClickListener { practice() }
        binding.setCustRootsBt.setOnClickListener { openDialog() }
        binding.saveBt.setOnClickListener { openSaveDialog() }
        binding.enableCustRootsSw.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                RoutineGenerator.roots = RoutineInputs.CustRootOptions
            } else {
                RoutineGenerator.roots = RoutineInputs.RootOptions
            }
        }
        binding.routineViewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    private fun practice(){
        getInputs()
        viewModel.generateRoutine()
        val routine =  viewModel.routine
        val practice_package = PracticePackage("MyPractice",routine, false, total=routine.size)
        if(routine.size != 0){
            val action = RoutineCreatorDirections.actionRoutineToPractice(practice_package)
            RoutineGenerator.routine.clear()
            findNavController().navigate(action)

        }
        //add warning message here
    }

     fun getInputs(){
        RoutineInputs.apply {
            scaleOptions[0].isUsed = binding.majCb.isChecked
            scaleOptions[1].isUsed = binding.minCb.isChecked
            scaleOptions[2].isUsed = binding.dimCb.isChecked
            scaleOptions[3].isUsed = binding.augCb.isChecked
            scaleOptions[4].isUsed = binding.maj7Cb.isChecked
            scaleOptions[5].isUsed = binding.min7Cb.isChecked
            scaleOptions[6].isUsed = binding.dom7Cb.isChecked
            techOptions[0].isUsed = binding.scalesCb.isChecked
            techOptions[1].isUsed = binding.arpCb.isChecked
            techOptions[2].isUsed = binding.solidchCb.isChecked
            techOptions[3].isUsed = binding.brchCb.isChecked
            techOptions[4].isUsed = binding.octCb.isChecked
            techOptions[5].isUsed = binding.conMCb.isChecked
        }
    }

    private fun openDialog(){
        val rootOptionDialog = RootOptionDialog()
        rootOptionDialog.show(childFragmentManager, "dialogTerm" )
    }

    private fun openSaveDialog() {
        getInputs()
        val SavedDialog = SavedDialog()
        SavedDialog.show(childFragmentManager, "dialogTerm" )
    }
}