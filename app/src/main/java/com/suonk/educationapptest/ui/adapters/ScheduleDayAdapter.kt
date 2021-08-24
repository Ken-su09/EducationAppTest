package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ItemClassBinding
import com.suonk.educationapptest.databinding.ItemScheduleDayBinding
import com.suonk.educationapptest.model.ScheduleDay
import com.suonk.educationapptest.model.SchoolClass
import java.util.*

class ScheduleDayAdapter(
    private var listOfScheduleDay: MutableList<ScheduleDay>,
    private var currentPos: Int,
    private var activity: Activity
) :
    RecyclerView.Adapter<ScheduleDayAdapter.ClassViewHolder>() {

    private lateinit var binding: ItemScheduleDayBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClassViewHolder {
        binding = ItemScheduleDayBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClassViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ClassViewHolder, position: Int) {
        val scheduleDay = listOfScheduleDay[position]
        holder.onBind(scheduleDay)

        if (currentPos == position) {

        } else {
            binding.itemScheduleDayLayout.background =
                holder.getDraw(R.drawable.pressed_rounded_item_animation)
        }
    }

    override fun getItemCount(): Int {
        return listOfScheduleDay.size
    }

    class ClassViewHolder(
        private var binding: ItemScheduleDayBinding,
        private var activity: Activity
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun getDraw(resources: Int): Drawable? {
            return AppCompatResources.getDrawable(activity, resources)
        }

//        private fun getTodayDay() {
//            val calendar = Calendar.getInstance()
//            val dayText = calendar.get(Calendar.DAY_OF_WEEK)
//            val dayNumb = calendar.get(Calendar.DAY_OF_MONTH)
//            val dayMonth = calendar.get(Calendar.MONTH)
//
//            binding.scheduleTodayValue.text = "$dayText, $dayNumb $dayMonth"
//        }

        fun onBind(scheduleDay: ScheduleDay) {
//            binding.itemScheduleDayLayout.background = getDraw(R.drawable.pressed_rounded_item_pressed)

            binding.itemScheduleDayLayout
            binding.itemScheduleDayNumber.text = scheduleDay.dayNumb
            binding.itemScheduleDayText.text = scheduleDay.dayText.take(3)
        }
    }
}