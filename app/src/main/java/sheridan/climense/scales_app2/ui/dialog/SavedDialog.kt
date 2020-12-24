package sheridan.climense.scales_app2.ui.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import sheridan.climense.scales_app2.databinding.DialogsaveBinding
import sheridan.climense.scales_app2.ui.routine.RoutineCreator
import sheridan.climense.scales_app2.ui.routine.RoutineCreatorViewModel

class SavedDialog : DialogFragment() {
    private val routineCreatorViewModel: RoutineCreatorViewModel by viewModels()
    private lateinit var binding: DialogsaveBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogsaveBinding.inflate(inflater, container, false)

         val r = RoutineCreator()

        binding.confirmsaveBt.setOnClickListener{
            val name = binding.nameInputEt.text
            routineCreatorViewModel.saveRoutine(name.toString())
            dismiss()
        }

        return binding.root

    }



}