package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityMainBinding
import com.suonk.educationapptest.model.NavigationItemModel
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.ui.adapters.ClassAdapter
import com.suonk.educationapptest.ui.adapters.NavigationRecyclerViewAdapter
import com.suonk.educationapptest.utils.ClickListener
import com.suonk.educationapptest.utils.RecyclerTouchListener

class MainActivity : AppCompatActivity(), View.OnClickListener {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout
    private lateinit var adapter: NavigationRecyclerViewAdapter

    // Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private val toastLength = Toast.LENGTH_SHORT

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        if (auth.currentUser != null) {
            auth.currentUser.let {
                Toast.makeText(this, it!!.displayName, toastLength).show()
            }
        }


        initializeUI()
    }

    //region ========================================== Functions ===========================================

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
                6 -> changeActivity(StudentCardActivity::class.java as Class<Activity>)
                7 -> changeActivity(SettingsActivity::class.java as Class<Activity>)
                8 -> alertDialog()
            }
            updateAdapter(v.tag as Int)
        }
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        initializeDrawerToolbarAndNavigation()
        initializeHorizontalRecyclerView()
        updateAdapter(0)
    }

    private fun initializeDrawerToolbarAndNavigation() {
        drawer = binding.drawerLayout

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        val toggle = object : ActionBarDrawerToggle(
            this, drawer, binding.toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ) {
            override fun onDrawerClosed(drawerView: View) {
                super.onDrawerClosed(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }

            override fun onDrawerOpened(drawerView: View) {
                super.onDrawerOpened(drawerView)
                try {
                    val inputMethodManager =
                        getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
                } catch (e: Exception) {
                    e.stackTrace
                }
            }
        }

        drawer.addDrawerListener(toggle)
        toggle.syncState()

        initializeNavigationRecyclerView()
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

    private fun initializeNavigationRecyclerView() {
        binding.navViewRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.navViewRecyclerView.setHasFixedSize(true)

//        binding.navViewRecyclerView.addOnItemTouchListener(
//            RecyclerTouchListener(
//                this,
//                object : ClickListener {
//                    override fun onClick(view: View, position: Int) {
//                        when (position) {
//                            0 -> {
//                            }
//                            1 -> {
//                            }
//                            2 -> {
//                            }
//                            3 -> {
//                            }
//                            4 -> {
//                            }
//                            5 -> {
//                            }
//                            6 -> changeActivity(StudentCardActivity::class.java as Class<Activity>)
//                            7 -> changeActivity(SettingsActivity::class.java as Class<Activity>)
//                            8 -> alertDialog()
//                        }
//                        updateAdapter(position)
//                    }
//                })
//        )
    }

    private fun createListOfNavItems(): MutableList<NavigationItemModel> {
        return mutableListOf(
            NavigationItemModel(R.drawable.ic_my_dashboard, getString(R.string.dashboard)),
            NavigationItemModel(R.drawable.ic_schedule, getString(R.string.schedule)),
            NavigationItemModel(R.drawable.ic_messaging, getString(R.string.messaging)),
            NavigationItemModel(R.drawable.ic_nav_notifications, getString(R.string.notifications)),
            NavigationItemModel(R.drawable.ic_absences, getString(R.string.non_attendance)),
            NavigationItemModel(R.drawable.ic_folders, getString(R.string.folders)),
            NavigationItemModel(R.drawable.ic_student_card, getString(R.string.student_card)),
            NavigationItemModel(R.drawable.ic_settings, getString(R.string.settings)),
            NavigationItemModel(R.drawable.ic_logout, getString(R.string.logout))
        )
    }

    //endregion

    //region =========================================== Firebase ===========================================


    //endregion

    //region ========================================= AlertDialog ==========================================

    private fun alertDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Déconnexion")
            .setMessage("Êtes-vous sûr de vouloir vous déconnecter ?")
            .setPositiveButton(resources.getString(R.string.alert_dialog_yes)) { dialog, which ->
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            .setNegativeButton(resources.getString(R.string.alert_dialog_no)) { dialog, which ->
                dialog.dismiss()
            }
            .show()
    }


    //endregion

    private fun changeActivity(cls: Class<Activity>) {
        startActivity(Intent(this@MainActivity, cls))
        drawer.closeDrawer(GravityCompat.START)
    }

    private fun updateAdapter(currentPosition: Int) {
        adapter = NavigationRecyclerViewAdapter(createListOfNavItems(), currentPosition, this, this)
        binding.navViewRecyclerView.adapter = adapter
    }

    //endregion
}