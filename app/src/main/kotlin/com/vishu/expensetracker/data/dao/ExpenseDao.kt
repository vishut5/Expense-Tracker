package com.vishu.expensetracker.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.vishu.expensetracker.data.model.ExpenseEntity
import com.vishu.expensetracker.data.model.ExpenseSummary
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {


    @Query("SELECT * FROM expense_table")
    fun getAllExpense(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM expense_table WHERE type = 'Expense' ORDER BY amount DESC LIMIT 5")
    fun getTopExpenses(): Flow<List<ExpenseEntity>>


    @Query("SELECT type, date, SUM(amount) AS total_amount FROM expense_table where type = :type GROUP BY type, date ORDER BY date")
    fun getAllExpenseByDate(type: String = "Expense"): Flow<List<ExpenseSummary>>

    @Insert
    suspend fun insertExpense(expenseEntity: ExpenseEntity)

    @Delete
    suspend fun deleteExpense(expenseEntity: ExpenseEntity)

    @Update
    suspend fun updateExpense(expenseEntity: ExpenseEntity)
}