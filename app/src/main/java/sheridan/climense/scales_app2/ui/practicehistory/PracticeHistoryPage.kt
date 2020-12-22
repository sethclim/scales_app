package sheridan.climense.scales_app2.ui.practicehistory


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.database.PracticeRecord
import sheridan.climense.scales_app2.databinding.LinegraphBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter


class PracticeHistoryPage : Fragment(){

    private  lateinit var  binding : LinegraphBinding
    private val viewModel : PracticeHistoryPageViewModel by viewModels()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = LinegraphBinding.inflate(inflater, container, false)


        val data = mutableListOf<PracticeRecord>()
        viewModel.allPractice.observe(viewLifecycleOwner, { setData(it) })
        Log.d("it", data.toString())




        return binding.root
    }

    private fun setData(data: List<PracticeRecord>){
        val scales : MutableList<Entry>  = mutableListOf();
        val oct : MutableList<Entry>  = mutableListOf();
        val solid : MutableList<Entry>  = mutableListOf();
        val broken : MutableList<Entry>  = mutableListOf();
        val arps : MutableList<Entry>  = mutableListOf();
        val cm : MutableList<Entry>  = mutableListOf();
        for ( obj in data) {
            // turn your data into Entry objects
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy")
            val temp = LocalDate.parse(obj.date, formatter).dayOfWeek.value.toFloat()
            scales.add(Entry(temp, obj.scales.toFloat()));
            oct.add(Entry(temp, obj.oct.toFloat()));
            solid.add(Entry(temp, obj.solid.toFloat()));
            broken.add(Entry(temp, obj.broken.toFloat()));
            arps.add(Entry(temp, obj.arps.toFloat()));
            cm.add(Entry(temp, obj.conMotion.toFloat()));
        }

        val quarters = arrayOf("Sun", "Mon", "Tues", "Weds", "Thurs", "Fri", "Sat")
        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {
                return quarters[value.toInt()]
            }
        }

        val chart : LineChart = binding.chart
        val xAxis: XAxis = chart.getXAxis()
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter

        val scalesset = LineDataSet(scales, "scales")
        scalesset.setColors(intArrayOf(R.color.design_default_color_primary), context)
        val octSet = LineDataSet(oct, "oct")
        octSet.setColors(intArrayOf(R.color.design_default_color_secondary_variant), context)
        val solidSet = LineDataSet(oct, "solid")
        solidSet.setColors(intArrayOf(R.color.design_default_color_error), context)
        val brokenSet = LineDataSet(oct, "broken")
        brokenSet.setColors(intArrayOf(R.color.design_default_color_primary), context)
        val arpsSet = LineDataSet(oct, "arps")
        arpsSet.setColors(intArrayOf(R.color.design_default_color_primary), context)
        val cmSet = LineDataSet(oct, "cm")
        cmSet.setColors(intArrayOf(R.color.black), context)


        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(scalesset)
        dataSets.add(octSet)
        dataSets.add(solidSet)
        dataSets.add(brokenSet)
        dataSets.add(arpsSet)
        dataSets.add(cmSet)

        val lineData = LineData(dataSets)
        chart.setData(lineData)

        val left: YAxis = chart.getAxisLeft()
        left.setAxisMinimum(0f);
        //left.setAxisMaximum(100f);
        //left.granularity
        left.axisMinimum = 0f
        left.axisMaximum = 100f

        val legend = chart.getLegend();
        legend.isEnabled = true

        chart.invalidate();
    }
}
