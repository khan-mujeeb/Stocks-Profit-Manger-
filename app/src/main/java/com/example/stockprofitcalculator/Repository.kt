package com.example.stockprofitcalculator

import androidx.lifecycle.LiveData

class Repository(private val dao: StockDao) {
    fun  addData(stock: Stock) = dao.insert(stock)
    val readAll: LiveData<List<Stock>> = dao.getAll()
}