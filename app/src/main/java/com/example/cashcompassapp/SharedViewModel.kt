package com.example.cashcompassapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

data class BudgetEntry(
    val type: String, // "Income" or "Expense"
    val date: String,
    val amount: Double,
    val description: String,
    val category: String,
    val wallet: String,
    val note: String
)

class SharedViewModel : ViewModel() {
    private val _entries = MutableLiveData<MutableList<BudgetEntry>>(mutableListOf())
    val entries: LiveData<MutableList<BudgetEntry>> get() = _entries

    fun addEntry(entry: BudgetEntry) {
        _entries.value?.add(entry)
        _entries.value = _entries.value // Notify observers
    }
}
