package com.suonk.educationapptest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.FragmentJustifiedBinding
import com.suonk.educationapptest.databinding.FragmentPrep1Binding
import com.suonk.educationapptest.databinding.FragmentUnjustifiedBinding
import com.suonk.educationapptest.model.Module
import com.suonk.educationapptest.model.NonAttendance
import com.suonk.educationapptest.model.ScheduleDay
import com.suonk.educationapptest.ui.adapters.CustomSpinnerAdapter
import com.suonk.educationapptest.ui.adapters.NonAttendanceRecyclerViewAdapter

class JustifiedFragment : Fragment() {

    lateinit var binding: FragmentJustifiedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJustifiedBinding.inflate(inflater, container, false)
        binding.justifiedRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.justifiedRecyclerView.adapter =
            NonAttendanceRecyclerViewAdapter(getListOfNonAttendance())

        return binding.root
    }

    private fun getListOfNonAttendance(): MutableList<NonAttendance> {

        val nonAttendance1 = NonAttendance(
            "Anglais",
            "Vendredi 20 août 2021 à 8h30",
            "Certificat médical fourni"
        )
        val nonAttendance2 = NonAttendance(
            "Physique",
            "Vendredi 20 août 2021 à 10h30",
            "Certificat médical fourni"
        )
        val nonAttendance3 = NonAttendance(
            "Algo",
            "Vendredi 20 août 2021 à 14h30",
            "Certificat médical fourni"
        )

        return mutableListOf(
            nonAttendance1,
            nonAttendance2,
            nonAttendance3,
        )
    }
}