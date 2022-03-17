package sheridan.climense.scales_app2.ui.dialog

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.features.creator.CreatorContract
import sheridan.climense.kmmsharedmodule.features.creator.CreatorViewModel
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.DialogsaveBinding
import java.util.*

class SavedDialog : DialogFragment() {

    private lateinit var binding: DialogsaveBinding
    private val creatorVM: CreatorViewModel by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        binding = DialogsaveBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.confirmsaveBt.setOnClickListener{
            val name = binding.nameInputEt.text

            if(name.isNotEmpty()){
                val date = getCurrentDateTime()
                //TODO
                //Should be date
                creatorVM.setEvent(CreatorContract.Event.SaveRoutine(name.toString()))
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