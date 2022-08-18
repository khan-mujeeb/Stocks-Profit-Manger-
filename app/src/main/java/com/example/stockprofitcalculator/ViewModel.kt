package com.example.stockprofitcalculator

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
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

}