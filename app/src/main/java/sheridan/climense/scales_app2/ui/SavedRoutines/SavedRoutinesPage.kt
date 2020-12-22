package sheridan.climense.scales_app2.ui.SavedRoutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import sheridan.climense.scales_app2.databinding.SavedroutinespageBinding

class SavedRoutinesPage : Fragment() {
    private lateinit var binding: SavedroutinespageBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SavedroutinespageBinding.inflate(inflater, container, false)



        return binding.root
    }
}


