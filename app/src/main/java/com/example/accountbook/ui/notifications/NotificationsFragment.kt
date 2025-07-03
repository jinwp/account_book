package com.example.accountbook.ui.notifications

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.accountbook.R
import com.example.accountbook.databinding.FragmentNotificationsBinding
import com.github.mikephil.charting.animation.Easing
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.utils.ColorTemplate


class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        val pieChart = binding.pieChart


// Show percent values
        pieChart.setUsePercentValues(true)

// Sample data
        val dataList: List<PieEntry> = listOf(
            PieEntry(70f, "감자"),
            PieEntry(30f, "고구마"),
            PieEntry(25f, "배추"),
            PieEntry(35f, "양파"),
            PieEntry(20f, "고추"),
            PieEntry(50f, "삼겹살"),
            PieEntry(20f, "콩나물")
        )

        val sortedDataList =  dataList.sortedByDescending { it.value }

// Create dataset with custom pastel colors
        val dataSet = PieDataSet(sortedDataList, "").apply {
            colors = listOf(
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow1),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow2),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow3),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow4),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow5),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow6),
                ContextCompat.getColor(requireContext(), R.color.pastel_rainbow7),
            )
            valueTextSize = 16f
            setDrawValues(false) // Hide values inside chart
        }

        val pieData = PieData(dataSet)

// Apply chart settings
        pieChart.apply {
            data = pieData
            description.isEnabled = false
            legend.isEnabled = false
            isRotationEnabled = true
            setEntryLabelColor(Color.BLACK)
            animateY(1400, Easing.EaseInOutQuad)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}