package sheridan.climense.scales_app2.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.domain.model.types.RootType
import sheridan.climense.kmmsharedmodule.features.creator.CreatorContract
import sheridan.climense.kmmsharedmodule.features.creator.CreatorViewModel
import sheridan.climense.scales_app2.databinding.DialogRootoptionsBinding
import sheridan.climense.scales_app2.models.RoutineInputs
import sheridan.climense.scales_app2.ui.routine.RoutineCreatorViewModel

class RootOptionDialog : DialogFragment() {

    private val routineCreatorViewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding: DialogRootoptionsBinding

    private val creatorVM: CreatorViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogRootoptionsBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.aSharpCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.A, isChecked))
        }

        binding.bCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.B, isChecked))
        }

        binding.cCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.C, isChecked))
        }

        binding.cSharpCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.Cs, isChecked))
        }

        binding.dCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.D, isChecked))
        }

        binding.dSharpCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.Ds, isChecked))
        }

        binding.eCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.E, isChecked))
        }

        binding.fCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.F, isChecked))
        }

        binding.fSharpCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.Fs, isChecked))
        }

        binding.gCb.setOnCheckedChangeListener { _, isChecked ->
            creatorVM.setEvent(CreatorContract.Event.SetRootsEvent(RootType.G, isChecked))
        }


//        RoutineInputs.apply {
//            binding.cCb.isChecked = CustRootOptions[0].isUsed
//            binding.cSharpCb.isChecked = CustRootOptions[1].isUsed
//            binding.dCb.isChecked = CustRootOptions[2].isUsed
//            binding.dSharpCb.isChecked = CustRootOptions[3].isUsed
//            binding.eCb.isChecked = CustRootOptions[4].isUsed
//            binding.fCb.isChecked = CustRootOptions[5].isUsed
//            binding.fSharpCb.isChecked = CustRootOptions[6].isUsed
//            binding.gCb.isChecked = CustRootOptions[7].isUsed
//            binding.gSharpCb.isChecked = CustRootOptions[8].isUsed
//            binding.aCb.isChecked = CustRootOptions[9].isUsed
//            binding.aSharpCb.isChecked = CustRootOptions[10].isUsed
//            binding.bCb.isChecked = CustRootOptions[11].isUsed
//        }

        binding.okBt.setOnClickListener {

//            RoutineInputs.apply {
//                CustRootOptions[0].isUsed = binding.cCb.isChecked
//                CustRootOptions[1].isUsed = binding.cSharpCb.isChecked
//                CustRootOptions[2].isUsed = binding.dCb.isChecked
//                CustRootOptions[3].isUsed = binding.dSharpCb.isChecked
//                CustRootOptions[4].isUsed = binding.eCb.isChecked
//                CustRootOptions[5].isUsed = binding.fCb.isChecked
//                CustRootOptions[6].isUsed = binding.fSharpCb.isChecked
//                CustRootOptions[7].isUsed = binding.gCb.isChecked
//                CustRootOptions[8].isUsed = binding.gSharpCb.isChecked
//                CustRootOptions[9].isUsed = binding.aCb.isChecked
//                CustRootOptions[10].isUsed = binding.aSharpCb.isChecked
//                CustRootOptions[11].isUsed = binding.bCb.isChecked
//            }


            dismiss()
        }
        binding.cancelBt.setOnClickListener { dismiss() }
        return binding.root
    }


}