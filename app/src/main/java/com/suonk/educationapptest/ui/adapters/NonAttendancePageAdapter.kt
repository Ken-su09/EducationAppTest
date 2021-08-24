package com.suonk.educationapptest.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.suonk.educationapptest.ui.fragments.*

class NonAttendancePageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 2
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                UnjustifiedFragment()
            }
            1 -> {
                JustifiedFragment()
            }
            else -> {
                UnjustifiedFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Injustifiées"
            }
            1 -> {
                return "Justifiées"
            }
        }
        return super.getPageTitle(position)
    }

}