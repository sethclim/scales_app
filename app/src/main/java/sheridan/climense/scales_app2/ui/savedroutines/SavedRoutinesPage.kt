package sheridan.climense.scales_app2.ui.savedroutines

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.base.mvi.UiEffect
import sheridan.climense.kmmsharedmodule.features.saved_routines.SavedRoutinesContract
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.SavedroutinespageBinding
import sheridan.climense.scales_app2.util.SwipeToDeleteCallback

class SavedRoutinesPage : Fragment() {
    private lateinit var binding: SavedroutinespageBinding
    private lateinit var adapter : SavedRoutinesRecyclerViewAdapter
    private lateinit var navController : NavController

    private val savedRoutinesVM: sheridan.climense.kmmsharedmodule.features.saved_routines.SavedRoutinesViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        savedRoutinesVM.setEvent(SavedRoutinesContract.Event.GetSavedRoutines)
        navController = findNavController()

        binding = SavedroutinespageBinding.inflate(inflater, container, false)
        adapter = SavedRoutinesRecyclerViewAdapter(savedRoutinesVM)

        with(binding){
            val divider = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            savedRecyclerView.adapter = adapter
            savedRecyclerView.addItemDecoration(divider)
            savedRecyclerView.layoutManager = LinearLayoutManager(context)

            favBeginBt.setOnClickListener { savedRoutinesVM.setEvent(SavedRoutinesContract.Event.StartFavourites) }
        }

        savedRoutinesVM.effect
            .onEach { uiEffect ->
                handleEffect(uiEffect)
            }
            .launchIn(lifecycleScope)

        viewLifecycleOwner.lifecycleScope.launch {
            savedRoutinesVM.uiState.collect { state ->
                if(state.savedRoutines.accessData() != null)
                    adapter.routines = state.savedRoutines.accessData()?.toMutableList()
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

        return binding.root
    }

    private fun handleEffect(uiEffect: UiEffect){
        when(uiEffect)
        {
            SavedRoutinesContract.Effect.FavouritesExist -> navController.navigate(R.id.savedRoutine_to_practice)
            SavedRoutinesContract.Effect.FavouritesError -> binding.beginErrorTv.text = getString(R.string.no_favs_error)
            SavedRoutinesContract.Effect.SavedRoutineSetup -> navController.navigate(R.id.savedRoutine_to_practice)
        }
    }
}


