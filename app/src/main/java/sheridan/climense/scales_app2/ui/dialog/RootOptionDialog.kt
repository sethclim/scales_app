package sheridan.climense.scales_app2.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import sheridan.climense.scales_app2.databinding.DialogRootoptionsBinding
import sheridan.climense.scales_app2.model.RoutineInputs
import sheridan.climense.scales_app2.ui.routine.RoutineCreatorViewModel
import java.lang.IllegalStateException

class RootOptionDialog : DialogFragment() {

    private val routineCreatorViewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding: DialogRootoptionsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogRootoptionsBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.okBt.setOnClickListener {

            RoutineInputs.CustRootOptions[0].isUsed = binding.cCb.isChecked
            RoutineInputs.CustRootOptions[1].isUsed = binding.cSharpCb.isChecked
            RoutineInputs.CustRootOptions[2].isUsed = binding.dCb.isChecked
            RoutineInputs.CustRootOptions[3].isUsed = binding.dSharpCb.isChecked
            RoutineInputs.CustRootOptions[4].isUsed = binding.eCb.isChecked
            RoutineInputs.CustRootOptions[5].isUsed = binding.fCb.isChecked
            RoutineInputs.CustRootOptions[6].isUsed = binding.fSharpCb.isChecked
            RoutineInputs.CustRootOptions[7].isUsed = binding.gCb.isChecked
            RoutineInputs.CustRootOptions[8].isUsed = binding.gSharpCb.isChecked
            RoutineInputs.CustRootOptions[9].isUsed = binding.aCb.isChecked
            RoutineInputs.CustRootOptions[10].isUsed = binding.aSharpCb.isChecked
            RoutineInputs.CustRootOptions[11].isUsed = binding.bCb.isChecked

            dismiss()
        }
        binding.cancelBt.setOnClickListener { dismiss() }
        return binding.root
    }


}