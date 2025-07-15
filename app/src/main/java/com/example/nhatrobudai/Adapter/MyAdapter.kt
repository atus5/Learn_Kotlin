package com.example.nhatrobudai.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.DataModel.Room
import com.example.nhatrobudai.R
import com.example.nhatrobudai.ViewHolder.ViewHolder

class MyAdapter(val list: ArrayList<Room>): RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.room_layout,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val listRoom = list[position]
        holder.txtRoomName.text = listRoom.name
        holder.txtPrice.text = listRoom.price
        holder.txtStatus.text = listRoom.status
        holder.imgRoom.setImageResource(listRoom.imageView)



        holder.btnThue.setOnClickListener {
            // mở dialog ở đây nè ku
            val dialogView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.qr_bank_dialog, null)

            val alertDialog = AlertDialog.Builder(holder.itemView.context)
                .setView(dialogView)
                .create()



            alertDialog.show()
        }
    }

}