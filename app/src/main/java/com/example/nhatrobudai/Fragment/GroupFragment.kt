package com.example.nhatrobudai.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nhatrobudai.Adapter.eventAdapter
import com.example.nhatrobudai.DataModel.EventData
import com.example.nhatrobudai.R


class GroupFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_group, container, false)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val list = arrayListOf(
            EventData("J ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Sao ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("J ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Sao ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("J ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Sao ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("J ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Sao ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("J ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Sao ku", "23/06/2025, 19:00", R.drawable.vipvl),
            EventData("Ê ku", "23/06/2025, 19:00", R.drawable.vipvl)
        )

        val adapter = eventAdapter(list)
        recyclerView.adapter = adapter
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)




        return view
    }


}
