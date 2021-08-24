package com.suonk.educationapptest.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.FragmentPrep1Binding
import com.suonk.educationapptest.model.Module
import com.suonk.educationapptest.model.ScheduleDay
import com.suonk.educationapptest.ui.adapters.CustomSpinnerAdapter

class Prep1Fragment : Fragment() {

    lateinit var binding: FragmentPrep1Binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPrep1Binding.inflate(inflater, container, false)
        binding.semester1Spinner.adapter =
            CustomSpinnerAdapter(context, getListOfGradesSemester1())

        binding.semester2Spinner.adapter =
            CustomSpinnerAdapter(context, getListOfGradesSemester2())
        return binding.root
    }

    private fun getListOfGradesSemester1(): MutableList<Module> {

        val module1 = Module("Maths", "6", "16")
        val module2 = Module("Physique", "1", "4")
        val module3 = Module("Algo", "5", "12")
        val module4 = Module("Anglais", "4", "15")
        val module5 = Module("Programmation", "6", "18")
        val module6 = Module("Electronique", "2", "6")

        return mutableListOf(
            module1,
            module2,
            module3,
            module4,
            module5,
            module6,
        )
    }

    private fun getListOfGradesSemester2(): MutableList<Module> {

        val module1 = Module("Maths", "6", "18")
        val module2 = Module("Physique", "1", "10")
        val module3 = Module("Algo", "5", "14")
        val module4 = Module("Anglais", "4", "11")
        val module5 = Module("Programmation", "6", "15")
        val module6 = Module("Electronique", "2", "2")

        return mutableListOf(
            module1,
            module2,
            module3,
            module4,
            module5,
            module6,
        )
    }
}