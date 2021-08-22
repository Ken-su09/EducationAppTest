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
import com.suonk.educationapptest.model.NavigationItemModel

class NavigationRecyclerViewAdapter(
    private var items: MutableList<NavigationItemModel>,
    private var currentPos: Int,
    private var activity: Activity
) : RecyclerView.Adapter<NavigationRecyclerViewAdapter.ViewHolder>() {

    private lateinit var binding: ItemNavigationBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding =
            ItemNavigationBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, activity)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val navigationItemModel = items[position]
        holder.onBind(navigationItemModel)

        if (currentPos == position) {
            binding.navItemLayout.background =
                holder.getDraw(R.color.backgroundColorDarkTransparent)
        } else {
            binding.navItemLayout.background = holder.getDraw(R.color.transparent)
        }

        binding.navItemLayout.tag = position
        binding.navItemLayout.setOnClickListener(activity as View.OnClickListener)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(
        private var itemNavigationBinding: ItemNavigationBinding,
        private var activity: Activity
    ) :
        RecyclerView.ViewHolder(itemNavigationBinding.root) {

        fun getDraw(resources: Int): Drawable? {
            return AppCompatResources.getDrawable(activity, resources)
        }

        private fun checkIfTitleIsNotifications(title: String): Boolean {
            return title == activity.getString(R.string.notifications) || title == activity.getString(
                R.string.messaging
            )
        }

        private fun checkIfTitleNeedDivider(title: String): Boolean {
            if (title == activity.getString(R.string.schedule)) {
                itemNavigationBinding.dividerTitle.text = activity.getString(R.string.studies)
                return true
            } else if (title == activity.getString(R.string.settings)) {
                itemNavigationBinding.dividerTitle.text = activity.getString(R.string.other)
                return true
            } else {
                return false
            }
        }

        fun onBind(navigationItemModel: NavigationItemModel) {
            itemNavigationBinding.navIcon.setImageDrawable(getDraw(navigationItemModel.icon))
            itemNavigationBinding.navTitle.text = navigationItemModel.title
            itemNavigationBinding.navNotifText.text = "1"

            if (checkIfTitleIsNotifications(navigationItemModel.title)) {
                itemNavigationBinding.navNotificationLayout.visibility = View.VISIBLE
            }

            if (checkIfTitleNeedDivider(navigationItemModel.title)) {
                itemNavigationBinding.dividerLayout.visibility = View.VISIBLE
            }

        }
    }
}