package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivitySettingsBinding
import com.suonk.educationapptest.databinding.ActivityStudentCardBinding

class SettingsActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivitySettingsBinding
    private lateinit var drawer: DrawerLayout

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    //region ========================================== Functions ===========================================

    //region ========================================== Override ===========================================

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
                startActivity(Intent(this@SettingsActivity, MainActivity::class.java))
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
                startActivity(Intent(this@SettingsActivity, StudentCardActivity::class.java))
            }
            R.id.nav_settings -> {
            }
            R.id.nav_logout -> {
                alertDialog()
            }
        }
        return true
    }

    //endregion

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        initializeDrawerToolbarAndNavigation()
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
        binding.navView.menu.getItem(2).isChecked = true
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

    //endregion
}