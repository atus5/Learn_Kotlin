package com.example.nhatrobudai.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.DataModel.BillData
import com.example.nhatrobudai.ViewHolder.BillViewHolder
import com.example.nhatrobudai.R

class BillAdapter(val list : ArrayList<BillData>) : RecyclerView.Adapter<BillViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_history, parent, false)
        return BillViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        var listB = list[position]
        holder.title.text = listB.title
        holder.date.text = listB.date
        holder.bank.text = listB.bank
        holder.money.text = listB.money

    }
}