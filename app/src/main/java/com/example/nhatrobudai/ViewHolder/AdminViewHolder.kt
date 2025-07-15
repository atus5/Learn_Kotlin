package com.example.nhatrobudai.ViewHolder

import ChatMessage
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.nhatrobudai.R
import java.text.SimpleDateFormat
import java.util.*


class AdminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val textAdmin: TextView = itemView.findViewById(R.id.textAdmin)
    val timeAdmin: TextView = itemView.findViewById(R.id.timeAdmin)
    val imgAvatarA: ImageView = itemView.findViewById(R.id.imgAvatarA)


}
