package com.example.cashcompassapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class AddFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnAddIncome = view.findViewById<Button>(R.id.btnAddIncome)
        val btnAddExpense = view.findViewById<Button>(R.id.btnAddExpense)

        btnAddIncome.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_incomeFragment)
        }

        btnAddExpense.setOnClickListener {
            findNavController().navigate(R.id.action_addFragment_to_expenseFragment)
        }
    }
}
