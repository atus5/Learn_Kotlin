package com.example.nhatrobudai.ViewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.R
import com.google.android.material.imageview.ShapeableImageView

class ViewHolderEvent(itemView : View) : RecyclerView.ViewHolder(itemView)
{
    val title: TextView = itemView.findViewById(R.id.txtTitle)
    val subTitle: TextView = itemView.findViewById(R.id.txtDate)
    val imgView: ShapeableImageView = itemView.findViewById(R.id.imageView)
    val btnJoin : Button = itemView.findViewById(R.id.btnJoin)
}