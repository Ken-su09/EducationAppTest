package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ItemClassBinding
import com.suonk.educationapptest.databinding.ItemOnlineStudentBinding
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.model.Student

class OnlineStudentsAdapter(
    private var listOfStudentOnline: MutableList<Student>,
    private var activity: Activity
) :
    RecyclerView.Adapter<OnlineStudentsAdapter.ClassViewHolder>() {

    private lateinit var binding: ItemOnlineStudentBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding =
            ItemOnlineStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
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
        private var itemOnlineStudentBinding: ItemOnlineStudentBinding,
        private var activity: Activity
    ) :
        RecyclerView.ViewHolder(itemOnlineStudentBinding.root) {

        fun onBind(student: Student) {
            itemOnlineStudentBinding.itemOnlineStudentImage

            Glide.with(activity)
                .load(student.image_profile_url)
                .centerCrop()
                .into(itemOnlineStudentBinding.itemOnlineStudentImage)

            itemOnlineStudentBinding.itemClassSubjectTitle.text = student.firstName
        }
    }
}