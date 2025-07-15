package com.example.nhatrobudai.SubFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.nhatrobudai.R
import com.example.nhatrobudai.Fragment.SettingFragment


class GioiThieuFragment : Fragment() {

    fun openFragment(fragment: Fragment) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_gioi_thieu, container, false)
        val btnBack = view.findViewById<ImageView>(R.id.btnBack)






        btnBack.setOnClickListener {
            openFragment(SettingFragment())

        }
        return view
    }


}
