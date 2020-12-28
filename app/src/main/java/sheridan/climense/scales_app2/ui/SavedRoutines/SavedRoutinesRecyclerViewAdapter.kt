package sheridan.climense.scales_app2.ui.SavedRoutines

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import sheridan.climense.scales_app2.database.SavedRoutine
import sheridan.climense.scales_app2.databinding.SavedroutineitemBinding
import sheridan.climense.scales_app2.model.PracticePackage
import sheridan.climense.scales_app2.model.RoutineGenerator

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

                    val routineToSend : Array<RoutineGenerator.Companion.practice>

                    if(routine.inProgress != null && routine.inProgress.isNotEmpty()){
                        routineToSend = routine.inProgress
                        Log.d("IF State", "In Progress" + routine.inProgress.size.toString())
                    }else{
                        routineToSend = routine.routine
                        Log.d("IF State", "routine routine " + routineToSend.size + "out of " + routine.total)
                    }
                    val action = SavedRoutinesPageDirections.savedRoutineToPractice(PracticePackage(
                            routine.title,
                            routineToSend,
                            true,
                            routine.key,
                            routine.total,
                            routine.date ))
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
