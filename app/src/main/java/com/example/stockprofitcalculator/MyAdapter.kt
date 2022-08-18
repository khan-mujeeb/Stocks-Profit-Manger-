package com.example.stockprofitcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


//    val stock = Stock(null,"null","null","null","null")
var stock: List<Stock> = emptyList()
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var naame: TextView = itemView.findViewById(R.id.RstockName)
        var qty: TextView = itemView.findViewById(R.id.RQty)
        var avgP: TextView = itemView.findViewById(R.id.RStockAvgPrice)
        var sellP: TextView = itemView.findViewById(R.id.RSellingPrice)
        var profit: TextView = itemView.findViewById(R.id.RStockProfit)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater: LayoutInflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.item_view,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentStock:Stock = stock[position]
        holder.naame.text = currentStock.stockName
        holder.qty.text = currentStock.stockQty
        holder.avgP.text = "₹"+currentStock.avgPrice
        holder.sellP.text = "₹"+currentStock.sellPrice

        // profit calculation
        val qty:Double = currentStock.stockQty.toDouble()
        val avgp:Double = currentStock.avgPrice.toDouble()
        val sp:Double = currentStock.sellPrice.toDouble()
        val netProfit: Double = ((sp-avgp)*qty)

        // Dashboard update
        dashBoardVariable.Profit+=netProfit
        dashBoardVariable.netInvestment+=avgp

        holder.profit.text = "₹$netProfit"
    }

    override fun getItemCount(): Int {
        return stock.size
    }
    fun setData(stock: List<Stock>) {
        this.stock = stock
        notifyDataSetChanged()
    }
}