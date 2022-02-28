package sheridan.climense.scales_app2.ui.SavedRoutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.SavedroutinespageBinding
import sheridan.climense.scales_app2.models.PracticePackage
import sheridan.climense.scales_app2.util.SwipeToDeleteCallback

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
            adapter = SavedRoutinesRecyclerViewAdapter(viewModel)

        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            savedRecyclerView.adapter = adapter
            savedRecyclerView.addItemDecoration(divider)
            savedRecyclerView.layoutManager = LinearLayoutManager(context)

            favBeginBt.setOnClickListener {
                viewModel.favourites.observe(viewLifecycleOwner){
                    if(!it.isNullOrEmpty()){
                        binding.beginErrorTv.text = ""
//                        val action = SavedRoutinesPageDirections.savedRoutineToPractice(
//                            PracticePackage("favourites", it, true, total = it.size )
//                        )
//                        findNavController().navigate(action)
                    }
                    else{
                        binding.beginErrorTv.text = getString(R.string.no_favs_error)
                    }
                    
                }

            }
        }

        val swipeHandler = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = adapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(binding.savedRecyclerView)


        viewModel.savedRoutines.observe(viewLifecycleOwner){ adapter.routines = it.toMutableList() }

        return binding.root
    }
}


