package com.example.stockprofitcalculator

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stock_table")
data class Stock(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    @ColumnInfo(name = "stock_name") val stockName:String,
    @ColumnInfo(name = "stock_qty") val stockQty:String,
    @ColumnInfo(name = "avg_price") val avgPrice:String,
    @ColumnInfo(name = "sell_price") val sellPrice:String
)