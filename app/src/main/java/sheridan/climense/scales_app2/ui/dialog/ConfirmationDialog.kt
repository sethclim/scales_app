package sheridan.climense.scales_app2.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import sheridan.climense.scales_app2.databinding.DialogRootoptionsBinding
import sheridan.climense.scales_app2.databinding.DialogconfirmationBinding
import sheridan.climense.scales_app2.model.RoutineInputs
import sheridan.climense.scales_app2.ui.routine.RoutineCreatorViewModel
import sheridan.climense.scales_app2.ui.settings.SettingsViewModel


class ConfirmationDialog : DialogFragment() {

    private val settingsViewModel: SettingsViewModel by activityViewModels()
    private lateinit var binding: DialogconfirmationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogconfirmationBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.confirmsaveBt.setOnClickListener {
            deleteWhat()
            dismiss()
        }
        binding.saveCancelBt.setOnClickListener {
            dismiss() }
        return binding.root
    }

    private fun deleteWhat() {
        Log.d("Delete What called num: ", settingsViewModel.deleteNum.toString() )
        when(settingsViewModel.deleteNum){
            1 -> settingsViewModel.deleteFavs()
            2 -> settingsViewModel.deleteRoutines()
            3 -> settingsViewModel.deleteHistory()
            4 -> settingsViewModel.deleteAll()
        }
    }


}