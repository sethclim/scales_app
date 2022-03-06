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

        binding.okBt.setOnClickListener {
            creatorVM.setEvent(CreatorContract.Event.ConfirmRootsEvent)
            dismiss()
        }

        binding.cancelBt.setOnClickListener { dismiss() }

        return binding.root
    }


}