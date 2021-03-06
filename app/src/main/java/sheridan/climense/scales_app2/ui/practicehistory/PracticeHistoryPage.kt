package sheridan.climense.scales_app2.ui.practicehistory

import android.content.res.Configuration
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import sheridan.climense.kmmsharedmodule.domain.model.PracticeSession
import sheridan.climense.kmmsharedmodule.features.history.HistoryContract
import sheridan.climense.kmmsharedmodule.features.history.HistoryViewModel
import sheridan.climense.scales_app2.R
import sheridan.climense.scales_app2.databinding.LinegraphBinding
import java.time.LocalDate
import java.time.ZoneId
import java.util.*


class PracticeHistoryPage : Fragment(){

    private  lateinit var  binding : LinegraphBinding
    private val historyVM: HistoryViewModel by inject()

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View {
        binding = LinegraphBinding.inflate(inflater, container, false)

        val date = LocalDate.now()
        val weekAgo = date.minusWeeks(1)
        val zoneId: ZoneId = ZoneId.systemDefault() // or: ZoneId.of("Europe/Oslo");
        val epoch: Long = date.atStartOfDay(zoneId).toEpochSecond()
        val epochWeekAgo: Long = weekAgo.atStartOfDay(zoneId).toEpochSecond()

        historyVM.setEvent(HistoryContract.Event.SetNewRangeEvent(epochWeekAgo, epoch))

        val mode = context?.resources?.configuration?.uiMode?.and(Configuration.UI_MODE_NIGHT_MASK)

        viewLifecycleOwner.lifecycleScope.launch {
            historyVM.uiState.collect{ state ->
                val practiceHistory = state.practiceHistory.accessData()

                Log.d("PH", "$practiceHistory")

                if(practiceHistory != null)
                    setData(practiceHistory, mode)
            }
        }

        return binding.root
    }

    private fun setData(data: List<PracticeSession>, mode: Int?){
        val scales : MutableList<Entry>  = mutableListOf()
        val oct : MutableList<Entry>  = mutableListOf()
        val solid : MutableList<Entry>  = mutableListOf()
        val broken : MutableList<Entry>  = mutableListOf()
        val arps : MutableList<Entry>  = mutableListOf()
        val cm : MutableList<Entry>  = mutableListOf()

        var max = 0L
        fun getMax(value : Long){
            if(value > max ){
                max = value
            }
        }

        for (session in data) {
            // turn your data into Entry objects
            val temp = LocalDate.ofEpochDay(session.date).dayOfWeek.value.toFloat() - 1
            scales.add(Entry(temp, session.scale.toFloat()));
            getMax(session.scale)
            oct.add(Entry(temp, session.oct.toFloat()));
            getMax(session.oct)
            solid.add(Entry(temp, session.solid.toFloat()));
            getMax(session.solid)
            broken.add(Entry(temp, session.broken.toFloat()));
            getMax(session.broken)
            arps.add(Entry(temp, session.arps.toFloat()));
            getMax(session.arps)
            cm.add(Entry(temp, session.conMotion.toFloat()));
            getMax(session.conMotion)
        }

        val quarters = arrayOf("Mon", "Tues", "Weds", "Thurs", "Fri", "Sat", "Sun")
        val formatter: ValueFormatter = object : ValueFormatter() {
            override fun getAxisLabel(value: Float, axis: AxisBase): String {

                return quarters[value.toInt()]
            }
        }

        val chart : LineChart = binding.chart
        val xAxis: XAxis = chart.xAxis
        xAxis.granularity = 1f // minimum axis-step (interval) is 1
        xAxis.valueFormatter = formatter

        val scalesset = LineDataSet(scales, "scales")
        scalesset.color = Color.rgb(255, 241, 46);
        scalesset.setCircleColor(Color.rgb(255, 241, 46))
        scalesset.lineWidth = 2f
        scalesset.circleRadius = 5f
        scalesset.setDrawCircleHole(false)
        scalesset.label = "Scales"
        scalesset.setDrawValues(false)

        val octSet = LineDataSet(oct, "oct")
        octSet.setColors(intArrayOf(R.color.design_default_color_secondary_variant), context)
        octSet.setCircleColor(R.color.design_default_color_secondary_variant)
        octSet.lineWidth = 2f
        octSet.circleRadius = 5f
        octSet.setDrawCircleHole(false)
        octSet.label = "Oct"
        octSet.setDrawValues(false)

        val solidSet = LineDataSet(solid, "solid")
        solidSet.setColors(intArrayOf(R.color.design_default_color_error), context)
        solidSet.setCircleColor(R.color.design_default_color_error)
        solidSet.lineWidth = 2f
        solidSet.circleRadius = 5f
        solidSet.setDrawCircleHole(false)
        solidSet.label = "Solid"
        solidSet.setDrawValues(false)

        val brokenSet = LineDataSet(broken, "broken")
        brokenSet.color = Color.rgb(78, 222, 62);
        brokenSet.setCircleColor(Color.rgb(78, 222, 62))
        brokenSet.lineWidth = 2f
        brokenSet.circleRadius = 5f
        brokenSet.setDrawCircleHole(false)
        brokenSet.label = "Broken"
        brokenSet.valueTextSize = 10f
        brokenSet.setDrawValues(false)

        val arpsSet = LineDataSet(arps, "arps")
        arpsSet.color = Color.rgb(240, 76, 69);
        arpsSet.setCircleColor(Color.rgb(240, 76, 69))
        arpsSet.lineWidth = 2f
        arpsSet.circleRadius = 5f
        arpsSet.setDrawCircleHole(false)
        arpsSet.label = "Arps"
        arpsSet.setDrawValues(false)



        val cmSet = LineDataSet(cm, "cm")
        cmSet.color = Color.rgb(56, 124, 245);
        cmSet.setCircleColor(Color.rgb(56, 124, 245))
        cmSet.lineWidth = 2f
        cmSet.circleRadius = 5f
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
        //left.setAxisMinimum(0f);
        //left.setAxisMaximum(100f);
        //left.granularity
        left.axisMinimum = 0f
        left.axisMaximum = (max + 40).toFloat()

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
