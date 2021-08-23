package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ItemClassBinding
import com.suonk.educationapptest.databinding.ItemNewMessageStudentBinding
import com.suonk.educationapptest.databinding.ItemOnlineStudentBinding
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.model.Student

class NewMessageStudentAdapter(
    private var listOfStudentOnline: MutableList<Student>,
    private var activity: Activity
) :
    RecyclerView.Adapter<NewMessageStudentAdapter.ClassViewHolder>() {

    private lateinit var binding: ItemNewMessageStudentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding =
            ItemNewMessageStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val student = listOfStudentOnline[position]
        holder.onBind(student)
    }

    override fun getItemCount(): Int {
        return listOfStudentOnline.size
    }

    class ClassViewHolder(
        private var binding: ItemNewMessageStudentBinding,
        private var activity: Activity
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(student: Student) {
            binding.itemNewMessageImage

            Glide.with(activity)
                .load(student.image_profile_url)
                .centerCrop()
                .into(binding.itemNewMessageImage)

            binding.itemNewMessageTitle.text = student.firstName

            binding.itemNewMessageLayout.tag = student.email
            binding.itemNewMessageLayout.setOnClickListener(activity as View.OnClickListener)
        }
    }
}