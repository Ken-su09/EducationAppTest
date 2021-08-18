package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityLoginBinding
import com.suonk.educationapptest.databinding.ActivityMainBinding
import com.suonk.educationapptest.model.SchoolClass
import com.suonk.educationapptest.ui.adapters.ClassAdapter

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawer: DrawerLayout

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

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.dashboard -> {
            }
            R.id.schedule -> {
            }
            R.id.school_notes -> {
            }
            R.id.school_messaging -> {
            }
            R.id.school_folder -> {
            }
            R.id.school_absences -> {
            }
            R.id.school_student_card -> {
            }
            R.id.nav_settings -> {
            }
            R.id.nav_logout -> {
                auth.signOut()
                startActivity(Intent(this, LoginActivity::class.java))
            }
        }
        return true
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        initializeDrawerToolbarAndNavigation()
        initializeRecyclerView()
    }

    private fun initializeDrawerToolbarAndNavigation() {
        drawer = binding.drawerLayout

        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)

        // Navigation View and Drawer
        binding.navView.bringToFront()
        val toggle = ActionBarDrawerToggle(
            this, drawer, binding.toolbar, R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer.addDrawerListener(toggle)
        toggle.syncState()
        binding.navView.setNavigationItemSelectedListener(this)


    }

    private fun initializeRecyclerView() {
        binding.todayClassesRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.todayClassesRecyclerView.adapter = ClassAdapter(createListOfSchoolClass())
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


    //endregion

    //endregion
}