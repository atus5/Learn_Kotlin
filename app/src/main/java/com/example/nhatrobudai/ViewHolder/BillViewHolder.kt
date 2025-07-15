package com.example.nhatrobudai.ViewHolder

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.R

class BillViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val title : TextView = itemView.findViewById(R.id.txtTitle1)
    val date : TextView = itemView.findViewById(R.id.txtDate1)
    val bank : TextView = itemView.findViewById(R.id.txtBank)
    val money : TextView = itemView.findViewById(R.id.txtMoney)
}