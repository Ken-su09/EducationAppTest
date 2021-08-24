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
            CustomSpinnerAdapter(context, getListOfGrades());
        return binding.root
    }

    private fun getListOfGrades(): MutableList<Module> {

        val module1 = Module("Maths", "6", "16")
        val module2 = Module("Physique", "2", "4")
        val module3 = Module("Algo", "3", "12")
        val module4 = Module("Anglais", "4", "15")
        val module5 = Module("Programmation", "5", "18")
        val module6 = Module("Electronique", "6", "6")

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