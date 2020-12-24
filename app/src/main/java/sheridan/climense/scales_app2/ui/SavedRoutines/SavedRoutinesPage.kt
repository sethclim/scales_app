package sheridan.climense.scales_app2.ui.SavedRoutines

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import sheridan.climense.scales_app2.databinding.SavedroutinespageBinding

class SavedRoutinesPage : Fragment() {
    private lateinit var binding: SavedroutinespageBinding
    private lateinit var adapter : SavedRoutinesRecyclerViewAdapter
    private val viewModel : SavedRoutinesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = SavedroutinespageBinding.inflate(inflater, container, false)
        adapter = SavedRoutinesRecyclerViewAdapter()

        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            savedRecyclerView.adapter = adapter
            savedRecyclerView.addItemDecoration(divider)
            savedRecyclerView.layoutManager = LinearLayoutManager(context)
        }

        viewModel.savedRoutines.observe(viewLifecycleOwner){
            adapter.routines = it
            Log.d("routines IT", it[0].progress.toString())
        }

        return binding.root
    }
}


