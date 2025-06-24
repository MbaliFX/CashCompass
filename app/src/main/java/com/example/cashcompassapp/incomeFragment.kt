package com.example.cashcompassapp

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import java.util.*

class incomeFragment : Fragment() {

    private lateinit var viewModel: SharedViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_income, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize SharedViewModel
        viewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]

        // Find views
        val inputDate = view.findViewById<EditText>(R.id.inputDate)
        val inputAmount = view.findViewById<EditText>(R.id.inputAmount)
        val inputDescription = view.findViewById<EditText>(R.id.inputDescription)
        val inputNote = view.findViewById<EditText>(R.id.inputNote)
        val categorySpinner = view.findViewById<Spinner>(R.id.inputCategory)
        val walletSpinner = view.findViewById<Spinner>(R.id.inputWallet)
        val btnIncomeSave = view.findViewById<Button>(R.id.btnIncomeSave)

        // Set up Date Picker
        inputDate.setOnClickListener {
            val calendar = Calendar.getInstance()
            val datePicker = DatePickerDialog(
                requireContext(),
                { _, year, month, day ->
                    val date = "${day.toString().padStart(2, '0')}/${(month + 1).toString().padStart(2, '0')}/$year"
                    inputDate.setText(date)
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePicker.show()
        }

        // Set up Category Spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.category_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            categorySpinner.adapter = adapter
        }

        // Set up Wallet Spinner
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.wallet_options,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            walletSpinner.adapter = adapter
        }

        // Save button action
        btnIncomeSave.setOnClickListener {
            val entry = BudgetEntry(
                type = "Income",
                date = inputDate.text.toString(),
                amount = inputAmount.text.toString().toDoubleOrNull() ?: 0.0,
                description = inputDescription.text.toString(),
                category = categorySpinner.selectedItem.toString(),
                wallet = walletSpinner.selectedItem.toString(),
                note = inputNote.text.toString()
            )

            viewModel.addEntry(entry)
            Toast.makeText(context, "Income Saved successfully", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.homeFragment)
        }
    }
}
