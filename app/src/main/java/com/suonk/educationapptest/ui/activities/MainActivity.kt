package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityMainBinding
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.ui.adapters.ClassAdapter
import com.suonk.educationapptest.utils.FunctionsUtils.initializeDrawerToolbarAndNavigation
import com.suonk.educationapptest.utils.FunctionsUtils.updateAdapter

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var toolbar: Toolbar

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
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
                0 -> {
                }
                1 -> changeActivity(ScheduleActivity::class.java as Class<Activity>)
                2 -> {
                }
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
            updateAdapter(v.tag as Int, this)
        }
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        drawer = binding.drawerLayout
        toolbar = binding.toolbar

        initializeDrawerToolbarAndNavigation(drawer, toolbar, this, binding.navViewRecyclerView, 0)
        initializeHorizontalRecyclerView()

        binding.userViewProfile.setOnClickListener {
            changeActivity(StudentProfileActivity::class.java as Class<Activity>)
        }
    }

    private fun initializeHorizontalRecyclerView() {
        binding.todayClassesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.todayClassesRecyclerView.adapter = ClassAdapter(createListOfSchoolClass(), this)
    }

    private fun createListOfSchoolClass(): MutableList<SchoolClass> {
        val listOfSchoolClass = mutableListOf<SchoolClass>()

        val schoolClass1 = SchoolClass("Maths", "", "8h - 10h", "3C", "Mr Barré", 1)
        val schoolClass2 = SchoolClass("Education Civique", "", "10h - 11h", "3C", "Mr Onizuka", 2)
        val schoolClass3 = SchoolClass("Japonais", "", "11h - 12h", "5B", "Mlle Fuyutsuki", 3)
        val schoolClass4 = SchoolClass("Sous directeur", "", "8h - 10h", "3C", "Mr Uchiyamada", 4)
//        val schoolClass5 = SchoolClass("Maths", "", "8h - 10h", "3C", "Mr Onizuka", 5)
//        val schoolClass6 = SchoolClass("Maths", "", "8h - 10h", "3C", "Mr Onizuka", 6)

        listOfSchoolClass.add(schoolClass1)
        listOfSchoolClass.add(schoolClass2)
        listOfSchoolClass.add(schoolClass3)
        listOfSchoolClass.add(schoolClass4)

        return listOfSchoolClass
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
        getUserFromFirestore()
//        getTodaySchoolClassFromFirestore()
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

    private fun getTodaySchoolClassFromFirestore() {
        val schoolClassCollectionReference = Firebase.firestore.collection("SchoolClass")
        val userCollectionReference = Firebase.firestore.collection("Users")

        userCollectionReference.get().addOnSuccessListener { users ->
            for (user in users) {
                if (auth.currentUser!!.email == user.data["email"]) {
                    schoolClassCollectionReference.get().addOnSuccessListener { SchoolClass ->
                        for (schoolClass in SchoolClass) {
                            val dataSchoolClass = schoolClass.data

                            if (dataSchoolClass["className"] == user.data["className"]) {

                                Log.i("SchoolClass/module", "${dataSchoolClass}")

                                Firebase.firestore.collection("SchoolClass").document("module")
                                    .get()
                                    .addOnSuccessListener { module ->
//                                        Log.i("SchoolClass/module", "${module["professor_name"]}")
//                                        for (module in modules) {
//                                            Log.i("SchoolClass/module", "${module["name"]}")
//                                        }
                                    }
                                break
                            }
                        }
                    }

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

    private fun changeActivity(cls: Class<Activity>) {
        startActivity(Intent(this@MainActivity, cls))
        drawer.closeDrawer(GravityCompat.START)
    }

    //endregion
}