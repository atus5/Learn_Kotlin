package com.example.nhatrobudai.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.Adapter.MyAdapter
import com.example.nhatrobudai.DataModel.Room
import com.example.nhatrobudai.R

/**
 * A simple [Fragment] subclass.
 * Use the [AllRoomFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AllRoomFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // 1. Inflate layout
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        // 2. Tìm RecyclerView trong view đã inflate
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        // 3. Tạo danh sách phòng
        val roomList = ArrayList<Room>()
        roomList.add(
            Room(
                "Phòng 101",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho1
            )
        )
        roomList.add(
            Room(
                "Phòng 102",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho2
            )
        )
        roomList.add(
            Room(
                "Phòng 103",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho3
            )
        )
        roomList.add(
            Room(
                "Phòng 104",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho4
            )
        )
        roomList.add(
            Room(
                "Phòng 105",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho5
            )
        )
        roomList.add(
            Room(
                "Phòng 106",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho6
            )
        )
        roomList.add(
            Room(
                "Phòng 201",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho7
            )
        )
        roomList.add(
            Room(
                "Phòng 202",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho8
            )
        )
        roomList.add(
            Room(
                "Phòng 203",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho9
            )
        )
        roomList.add(
            Room(
                "Phòng 204",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho10
            )
        )
        roomList.add(
            Room(
                "Phòng 101",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho1
            )
        )
        roomList.add(
            Room(
                "Phòng 102",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho2
            )
        )
        roomList.add(
            Room(
                "Phòng 101",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho3
            )
        )
        roomList.add(
            Room(
                "Phòng 102",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho4
            )
        )
        roomList.add(
            Room(
                "Phòng 101",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho6
            )
        )
        roomList.add(
            Room(
                "Phòng 102",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho5
            )
        )
        roomList.add(
            Room(
                "Phòng 101",
                "1.000.000 VND/Tháng",
                "Còn trống",
                R.drawable.chuong_cho7
            )
        )
        roomList.add(
            Room(
                "Phòng 102",
                "2.000.000 VND/Tháng",
                "Đã thuê",
                R.drawable.chuong_cho8
            )
        )
        // (Thêm bao nhiêu tuỳ mày)

        // 4. Gán layoutManager và adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        val adapter = MyAdapter(roomList)
        recyclerView.adapter = adapter
        //








        return view
    }
}