package com.suonk.educationapptest.ui.adapters

import android.app.Activity
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ItemNavigationBinding
import com.suonk.educationapptest.databinding.ItemNonAttendanceBinding
import com.suonk.educationapptest.model.NavigationItemModel
import com.suonk.educationapptest.model.NonAttendance

class NonAttendanceRecyclerViewAdapter(
    private var items: MutableList<NonAttendance>
) : RecyclerView.Adapter<NonAttendanceRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: ItemNonAttendanceBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemNonAttendanceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(
        private var binding: ItemNonAttendanceBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(nonAttendance: NonAttendance) {
            binding.itemNonAttendanceTitle.text = nonAttendance.title
            binding.itemNonAttendanceDay.text = nonAttendance.day
            binding.itemNonAttendanceComments.text = nonAttendance.comment
        }
    }
}