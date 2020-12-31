package sheridan.climense.scales_app2.ui.practicehistory

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
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

        val mode = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)
        viewModel.allPractice.observe(viewLifecycleOwner, { setData(it, mode) })

        return binding.root
    }

    private fun setData(data: List<PracticeRecord>, mode: Int?){
        val scales : MutableList<Entry>  = mutableListOf()
        val oct : MutableList<Entry>  = mutableListOf()
        val solid : MutableList<Entry>  = mutableListOf()
        val broken : MutableList<Entry>  = mutableListOf()
        val arps : MutableList<Entry>  = mutableListOf()
        val cm : MutableList<Entry>  = mutableListOf()

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

        val quarters = arrayOf("Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun", "Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun",)
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
        scalesset.setColor(Color.rgb(255, 241, 46));
        scalesset.setCircleColor(Color.rgb(255, 241, 46))
        scalesset.setLineWidth(2f)
        scalesset.setCircleRadius(5f)
        scalesset.setDrawCircleHole(false)
        scalesset.label = "Scales"
        scalesset.setDrawValues(false)

        val octSet = LineDataSet(oct, "oct")
        octSet.setColors(intArrayOf(R.color.design_default_color_secondary_variant), context)
        octSet.setCircleColor(R.color.design_default_color_secondary_variant)
        octSet.setLineWidth(2f)
        octSet.setCircleRadius(5f)
        octSet.setDrawCircleHole(false)
        octSet.label = "Oct"
        octSet.setDrawValues(false)

        val solidSet = LineDataSet(solid, "solid")
        solidSet.setColors(intArrayOf(R.color.design_default_color_error), context)
        solidSet.setCircleColor(R.color.design_default_color_error)
        solidSet.setLineWidth(2f)
        solidSet.setCircleRadius(5f)
        solidSet.setDrawCircleHole(false)
        solidSet.label = "Solid"
        solidSet.setDrawValues(false)

        val brokenSet = LineDataSet(broken, "broken")
        brokenSet.setColor(Color.rgb(78, 222, 62));
        brokenSet.setCircleColor(Color.rgb(78, 222, 62))
        brokenSet.setLineWidth(2f)
        brokenSet.setCircleRadius(5f)
        brokenSet.setDrawCircleHole(false)
        brokenSet.label = "Broken"
        brokenSet.valueTextSize = 10f
        brokenSet.setDrawValues(false)

        val arpsSet = LineDataSet(arps, "arps")
        arpsSet.setColor(Color.rgb(240, 76, 69));
        arpsSet.setCircleColor(Color.rgb(240, 76, 69))
        arpsSet.setLineWidth(2f)
        arpsSet.setCircleRadius(5f)
        arpsSet.setDrawCircleHole(false)
        arpsSet.label = "Arps"
        arpsSet.setDrawValues(false)



        val cmSet = LineDataSet(cm, "cm")
        cmSet.setColor(Color.rgb(56, 124, 245));
        cmSet.setCircleColor(Color.rgb(56, 124, 245))
        cmSet.setLineWidth(2f)
        cmSet.setCircleRadius(5f)
        cmSet.setDrawCircleHole(false)
        cmSet.label = "CM"
        cmSet.setDrawValues(false)


        val dataSets: MutableList<ILineDataSet> = ArrayList()
        dataSets.add(scalesset)
        dataSets.add(octSet)
        dataSets.add(solidSet)
        dataSets.add(brokenSet)
        dataSets.add(arpsSet)
        dataSets.add(cmSet)

        val lineData = LineData(dataSets)
        chart.data = lineData

        val right = chart.axisRight
        right.isEnabled = false

        val left: YAxis = chart.axisLeft
//        left.setAxisMinimum(0f);
        //left.setAxisMaximum(100f);
        //left.granularity
        left.axisMinimum = 0f
        left.axisMaximum = 100f

        val top: XAxis = chart.xAxis
        top.axisMinimum = 0f
        top.axisMaximum = 6f

        top.mAxisRange = 6f

        val legend = chart.getLegend();
        legend.isEnabled = true

        chart.description.text = ""

        when (mode) {
            Configuration.UI_MODE_NIGHT_YES -> {
                left.textColor = Color.WHITE
                top.textColor = Color.WHITE
                legend.textColor= Color.WHITE
            }
            Configuration.UI_MODE_NIGHT_NO -> {
                left.textColor = Color.BLACK
                top.textColor = Color.BLACK
                legend.textColor= Color.BLACK
            }
            Configuration.UI_MODE_NIGHT_UNDEFINED -> {}
        }

        chart.invalidate();
    }
}
