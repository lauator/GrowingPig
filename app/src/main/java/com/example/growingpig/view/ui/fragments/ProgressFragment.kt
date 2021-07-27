package com.example.growingpig.view.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.growingpig.R
import com.example.growingpig.databinding.FragmentProgressBinding
import com.github.mikephil.charting.charts.Chart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.listener.OnChartGestureListener
import com.github.mikephil.charting.listener.OnChartValueSelectedListener


class ProgressFragment : Fragment() {

    private lateinit var binding: FragmentProgressBinding

    private lateinit var lineChart: LineChart

    private val months = arrayOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December"
    )


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_progress, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProgressBinding.bind(view)

        lineChart = binding.lineChart

        /*lineChart.setOnChartValueSelectedListener(this)
        lineChart.onChartGestureListener = this*/


        lineChart.isDragEnabled = true
        lineChart.setScaleEnabled(false)

        val yValues: ArrayList<Entry> = arrayListOf()

        yValues.add(Entry(0f, 60f))
        yValues.add(Entry(1f, 50f))
        yValues.add(Entry(2f, 70f))
        yValues.add(Entry(3f, 30f))
        yValues.add(Entry(4f, 50f))

        val set1 = LineDataSet(yValues, "Data Set 1")

        set1.fillAlpha = 110

        val dataSets: ArrayList<LineDataSet> = arrayListOf()

        dataSets.add(set1)

        val data: LineData = LineData(dataSets as List<ILineDataSet>?)

        lineChart.data = data

    }


    fun axisX(axis: XAxis) {
        axis.isGranularityEnabled = true
        axis.position = XAxis.XAxisPosition.BOTTOM
        axis.valueFormatter = IndexAxisValueFormatter(months)

    }

    fun axisLeft(axis: YAxis) {
        axis.spaceTop = 30F
        axis.axisMinimum = 0F

    }

    fun axisRight(axis: YAxis) {
        axis.isEnabled = false
    }


}