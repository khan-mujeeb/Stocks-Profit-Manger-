package com.example.stockprofitcalculator

import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class Repository(private val dao: StockDao) {

//    val totalProfit:Flow<Double> = dao.getTotalSpent()


    // function to add data
    fun  addData(stock: Stock) = dao.insert(stock)

    //function to return profit
    suspend fun returnProfits():Double {
        var temp = dao.returnProfit()
//        p = temp
//        println("repository $temp")
        return temp
    }

    //function to return invested money
    suspend fun returnInvested():Double {
        var temp = dao.returnInvested()
        return temp
    }


    //function to delete item
    suspend fun deleteEntry(stock: Stock) = dao.deleteEntry(stock)

    val readAll: LiveData<List<Stock>> = dao.getAll()
//    val profitCal:Double = dao.returnProfit()

//    val totalSpent: Flow<Int> = transactionDAO.getTotalSpent()



}