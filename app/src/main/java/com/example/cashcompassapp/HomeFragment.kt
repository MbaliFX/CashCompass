package com.example.cashcompassapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        val incomeTextView = view.findViewById<TextView>(R.id.textIncome)
        val expensesTextView = view.findViewById<TextView>(R.id.textExpenses)
        val endingBalanceTextView = view.findViewById<TextView>(R.id.textEnding)

        val btnViewAllPlannedPayments = view.findViewById<Button>(R.id.btnViewAllPlannedPayments)
        val btnManageBills = view.findViewById<Button>(R.id.btnManageBills)

        btnViewAllPlannedPayments.setOnClickListener {
            Toast.makeText(requireContext(), "Viewing all planned payments...", Toast.LENGTH_SHORT).show()
            // You can navigate to a detailed PlannedPaymentsFragment if you have one
            // findNavController().navigate(R.id.action_homeFragment_to_plannedPaymentsFragment)
        }

        btnManageBills.setOnClickListener {
            Toast.makeText(requireContext(), "Manage your bills here.", Toast.LENGTH_SHORT).show()
            // You can navigate to a BillsFragment if needed
            // findNavController().navigate(R.id.action_homeFragment_to_manageBillsFragment)
        }

        sharedViewModel.entries.observe(viewLifecycleOwner) { entries ->
            var totalIncome = 0.0
            var totalExpenses = 0.0

            for (entry in entries) {
                if (entry.type == "Income") {
                    totalIncome += entry.amount
                } else if (entry.type == "Expense") {
                    totalExpenses += entry.amount
                }
            }

            val endingBalance = totalIncome - totalExpenses

            incomeTextView.text = "R%.2f".format(totalIncome)
            expensesTextView.text = "R%.2f".format(totalExpenses)
            endingBalanceTextView.text = "R%.2f".format(endingBalance)
        }
    }
}
