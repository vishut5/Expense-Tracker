package com.vishu.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.github.mikephil.charting.data.Entry
import com.vishu.expensetracker.data.dao.ExpenseDao
import com.vishu.expensetracker.data.model.ExpenseSummary
import com.vishu.expensetracker.utils.Utils
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatsViewModel @Inject constructor(private val dao: ExpenseDao) : ViewModel() {

    val entries = dao.getAllExpenseByDate()
    val topEntries = dao.getTopExpenses()

    fun getEntriesForChart(expenseSummaries: List<ExpenseSummary>): List<Entry> {
        val list = mutableListOf<Entry>()
        for (entry in expenseSummaries) {
            val formattedDate = Utils.getMillisFromDate(entry.date)
            list.add(Entry(formattedDate.toFloat(), entry.total_amount.toFloat()))
        }
        return list
    }
}
