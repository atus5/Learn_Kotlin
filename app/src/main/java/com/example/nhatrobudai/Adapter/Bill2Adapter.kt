package com.example.nhatrobudai.Adapter

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.ViewHolder.Bill2ViewHolder
import com.example.nhatrobudai.DataModel.DataBill2
import com.example.nhatrobudai.R
import java.util.UUID

class Bill2Adapter(private val originalList: ArrayList<DataBill2>) :
    RecyclerView.Adapter<Bill2ViewHolder>() {

    private var filteredList = ArrayList<DataBill2>()

    init {
        filteredList.addAll(originalList)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Bill2ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_bill, parent, false)
        return Bill2ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return filteredList.size
    }

    fun getId(): String {
        val randomId = UUID.randomUUID().toString().replace("-","",true)
        val roomId = randomId.substring(0,6).uppercase()
        return "#${roomId}"

    }

    override fun onBindViewHolder(holder: Bill2ViewHolder, position: Int) {
        val item = filteredList[position]
        holder.title2.text = item.title2
        holder.date2.text = item.date2
        holder.check.text = item.check2
        holder.money.text = item.money2
        holder.imgView2.setImageResource(item.imgView2)
        holder.roomId.text = item.roomId

        fun showBankAnhDiKu() {

            val dialogView = LayoutInflater.from(holder.itemView.context)
                .inflate(R.layout.qr_bank_dialog, null)

            val alertDialog = AlertDialog.Builder(holder.itemView.context)
                .setView(dialogView)
                .create()



            alertDialog.show()

        }

        holder.btnDatLai.setOnClickListener {
            showBankAnhDiKu()
        }

        holder.btnBaoCao.setOnClickListener {
            showBankAnhDiKu()
        }
    }

    fun filter(text: String) {
        filteredList.clear()
        if (text.isEmpty()) {
            filteredList.addAll(originalList)
        } else {
            val query = text.lowercase()
            for (item in originalList) {
                if (item.title2?.lowercase()?.contains(query) == true) {
                    filteredList.add(item)
                }
                if (item.roomId?.uppercase()?.contains(query) == true) {
                    filteredList.add(item)
                }

            }
        }
        notifyDataSetChanged()
    }


}
