package com.example.stockprofitcalculator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter: RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

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
        holder.qty.text = currentStock.newstockQty.toString() + " qty"
        holder.avgP.text = "₹"+currentStock.newavgPrice
        holder.sellP.text = "₹"+currentStock.newsellPrice
        holder.profit.text = "₹"+ String.format("%.2f",currentStock.profit)
    }

    override fun getItemCount(): Int {
        return stock.size
    }
    fun setData(stock: List<Stock>) {
        this.stock = stock
        notifyDataSetChanged()
    }

    fun getStockAt(pos:Int):Stock{
        return stock.get(pos)
    }
}