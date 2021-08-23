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
import com.suonk.educationapptest.databinding.ActivityMainBinding
import com.suonk.educationapptest.databinding.ActivityMessagingBinding
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.model.Student
import com.suonk.educationapptest.ui.adapters.ClassAdapter
import com.suonk.educationapptest.ui.adapters.OnlineStudentsAdapter
import com.suonk.educationapptest.utils.FunctionsUtils
import com.suonk.educationapptest.utils.FunctionsUtils.initializeDrawerToolbarAndNavigation

class MessagingActivity : AppCompatActivity(), View.OnClickListener {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityMessagingBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar

    // Firebase
    private lateinit var auth: FirebaseAuth

    // Model
    private var listOfStudentsOnline = mutableListOf<Student>()

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagingBinding.inflate(layoutInflater)
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
            if (v.id == R.id.item_online_student_layout) {
                Log.i("onClick", "${v.tag}")
                val intent = Intent(this@MessagingActivity, MessagingDetailActivity::class.java)
                intent.putExtra("studentOnline", v.tag as String)
                startActivity(intent)
            } else {
                when (v.tag as Int) {
                    0 -> changeActivity(MainActivity::class.java as Class<Activity>)
                    1 -> {
                    }
                    2 -> {
                    }
                    3 -> {
                    }
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
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        drawer = binding.drawerLayout
        toolbar = binding.toolbar

        initializeDrawerToolbarAndNavigation(
            drawer,
            toolbar,
            this,
            binding.navViewRecyclerView,
            3
        )

        binding.userViewProfile.setOnClickListener {
            changeActivity(StudentProfileActivity::class.java as Class<Activity>)
        }
    }

    private fun initializeRecyclerStudentsOnline(listOfStuds: MutableList<Student>) {
        binding.onlineStudentsRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.onlineStudentsRecyclerView.adapter =
            OnlineStudentsAdapter(listOfStuds, this)
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
        getUserFromFirestore()
        getListOfStudentsOnline()
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

    private fun getListOfStudentsOnline() {
        val userCollectionReference = Firebase.firestore.collection("Users")
        userCollectionReference.get().addOnSuccessListener { Students ->
            for (studentOnLine in Students) {
                val ifOnline = studentOnLine.data["online"] as Boolean
                if (auth.currentUser!!.email != studentOnLine.data["email"] && ifOnline) {
                    Log.i("getListOfStudentsOnline", "${studentOnLine.data["firstName"]}")
                    val studentOnline = Student(
                        studentOnLine.data["firstName"].toString(),
                        studentOnLine.data["lastName"].toString(),
                        studentOnLine.data["email"].toString(),
                        studentOnLine.data["birth_year"] as Long,
                        studentOnLine.data["image_profile_url"].toString(),
                        studentOnLine.data["phone_number"].toString(),
                        studentOnLine.data["year_school"] as Long,
                        studentOnLine.data["online"] as Boolean,
                        1
                    )
                    listOfStudentsOnline.add(studentOnline)
                }
            }
            initializeRecyclerStudentsOnline(listOfStudentsOnline)
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

    private fun changeActivity(cls: Class<Activity>) {
        startActivity(Intent(this@MessagingActivity, cls))
        drawer.closeDrawer(GravityCompat.START)
    }

    //endregion
}