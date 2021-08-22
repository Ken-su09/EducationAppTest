package com.suonk.educationapptest.utils

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.model.NavigationItemModel
import com.suonk.educationapptest.ui.adapters.NavigationRecyclerViewAdapter
import kotlinx.coroutines.flow.merge

object FunctionsUtils {

    //region ======================================= DrawerNavigation =======================================

    fun createListOfNavItems(activity: Activity) = mutableListOf(
        NavigationItemModel(R.drawable.ic_my_dashboard, activity.getString(R.string.dashboard)),
        NavigationItemModel(R.drawable.ic_schedule, activity.getString(R.string.schedule)),
        NavigationItemModel(R.drawable.ic_exams, activity.getString(R.string.notes)),
        NavigationItemModel(R.drawable.ic_messaging, activity.getString(R.string.messaging)),
        NavigationItemModel(
            R.drawable.ic_nav_notifications,
            activity.getString(R.string.notifications)
        ),
        NavigationItemModel(
            R.drawable.ic_absences,
            activity.getString(R.string.non_attendance)
        ),
        NavigationItemModel(R.drawable.ic_folders, activity.getString(R.string.folders)),
        NavigationItemModel(
            R.drawable.ic_student_card,
            activity.getString(R.string.student_card)
        ),
        NavigationItemModel(R.drawable.ic_settings, activity.getString(R.string.settings)),
        NavigationItemModel(R.drawable.ic_logout, activity.getString(R.string.logout))
    )

    fun updateAdapter(currentPosition: Int, activity: Activity) =
        NavigationRecyclerViewAdapter(createListOfNavItems(activity), currentPosition, activity)

    fun initializeDrawerToolbarAndNavigation(
        drawerLayout: DrawerLayout, toolbar: Toolbar,
        activity: AppCompatActivity, recyclerView: RecyclerView, position: Int
    ) {
        // Toolbar
        activity.setSupportActionBar(toolbar)
        activity.supportActionBar!!.setDisplayShowTitleEnabled(false)

        val toggle = object : ActionBarDrawerToggle(
            activity, drawerLayout, toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        activity.currentFocus?.windowToken,
                        0
                    )
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(
                        activity.currentFocus!!.windowToken,
                        0
                    )
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }

        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        recyclerView.apply {
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
            setHasFixedSize(true)
            adapter = updateAdapter(position, activity)
        }
    }

    //endregion

    //region =========================================== Firebase ===========================================


    //endregion
}