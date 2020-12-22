package sheridan.climense.scales_app2.ui.SavedRoutines

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import sheridan.climense.scales_app2.databinding.SavedroutineitemBinding
import sheridan.climense.scales_app2.model.PracticePackage

class EntryRecyclerViewAdapter() : RecyclerView.Adapter<EntryRecyclerViewAdapter.ViewHolder>() {

    var routines: List<PracticePackage>? = null
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

    override fun getItemCount(): Int = routines?.size ?: 0

    class ViewHolder private constructor(private val binding: SavedroutineitemBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(routine: PracticePackage) {
            binding.routines = routine
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