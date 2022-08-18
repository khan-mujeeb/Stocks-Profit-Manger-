package com.example.stockprofitcalculator

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface StockDao {
    @Query("SELECT * FROM stock_table")
    fun getAll(): LiveData<List<Stock>>

    @Insert
    fun insert(stock: Stock)

}