package com.example.stockprofitcalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockprofitcalculator.R.id.addNewStockBtn
import com.example.stockprofitcalculator.R.id.stockRecyclerView



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        setSupportActionBar(findViewById(R.id.ab))
        var Btn: CardView = findViewById(addNewStockBtn)
        val profit: TextView = findViewById(R.id.Return)
        val invset: TextView = findViewById(R.id.Invested)

        profit.text = "₹"+ dashBoardVariable.Profit.toString()
        invset.text = "₹"+ dashBoardVariable.netInvestment.toString()

        val rc: RecyclerView = findViewById(R.id.stockRecyclerView)
        val  adapter = MyAdapter()
        rc.adapter = adapter
        rc.layoutManager = LinearLayoutManager(this)
        var viewModel: ViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllStock.observe(this,{stock->adapter.setData(stock)})



        // Button
        Btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddStock::class.java)
            startActivity(intent)
        })

    }
}