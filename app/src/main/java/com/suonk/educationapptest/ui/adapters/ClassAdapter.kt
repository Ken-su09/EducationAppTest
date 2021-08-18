package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suonk.educationapptest.databinding.ItemClasseBinding
import com.suonk.educationapptest.model.SchoolClass

class ClassAdapter(private var listOfClass: MutableList<SchoolClass>) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    private lateinit var binding: ItemClasseBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding = ItemClasseBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classSchool = listOfClass[position]
        holder.onBind(classSchool)
    }

    override fun getItemCount(): Int {
        return listOfClass.size
    }

    class ClassViewHolder(private var itemClassBinding: ItemClasseBinding) :
        RecyclerView.ViewHolder(itemClassBinding.root) {

        fun onBind(schoolClass: SchoolClass) {
            itemClassBinding.itemClassSubject.text = schoolClass.subject
//            itemClassBinding.itemClassImage.setImageDrawable(activity)
            itemClassBinding.itemClassHour.text = schoolClass.hour
            itemClassBinding.itemClassRoom.text = schoolClass.classroom
            itemClassBinding.itemClassTeacher.text = schoolClass.teacher
        }
    }
}