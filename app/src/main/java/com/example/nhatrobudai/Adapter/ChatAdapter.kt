package com.example.nhatrobudai.Adapter

import ChatMessage
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity.MODE_PRIVATE
import androidx.recyclerview.widget.RecyclerView

import com.example.nhatrobudai.R
import com.example.nhatrobudai.ViewHolder.AdminViewHolder
import com.example.nhatrobudai.ViewHolder.UserViewHolder
import com.google.firebase.database.FirebaseDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.TimeZone

class ChatAdapter(private val messages: List<ChatMessage>, private val currentUser: String) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val VIEW_TYPE_USER = 1
        private const val VIEW_TYPE_ADMIN = 2
    }

    override fun getItemViewType(position: Int): Int {
        val message = messages[position]
        return if (message.sender == "admin") {
            VIEW_TYPE_ADMIN // Admin layout
        } else {
            VIEW_TYPE_USER // User layout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return if (viewType == VIEW_TYPE_ADMIN) {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_admin, parent, false)
            AdminViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_message_user, parent, false)
            UserViewHolder(view)
        }
    }

    override fun getItemCount(): Int = messages.size


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        fun formatTimeVN(timeMillis: Long): String {
            val sdf = SimpleDateFormat("HH:mm dd/MM/yyyy", Locale.getDefault())
            sdf.timeZone = TimeZone.getTimeZone("Asia/Ho_Chi_Minh")
            return sdf.format(Date(timeMillis))
        }
          val message = messages[position]
        val time = formatTimeVN(message.timestamp)


        if (holder is AdminViewHolder) {
            holder.textAdmin.text = message.text
            holder.timeAdmin.text = time
            holder.imgAvatarA.setImageResource(R.drawable.gau_hai)
        } else if (holder is UserViewHolder) {
            holder.textUser.text = message.text
            holder.timeUser.text = time
            holder.imgAvatarU.setImageResource(R.drawable.doro)
            holder.txtUserNAME.text = message.sender
        }

    }
}