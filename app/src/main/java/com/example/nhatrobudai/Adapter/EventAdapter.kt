package com.example.nhatrobudai.Adapter

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.DataModel.EventData
import com.example.nhatrobudai.R
import com.example.nhatrobudai.ViewHolder.ViewHolderEvent

class eventAdapter(val list: ArrayList<EventData>) : RecyclerView.Adapter<ViewHolderEvent>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderEvent {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.event_layout, parent, false)
        return ViewHolderEvent(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolderEvent, position: Int) {
        val listEvent = list[position]
        holder.title.text = listEvent.title
        holder.subTitle.text = listEvent.subtitle
        holder.imgView.setImageResource(listEvent.imgView)



       holder.btnJoin.setOnClickListener {
           val url = "https://zalo.me/g/hrnhuw201"
           val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
           val context = holder.itemView.context
           context.startActivity(intent)


       }

    }
}