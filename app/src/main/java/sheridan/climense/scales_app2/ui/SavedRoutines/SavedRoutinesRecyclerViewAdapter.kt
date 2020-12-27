package sheridan.climense.scales_app2.ui.SavedRoutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.databinding.SavedroutineitemBinding
import sheridan.climense.scales_app2.model.PracticePackage

class SavedRoutinesRecyclerViewAdapter(private val viewModel: SavedRoutinesViewModel) : RecyclerView.Adapter<SavedRoutinesRecyclerViewAdapter.ViewHolder>() {

    var routines: MutableList<SavedRoutine>? = null
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(routines!![position])
    }

    fun removeAt(position: Int) {

        val routine = routines?.get(position)

        routines?.removeAt(position)
        notifyItemRemoved(position)

        if (routine != null) {
            viewModel.removeSavedroutine(routine.key)
        }
    }

    override fun getItemCount(): Int = routines?.size?: 0

    class ViewHolder private constructor(private val binding: SavedroutineitemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: SavedRoutine) {
            binding.routines = routine
            binding.root.setOnClickListener{
                val action = SavedRoutinesPageDirections.savedRoutineToPractice(PracticePackage(routine.title,routine.routine))
                it.findNavController().navigate(action)
            }
        }

        companion object {
            fun from(parent: ViewGroup) : ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = SavedroutineitemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
}