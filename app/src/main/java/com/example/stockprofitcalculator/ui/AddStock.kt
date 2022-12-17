package com.example.stockprofitcalculator.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.stockprofitcalculator.R
import com.example.stockprofitcalculator.Stock
import com.example.stockprofitcalculator.model.ViewModel

class AddStock : AppCompatActivity() {

    // function to add data using view model
    fun writeData() {
        var viewModel: ViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        val Name: String = findViewById<EditText>(R.id.stockName).text.toString()
        val Qty: String = findViewById<EditText>(R.id.stockQty).text.toString()
        val Avg: String = findViewById<EditText>(R.id.stockAvgPrice).text.toString()
        val Sell: String = findViewById<EditText>(R.id.stockSellPrice).text.toString()


        val q:Int = Integer.parseInt(Qty)
        val x:Double = Avg.toDouble()
        val y: Double = Sell.toDouble()

        var temp: Double = q*(y-x)
        val invested: Double = q*x
        if (Name.isNotEmpty() && Qty.isNotEmpty() && Avg.isNotEmpty() && Sell.isNotEmpty()) {
            val stock = Stock(null,Name,Qty,Avg,Sell,q,x,y,invested,temp)
            viewModel.addData(stock)
            Toast.makeText(this, "Added Successfully", Toast.LENGTH_SHORT).show()
        }else {
            Toast.makeText(this, "Enter All Fields", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stock)


        val Btn: Button = findViewById(R.id.addBtn)

        Btn.setOnClickListener(View.OnClickListener {
            writeData()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })

    }


}