package sheridan.climense.scales_app2.ui.routine

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.domain.model.types.ScaleType
import sheridan.climense.kmmsharedmodule.domain.model.types.TechType
import sheridan.climense.kmmsharedmodule.features.creator.CreatorContract
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.RoutineCreatorFragmentBinding
import sheridan.climense.kmmsharedmodule.features.creator.CreatorViewModel
import sheridan.climense.scales_app2.models.RoutineInputs
import sheridan.climense.scales_app2.ui.dialog.RootOptionDialog
import sheridan.climense.scales_app2.ui.dialog.SavedDialog

class RoutineCreator : Fragment() {

    private lateinit var binding : RoutineCreatorFragmentBinding
    private val creatorVM: CreatorViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = RoutineCreatorFragmentBinding.inflate(inflater, container, false)
        binding.practiceBt.setOnClickListener { practice() }
        binding.setCustRootsBt.setOnClickListener { openDialog() }
        binding.saveBt.setOnClickListener { openSaveDialog() }

        binding.enableCustRootsSw.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.ToggleCustomRoots(isChecked))
        }

        binding.majCb.setOnCheckedChangeListener { _, isChecked ->
           creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Maj, isChecked))
        }

        binding.minCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Min, isChecked))
        }

        binding.minMCb2.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.MelMin, isChecked))
        }

        binding.dimCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Dim, isChecked))
        }

        binding.maj7Cb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Maj7, isChecked))
        }

        binding.min7Cb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Min7, isChecked))
        }

        binding.dom7Cb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Dom7, isChecked))
        }

        binding.augCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetScaleEvent(ScaleType.Aug, isChecked))
        }

        binding.scalesCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.Scale, isChecked))
        }

        binding.arpCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.Arp, isChecked))
        }

        binding.solidchCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.Solid, isChecked))
        }

        binding.brchCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.Broken, isChecked))
        }

        binding.octCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.Oct, isChecked))
        }

        binding.conMCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetTechEvent(TechType.CM, isChecked))
        }

        //binding.routineViewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    private fun practice(){
        //getInputs()
        val success = creatorVM.generateRoutine()
        //val routine =  routineCreaterVM.routine
        //val practicepackage = PracticePackage("MyPractice",routine, false, total=routine.size)
        if(success){
            //val action = RoutineCreatorDirections.actionRoutineToPractice(practicepackage)
            //RoutineGenerator.routine.clear()
            findNavController().navigate(R.id.action_routine_to_practice)
            binding.practiceErrorTv.text = ""
        }
        else{
            binding.practiceErrorTv.text = getString(R.string.practice_error_empty)
        }
    }

//     fun getInputs(){
//        RoutineInputs.apply {
//            scaleOptions[0].isUsed = binding.majCb.isChecked
//            scaleOptions[1].isUsed = binding.minCb.isChecked
//            scaleOptions[2].isUsed = binding.minMCb2.isChecked
//            scaleOptions[3].isUsed = binding.dimCb.isChecked
//            scaleOptions[4].isUsed = binding.maj7Cb.isChecked
//            scaleOptions[5].isUsed = binding.min7Cb.isChecked
//            scaleOptions[6].isUsed = binding.dom7Cb.isChecked
//            scaleOptions[7].isUsed = binding.augCb.isChecked
//            techOptions[0].isUsed = binding.scalesCb.isChecked
//            techOptions[1].isUsed = binding.arpCb.isChecked
//            techOptions[2].isUsed = binding.solidchCb.isChecked
//            techOptions[3].isUsed = binding.brchCb.isChecked
//            techOptions[4].isUsed = binding.octCb.isChecked
//            techOptions[5].isUsed = binding.conMCb.isChecked
//        }
//    }

    private fun openDialog(){
        val rootOptionDialog = RootOptionDialog()
        rootOptionDialog.show(childFragmentManager, "dialogTerm" )
    }

    private fun openSaveDialog() {
        //getInputs()
        val proceed = creatorVM.generateRoutine()
        if(proceed){
            val savedDialog = SavedDialog()
            savedDialog.show(childFragmentManager, "dialogTerm" )
            binding.saveErrorTv.text = ""
        }
        else{
            binding.saveErrorTv.text = getString(R.string.save_error_empty)
        }
    }
}