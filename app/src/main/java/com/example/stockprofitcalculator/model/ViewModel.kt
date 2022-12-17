package com.example.stockprofitcalculator.model

import android.app.Application
import android.widget.TextView
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.stockprofitcalculator.AppDatabase
import com.example.stockprofitcalculator.repository.Repository
import com.example.stockprofitcalculator.Stock
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ViewModel(application: Application): AndroidViewModel(application) {
    val dao = AppDatabase.getDataBase(application).stockDao()
    private val respository =  Repository(dao)

    // function to add data to room db
    fun addData(stock: Stock) {
        viewModelScope.launch(Dispatchers.IO){
            respository.addData(stock)
        }
    }

    //stores all the values which we will get from getAll
    val readAllStock: LiveData<List<Stock>> = dao.getAll()

    //function to delete data
    fun deleteEntery(stock: Stock){
        viewModelScope.launch {
            respository.deleteEntry(stock)
        }
    }

//    val totalSpent: LiveData<Int> = repository.totalSpent.asLiveData()
        val totalProfit = respository.totalProfit.asLiveData()

    fun xyz(profit:TextView){
        var x:Double = 0.0
        GlobalScope.launch {
            x = respository.returnProfits()

                profit.text = "₹"+String.format("%.2f", x).toString()


        }
    }

    fun pqr(invest:TextView){
        var y:Double = 0.0
        GlobalScope.launch {
            y = respository.returnInvested()

                invest.text = "₹"+String.format("%.2f", y).toString()


        }
    }
}