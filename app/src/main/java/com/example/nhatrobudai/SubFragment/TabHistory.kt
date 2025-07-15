package com.example.nhatrobudai.SubFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.Adapter.BillAdapter
import com.example.nhatrobudai.DataModel.BillData
import com.example.nhatrobudai.R


class TabHistory : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tabhistory, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val list1 = arrayListOf(
            BillData(
                "Hóa đơn tháng 04/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),
            BillData(
                "Hóa đơn tháng 05/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),
            BillData(
                "Hóa đơn tháng 0/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),BillData(
                "Hóa đơn tháng 07/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),
            BillData(
                "Hóa đơn tháng 08/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),BillData(
                "Hóa đơn tháng 09/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),BillData(
                "Hóa đơn tháng 10/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),BillData(
                "Hóa đơn tháng 11/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            ),BillData(
                "Hóa đơn tháng 12/2024",
                "Hạn: 16/06/2025",
                "Đã thanh toán",
                "5.000.000đ"
            )
        )
        val adapter = BillAdapter(list1)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        return view


    }


}