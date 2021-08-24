package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityStudentCardBinding
import com.suonk.educationapptest.utils.FunctionsUtils

class StudentCardActivity : AppCompatActivity(), View.OnClickListener {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityStudentCardBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentCardBinding.inflate(layoutInflater)
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
                1 -> changeActivity(ScheduleActivity::class.java as Class<Activity>)
                2 -> changeActivity(GradesActivity::class.java as Class<Activity>)
                3 -> changeActivity(MessagingActivity::class.java as Class<Activity>)
                4 -> {
                }
                5 -> changeActivity(NonAttendanceActivity::class.java as Class<Activity>)
                6 -> {
                }
                7 -> {}
                8 -> changeActivity(SettingsActivity::class.java as Class<Activity>)
                9 -> alertDialog()
            }
            FunctionsUtils.updateAdapter(v.tag as Int, this)
        }
    }

    //endregion

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        drawer = binding.drawerLayout
        toolbar = binding.toolbar

        FunctionsUtils.initializeDrawerToolbarAndNavigation(
            drawer,
            toolbar,
            this,
            binding.navViewRecyclerView,
            7
        )

        binding.userViewProfile.setOnClickListener {
            changeActivity(StudentProfileActivity::class.java as Class<Activity>)
        }
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
                    binding.studentCardProfileName.text =
                        "${data["firstName"]} ${data["lastName"]}"

                    Glide.with(this)
                        .load(data["image_profile_url"])
                        .centerCrop()
                        .into(binding.userProfileImage)

                    Glide.with(this)
                        .load(data["image_profile_url"])
                        .centerCrop()
                        .into(binding.studentCardProfileImage)

                    when (data["className"].toString()) {
                        "Prep1" -> binding.userProfileType.text = "Année Préparatoire 1"
                        "Prep2" -> binding.userProfileType.text = "Année Préparatoire 2"
                        "ING1" -> binding.userProfileType.text = "Année Ingénieur 1"
                        "ING2" -> binding.userProfileType.text = "Année Ingénieur 2"
                        "ING3" -> binding.userProfileType.text = "Année Ingénieur 3"
                    }

                    break
                }
            }
        }

    }

    //endregion

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
        startActivity(Intent(this@StudentCardActivity, cls))
        drawer.closeDrawer(GravityCompat.START)
    }
}