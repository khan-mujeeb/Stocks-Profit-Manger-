package com.example.stockprofitcalculator.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stockprofitcalculator.AppDatabase
import com.example.stockprofitcalculator.MyAdapter
import com.example.stockprofitcalculator.R
import com.example.stockprofitcalculator.R.id.addNewStockBtn
import com.example.stockprofitcalculator.model.ViewModel
import com.example.stockprofitcalculator.repository.Repository
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


//public var p = 1.1
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // try block to hide Action bar
        // try block to hide Action bar
        try {
            this.supportActionBar!!.hide()
        } // catch block to handle NullPointerException
        catch (e: NullPointerException) {
        }

        // database object declaration
        val dao = AppDatabase.getDataBase(application).stockDao()
        val respository =  Repository(dao)

        // references of dashboard fields
        var Btn: CardView = findViewById(addNewStockBtn)
        val profit: TextView = findViewById(R.id.Return)
        val invest: TextView = findViewById(R.id.Invested)


        // setting content of recycler view
        val rc: RecyclerView = findViewById(R.id.stockRecyclerView)
        val  adapter = MyAdapter()
        rc.adapter = adapter
        rc.layoutManager = LinearLayoutManager(this)
        var viewModel: ViewModel = ViewModelProvider(this).get(ViewModel::class.java)
        viewModel.readAllStock.observe(this) { stock -> adapter.setData(stock) }

//        val temp = viewModel.totalProfit.value
//        println("lets try $temp")

        // setting content of dashboard

        viewModel.xyz(profit)   // profit
        viewModel.pqr(invest)   //invested

        

            var x:Double = 0.0
            var y = 0.0


            val pieProfit:Float = x.toFloat()
            val pieInvested:Float = y.toFloat()
            setupPieCHart(pieProfit,pieInvested)



        // left swipe to delete the data from room db and recycler view
        var itemTouchHelperCallbacks = object : ItemTouchHelper.SimpleCallback(0,
            ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
        ){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val pos = viewHolder.adapterPosition
                val stock = adapter.getStockAt(pos)
                viewModel.deleteEntery(stock)

//                if(adapter.getItemCount()>0){

                    val pieProfit:Float = x.toFloat()
                    val pieInvested:Float = y.toFloat()
                    setupPieCHart(pieProfit,pieInvested)


                Toast.makeText(this@MainActivity,"deleted",Toast.LENGTH_SHORT).show()
            }

        }
        // attach swipe to recycler view
        ItemTouchHelper(itemTouchHelperCallbacks).apply {
            attachToRecyclerView(rc)
        }

        // Button
        Btn.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, AddStock::class.java)
            startActivity(intent)
        })
        
        // PieChart

    }
    fun setupPieCHart(pieProfit:Float,pieInvested:Float){

        val total:Float = pieProfit+pieInvested
        val x:Float = (pieProfit/total)*100
        val y:Float = (pieInvested/total)*100

//        println(" x and y = $pieProfit, $pieInvested")


        val pieChart:com.github.mikephil.charting.charts.PieChart = findViewById(R.id.pieChart)
        // data to show in pie chart
        val pieEntries = arrayListOf<PieEntry>()
        pieEntries.add(PieEntry( 30.0f,"profit"))
        pieEntries.add(PieEntry( 70.0f,"invested"))

        // setup piechart animation
        pieChart.animateXY(1000,1000)

        //set piechart entry color
        val pieDataSet = PieDataSet(pieEntries,"")
        pieDataSet.setColors(
            resources.getColor(R.color.green),
            resources.getColor(R.color.yellow)
        )

        // setup pie data set in pieData
        val  pieData = PieData(pieDataSet)

        // This enabled the value in each pieEntry
        pieData.setDrawValues(true)

        pieChart.data = pieData
    }
}



