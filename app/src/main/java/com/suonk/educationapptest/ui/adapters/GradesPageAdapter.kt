package com.suonk.educationapptest.ui.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.suonk.educationapptest.ui.fragments.Ing1Fragment
import com.suonk.educationapptest.ui.fragments.Prep1Fragment
import com.suonk.educationapptest.ui.fragments.Prep2Fragment

class GradesPageAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> {
                Prep1Fragment()
            }
            1 -> {
                Prep2Fragment()
            }
            2 -> {
                Ing1Fragment()
            }
            else -> {
                Prep1Fragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when (position) {
            0 -> {
                return "Prep 1"
            }
            1 -> {
                return "Prep 2"
            }
            2 -> {
                return "Ing 1"
            }
        }
        return super.getPageTitle(position)
    }

}