package sheridan.climense.scales_app2.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.features.settings.SettingsContract
import sheridan.climense.scales_app2.databinding.DialogconfirmationBinding


class ConfirmationDialog : DialogFragment() {

    private val settingsVM : sheridan.climense.kmmsharedmodule.features.settings.SettingsViewModel by inject()
    private lateinit var binding: DialogconfirmationBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogconfirmationBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.confirmsaveBt.setOnClickListener {
            val mNum = arguments?.getInt("num");

            if(mNum != null){
                deleteWhat(mNum)
                dismiss()
            }
        }

        binding.saveCancelBt.setOnClickListener {
            dismiss() }
        return binding.root
    }

    private fun deleteWhat(operation : Int) {
        when(operation){
            1 -> settingsVM.setEvent(SettingsContract.Event.RemoveAllFavourites)
            2 -> settingsVM.setEvent(SettingsContract.Event.RemoveALlSavedRoutines)
            3 -> settingsVM.setEvent(SettingsContract.Event.RemoveAllHistory)
            4 -> settingsVM.setEvent(SettingsContract.Event.RemoveALlData)
        }
    }


}