package com.vishu.expensetracker.viewmodel

import androidx.lifecycle.ViewModel
import com.vishu.expensetracker.data.dao.ExpenseDao
import com.vishu.expensetracker.data.model.ExpenseEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddExpenseViewModel @Inject constructor(val dao: ExpenseDao) : ViewModel() {

    suspend fun addExpense(expenseEntity: ExpenseEntity): Boolean {
        return try {
            dao.insertExpense(expenseEntity)
            true
        } catch (ex: Throwable) {
            false
        }
    }
}