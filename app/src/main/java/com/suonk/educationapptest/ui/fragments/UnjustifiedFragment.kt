package com.suonk.educationapptest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.FragmentPrep1Binding
import com.suonk.educationapptest.databinding.FragmentUnjustifiedBinding
import com.suonk.educationapptest.model.Module
import com.suonk.educationapptest.model.NonAttendance
import com.suonk.educationapptest.model.ScheduleDay
import com.suonk.educationapptest.ui.adapters.ClassAdapter
import com.suonk.educationapptest.ui.adapters.CustomSpinnerAdapter
import com.suonk.educationapptest.ui.adapters.NonAttendanceRecyclerViewAdapter

class UnjustifiedFragment : Fragment() {

    lateinit var binding: FragmentUnjustifiedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUnjustifiedBinding.inflate(inflater, container, false)
        binding.unjustifiedRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.unjustifiedRecyclerView.adapter =
            NonAttendanceRecyclerViewAdapter(getListOfNonAttendance())

        return binding.root
    }

    private fun getListOfNonAttendance(): MutableList<NonAttendance> {
        val nonAttendance1 = NonAttendance(
            "Maths",
            "Lundi 23 août 2021 à 8h30",
            "L'étudiant ne s'est pas présenté en cours."
        )
        val nonAttendance2 = NonAttendance(
            "Physique",
            "Lundi 23 août 2021 à 10h30",
            "L'étudiant ne s'est pas présenté en cours."
        )
        val nonAttendance3 = NonAttendance(
            "Algo",
            "Mardi 24 août 2021 à 15h30",
            "L'étudiant ne s'est pas présenté en cours."
        )

        return mutableListOf(
            nonAttendance1,
            nonAttendance2,
            nonAttendance3,
        )
    }
}