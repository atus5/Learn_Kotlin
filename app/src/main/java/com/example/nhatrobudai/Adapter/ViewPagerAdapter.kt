package com.example.nhatrobudai.Adapter


import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.nhatrobudai.Fragment.HistoryFragment
import com.example.nhatrobudai.SubFragment.TabBill
import com.example.nhatrobudai.SubFragment.TabHistory

class ViewPagerAdapter(fragmentActivity: HistoryFragment) : FragmentStateAdapter(fragmentActivity) {
    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> TabHistory()
            1 -> TabBill()
            else -> TabHistory()
        }
    }
}