package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ItemClassBinding
import com.suonk.educationapptest.model.SchoolClass

class ClassAdapter(
    private var listOfClass: MutableList<SchoolClass>,
    private var activity: Activity
) :
    RecyclerView.Adapter<ClassAdapter.ClassViewHolder>() {

    private lateinit var binding: ItemClassBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding = ItemClassBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val classSchool = listOfClass[position]
        holder.onBind(classSchool)
    }

    override fun getItemCount(): Int {
        return listOfClass.size
    }

    class ClassViewHolder(
        private var itemClassBinding: ItemClassBinding,
        private var activity: Activity
    ) :
        RecyclerView.ViewHolder(itemClassBinding.root) {

        fun onBind(schoolClass: SchoolClass) {
            itemClassBinding.itemClassSubject.text = schoolClass.subject
//            itemClassBinding.itemClassImage.setImageDrawable(activity.getDrawable(R.drawable.))
            itemClassBinding.itemClassHour.text = schoolClass.hour
            itemClassBinding.itemClassRoom.text = schoolClass.classroom
            itemClassBinding.itemClassTeacher.text = schoolClass.teacher

            val background = getClassBackground()
            itemClassBinding.itemClassLayout.background =
                AppCompatResources.getDrawable(activity, background)

            if (background == R.drawable.ic_class_background_black_border) {
                itemClassBinding.itemClassSubject.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassSubjectTitle.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassHour.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassHourTitle.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassRoom.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassTeacher.setTextColor(activity.resources.getColor(R.color.black))
                itemClassBinding.itemClassTeacherTitle.setTextColor(activity.resources.getColor(R.color.black))
            }
        }

        private fun getClassBackground(): Int {
            return when ((0..4).random()) {
                0 -> R.drawable.ic_class_background_blue
                1 -> R.drawable.ic_class_background_black_border
                2 -> R.drawable.ic_class_background_green
                3 -> R.drawable.ic_class_background_black
                4 -> R.drawable.ic_class_background_red
                else -> R.drawable.ic_class_background_blue
            }
        }
    }
}