package com.example.cashcompassapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class StatisticsFragment : Fragment() {

    private lateinit var pieChart: PieChart
    private lateinit var badge: ImageView
    private var minBudget = 0
    private var maxBudget = 0
    private var totalExpense = 0f
    private val expenseEntries = ArrayList<PieEntry>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment (create fragment_statistics.xml)
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val minInput = view.findViewById<EditText>(R.id.minBudgetInput)
        val maxInput = view.findViewById<EditText>(R.id.maxBudgetInput)
        val setBudgetBtn = view.findViewById<Button>(R.id.setBudgetBtn)
        val expenseInput = view.findViewById<EditText>(R.id.expenseInput)
        val addExpenseBtn = view.findViewById<Button>(R.id.addExpenseBtn)
        pieChart = view.findViewById(R.id.pieChart)
        badge = view.findViewById(R.id.congratsBadge)

        badge.visibility = View.GONE

        setBudgetBtn.setOnClickListener {
            minBudget = minInput.text.toString().toIntOrNull() ?: 0
            maxBudget = maxInput.text.toString().toIntOrNull() ?: 0
            Toast.makeText(requireContext(), "Budget Set!", Toast.LENGTH_SHORT).show()
        }

        addExpenseBtn.setOnClickListener {
            val expense = expenseInput.text.toString().toFloatOrNull()
            if (expense != null) {
                totalExpense += expense
                expenseEntries.add(PieEntry(expense, "Item ${expenseEntries.size + 1}"))
                updateChart()

                if (totalExpense >= maxBudget) {
                    badge.visibility = View.VISIBLE
                    Toast.makeText(requireContext(), "Congratulations! Max Budget Reached!", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(requireContext(), "Enter a valid expense", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun updateChart() {
        val dataSet = PieDataSet(expenseEntries, "Expenses")
        dataSet.colors = ColorTemplate.MATERIAL_COLORS.toList()
        val pieData = PieData(dataSet)
        pieChart.data = pieData
        pieChart.invalidate()
    }
}
