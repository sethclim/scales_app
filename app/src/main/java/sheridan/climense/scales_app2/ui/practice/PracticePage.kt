package sheridan.climense.scales_app2.ui.practice

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import sheridan.climense.scales_app2.model.PracticeCycler
import java.util.*

class PracticePage : Fragment() {

    private val safeArgs: PracticePageArgs by navArgs()
    private val viewModel: PracticePageViewModel by viewModels()
    private lateinit var binding : PracticePageFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = PracticePageFragmentBinding.inflate(inflater, container, false)
        binding.practiceViewModel = viewModel
        binding.lifecycleOwner = this

        val practiceArray = safeArgs.PracticePackage.practice_array
        PracticeCycler.practiceArray = practiceArray.toMutableList()



        binding.nextBt.setOnClickListener { next() }

        return binding.root
    }

    private fun next(){
        viewModel.next()

    }

    override fun onPause() {
        super.onPause()
        viewModel.saveRecord()
    }

    override fun onResume() {
        super.onResume()

        viewModel.record.observe(viewLifecycleOwner, Observer { viewModel.loadRecord(it) })
    }
}