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
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.DialogsaveBinding
import sheridan.climense.scales_app2.ui.routine.RoutineCreator
import sheridan.climense.scales_app2.ui.routine.RoutineCreatorViewModel
import sheridan.climense.scales_app2.util.DateConverters
import java.util.*

class SavedDialog : DialogFragment() {
    //private val routineCreatorViewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding: DialogsaveBinding

    private val routineCreatorVM: sheridan.climense.kmmsharedmodule.viewmodels.RoutineCreatorViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogsaveBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmsaveBt.setOnClickListener{
            val name = binding.nameInputEt.text

            if(name.isNotEmpty()){
                val date = getCurrentDateTime()
                //routineCreatorViewModel.saveRoutine(name.toString(),date)
                //Should be date
                routineCreatorVM.saveRoutine(name.toString(), 0L)
                binding.saveDialogErrorTv.text = ""
                dismiss()
            }
            else{
                binding.saveDialogErrorTv.text = getString(R.string.empty_name_error)
            }

        }
        binding.saveCancelBt.setOnClickListener { dismiss() }

        return binding.root

    }

    fun getCurrentDateTime(): Date {
        val cal = Calendar.getInstance().time
        return cal
    }

}