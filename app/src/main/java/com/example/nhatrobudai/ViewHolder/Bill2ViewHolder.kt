package com.example.nhatrobudai.ViewHolder

import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.R

class Bill2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    val title2 = itemView.findViewById<TextView>(R.id.txtTitle2)
    val date2 = itemView.findViewById<TextView>(R.id.txtDate2)
    val check = itemView.findViewById<TextView>(R.id.txtCheck)
    val money = itemView.findViewById<TextView>(R.id.textView3)
    val imgView2 = itemView.findViewById<ImageView>(R.id.imgView2)
    val btnDatLai : Button = itemView.findViewById(R.id.btnDatLai)
    val btnBaoCao : Button = itemView.findViewById(R.id.btnBaoCao)
    val roomId : TextView = itemView.findViewById(R.id.roomId)


}