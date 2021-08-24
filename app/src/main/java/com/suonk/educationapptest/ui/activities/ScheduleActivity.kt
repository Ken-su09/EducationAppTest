package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityScheduleBinding
import com.suonk.educationapptest.model.ScheduleDay
import com.suonk.educationapptest.ui.adapters.ScheduleDayAdapter
import com.suonk.educationapptest.utils.FunctionsUtils
import java.time.LocalDate
import java.util.*

class ScheduleActivity : AppCompatActivity(), View.OnClickListener {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityScheduleBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScheduleBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initializeUI()
    }

    //region =========================================== Override ===========================================

    override fun onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onClick(v: View?) {
        if (v?.tag != null) {
            when (v.tag as Int) {
                0 -> changeActivity(MainActivity::class.java as Class<Activity>)
                1 -> {
                }
                2 -> changeActivity(GradesActivity::class.java as Class<Activity>)
                3 -> changeActivity(MessagingActivity::class.java as Class<Activity>)
                4 -> {
                }
                5 -> {
                }
                6 -> {
                }
                7 -> changeActivity(StudentCardActivity::class.java as Class<Activity>)
                8 -> changeActivity(SettingsActivity::class.java as Class<Activity>)
                9 -> alertDialog()
            }
            FunctionsUtils.updateAdapter(v.tag as Int, this)
        }
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        drawer = binding.drawerLayout
        toolbar = binding.toolbar

        FunctionsUtils.initializeDrawerToolbarAndNavigation(
            drawer,
            toolbar,
            this,
            binding.navViewRecyclerView,
            1
        )

        initializeHorizontalRecyclerView()

        binding.userViewProfile.setOnClickListener {
            changeActivity(StudentProfileActivity::class.java as Class<Activity>)
        }

        getTodayDay()
    }

    private fun initializeHorizontalRecyclerView() {
        val calendar = Calendar.getInstance()

        binding.todayClassesRecyclerViewHorizontal.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.todayClassesRecyclerViewHorizontal.adapter =
            ScheduleDayAdapter(createListOfDays(), calendar.get(Calendar.DAY_OF_MONTH), this)
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
        getUserFromFirestore()
    }

    private fun getUserFromFirestore() {
        val userCollectionReference = Firebase.firestore.collection("Users")
        userCollectionReference.get().addOnSuccessListener { users ->
            for (user in users) {
                if (auth.currentUser!!.email == user.data["email"]) {
                    val data = user.data

                    binding.userProfileName.text =
                        "${data["firstName"]} ${data["lastName"]}"
                    data["firstName"]
                    data["email"]
                    data["lastName"]
                    data["birth_year"]
                    data["year_school"]

                    Glide.with(this)
                        .load(data["image_profile_url"])
                        .centerCrop()
                        .into(binding.userProfileImage)
                    break
                }
            }
        }
    }

    //endregion

    //region =========================================  ==========================================

    private fun alertDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Déconnexion")
            .setMessage("Êtes-vous sûr de vouloir vous déconnecter ?")
            .setPositiveButton(resources.getString(R.string.alert_dialog_yes)) { dialog, which ->
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
            .setNegativeButton(resources.getString(R.string.alert_dialog_no)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }

    private fun createListOfDays(): MutableList<ScheduleDay> {
        val day1 = ScheduleDay("Dimanche", "1")
        val day2 = ScheduleDay("Lundi", "2")
        val day3 = ScheduleDay("Mardi", "3")
        val day4 = ScheduleDay("Mercredi", "4")
        val day5 = ScheduleDay("Jeudi", "5")
        val day6 = ScheduleDay("Vendredi", "6")
        val day7 = ScheduleDay("Samedi", "7")
        val day8 = ScheduleDay("Dimanche", "8")

        val day9 = ScheduleDay("Lundi", "9")
        val day10 = ScheduleDay("Mardi", "10")
        val day11 = ScheduleDay("Mercredi", "11")
        val day12 = ScheduleDay("Jeudi", "12")
        val day13 = ScheduleDay("Vendredi", "13")
        val day14 = ScheduleDay("Samedi", "14")
        val day15 = ScheduleDay("Dimanche", "15")

        val day16 = ScheduleDay("Lundi", "16")
        val day17 = ScheduleDay("Mardi", "17")
        val day18 = ScheduleDay("Mercredi", "18")
        val day19 = ScheduleDay("Jeudi", "19")
        val day20 = ScheduleDay("Vendredi", "20")
        val day21 = ScheduleDay("Samedi", "21")
        val day22 = ScheduleDay("Dimanche", "22")

        val day23 = ScheduleDay("Lundi", "23")
        val day24 = ScheduleDay("Mardi", "24")
        val day25 = ScheduleDay("Mercredi", "25")
        val day26 = ScheduleDay("Jeudi", "26")
        val day27 = ScheduleDay("Vendredi", "27")
        val day28 = ScheduleDay("Samedi", "28")
        val day29 = ScheduleDay("Dimanche", "29")

        val day30 = ScheduleDay("Lundi", "30")
        val day31 = ScheduleDay("Mardi", "31")

        return mutableListOf(
            day1,
            day2,
            day3,
            day4,
            day5,
            day6,
            day7,
            day8,
            day9,
            day10,
            day11,
            day12,
            day13,
            day14,
            day15,
            day16,
            day17,
            day18,
            day19,
            day20,
            day21,
            day22,
            day23,
            day24,
            day25,
            day26,
            day27,
            day28,
            day29,
            day30,
            day31
        )
    }

    private fun getTodayDay() {
        val calendar = Calendar.getInstance()
        val dayText = calendar.get(Calendar.DAY_OF_WEEK)
        val dayNumb = calendar.get(Calendar.DAY_OF_MONTH)
        val dayMonth = calendar.get(Calendar.MONTH)

        val day = when (dayText) {
            2 -> {
                "Lundi"
            }
            3 -> {
                "Mardi"
            }
            4 -> {
                "Mercredi"
            }
            5 -> {
                "Jeudi"
            }
            6 -> {
                "Vendredi"
            }
            7 -> {
                "Samedi"
            }
            7 -> {
                "Dimanche"
            }
            else -> {
                "Lundi"
            }
        }

        val month = when (dayMonth) {
            0 -> {
                "Janvier"
            }
            1 -> {
                "Février"
            }
            2 -> {
                "Mars"
            }
            3 -> {
                "Avril"
            }
            4 -> {
                "Mai"
            }
            5 -> {
                "Juin"
            }
            6 -> {
                "Juillet"
            }
            7 -> {
                "Aout"
            }
            8 -> {
                "Septembre"
            }
            9 -> {
                "Octobre"
            }
            10 -> {
                "Novembre"
            }
            11 -> {
                "Décembre"
            }
            else -> {
                "Avril"
            }
        }

        binding.scheduleTodayValue.text = "$day, $dayNumb $month"
    }

    private fun changeActivity(cls: Class<Activity>) {
        startActivity(Intent(this@ScheduleActivity, cls))
        drawer.closeDrawer(GravityCompat.START)
    }

    //endregion
}