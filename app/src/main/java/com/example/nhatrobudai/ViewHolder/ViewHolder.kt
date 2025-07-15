package com.example.nhatrobudai.ViewHolder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.R

class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
    val imgRoom : ImageView = itemView.findViewById(R.id.imgRoom)
    val txtRoomName : TextView = itemView.findViewById(R.id.txtRoomName)
    val txtPrice : TextView = itemView.findViewById(R.id.txtPrice)
    val txtStatus : TextView = itemView.findViewById(R.id.txtStatus)
    val btnThue : Button = itemView.findViewById(R.id.btnThue)
}