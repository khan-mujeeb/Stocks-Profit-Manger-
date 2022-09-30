package com.example.stockprofitcalculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {
    @Query("SELECT * FROM stock_table")
    fun getAll(): LiveData<List<Stock>>

    @Query("SELECT SUM(profit) FROM stock_table")
    suspend fun returnProfit(): Double

    @Query("SELECT SUM(invested) FROM stock_table")
    suspend fun returnInvested(): Double

//    @Query("SELECT SUM(profit) FROM stock_table")
//    fun getTotalSpent(): Flow<Double>

    @Insert
    fun insert(stock: Stock)

    @Delete
    suspend fun deleteEntry(stock: Stock)



}