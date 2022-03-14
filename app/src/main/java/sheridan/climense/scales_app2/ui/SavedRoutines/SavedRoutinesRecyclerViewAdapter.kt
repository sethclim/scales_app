package sheridan.climense.scales_app2.ui.SavedRoutines


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.domain.model.RoutineInfo
import sheridan.climense.kmmsharedmodule.features.saved_routines.SavedRoutinesContract
import sheridan.climense.kmmsharedmodule.features.saved_routines.SavedRoutinesViewModel
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.databinding.SavedroutineitemBinding
import sheridan.climense.scales_app2.models.PracticeSave

class SavedRoutinesRecyclerViewAdapter(private val savedRoutineVM: SavedRoutinesViewModel) : RecyclerView.Adapter<SavedRoutinesRecyclerViewAdapter.ViewHolder>() {

    var routines: MutableList<RoutineInfo>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder.from(parent, savedRoutineVM)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(routines!![position])
    }

    fun removeAt(position: Int) {

        val routine = routines?.get(position)

        routines?.removeAt(position)
        notifyItemRemoved(position)

        if (routine != null) {
            savedRoutineVM.setEvent(SavedRoutinesContract.Event.RemoveSavedRoutine(routine.id))
        }
    }

    override fun getItemCount(): Int = routines?.size?: 0

    class ViewHolder private constructor(private val binding: SavedroutineitemBinding,private val viewModel: SavedRoutinesViewModel): RecyclerView.ViewHolder(binding.root) {

        fun bind(routine: RoutineInfo) {
            binding.routines = routine
            binding.root.setOnClickListener{
                viewModel.setEvent(SavedRoutinesContract.Event.StartSavedRoutine(routineID = routine.id))
            }
        }

        companion object {
            fun from(parent: ViewGroup, viewModel: SavedRoutinesViewModel) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SavedroutineitemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding, viewModel)
            }
        }
    }
}
