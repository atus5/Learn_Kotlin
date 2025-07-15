package com.example.nhatrobudai.ViewHolder

import ChatMessage
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.nhatrobudai.R
import java.text.SimpleDateFormat
import java.util.*



class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
     val textUser: TextView = itemView.findViewById(R.id.textUser)
    val timeUser: TextView = itemView.findViewById(R.id.timeUser) // Thêm nếu có thời gian
    val imgAvatarU: ImageView = itemView.findViewById(R.id.imgAvatarU)
    val txtUserNAME: TextView = itemView.findViewById(R.id.txtUserNAME)


}
