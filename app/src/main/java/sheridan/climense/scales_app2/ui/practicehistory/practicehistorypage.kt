package sheridan.climense.scales_app2.ui.practicehistory

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.database.PracticeRecord
import sheridan.climense.scales_app2.databinding.LinegraphBinding
import sheridan.climense.scales_app2.databinding.PracticePageFragmentBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PracticeHistoryPage : Fragment(){

    private  lateinit var  binding : LinegraphBinding
    private val viewModel : PracticeHistoryPageViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = LinegraphBinding.inflate(inflater, container, false)


        val data = mutableListOf<PracticeRecord>()
        viewModel.allPractice.observe(viewLifecycleOwner, { setData(it) })
        Log.d("it", data.toString())




        return binding.root
    }

    private fun setData(data : List<PracticeRecord> ){
        val entries : MutableList<Entry>  = mutableListOf();
        for ( obj in data) {
            // turn your data into Entry objects
            var formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            var temp = LocalDate.parse(obj.date, formatter).dayOfYear.toFloat()
            entries.add(Entry(obj.scales.toFloat(), temp));
        }
        val chart : LineChart = binding.chart
        val dataset = LineDataSet(entries, "label")
        val lineData = LineData(dataset)
        chart.setData(lineData)
    }
}
