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

class SettingsActivity : AppCompatActivity() {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivitySettingsBinding

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

    //endregion

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        initializeDrawerToolbarAndNavigation()
        darkModeSwitch()
    }

    private fun initializeDrawerToolbarAndNavigation() {
        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun darkModeSwitch() {
        binding.darkModeSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                binding.darkModeOnOff.text = "On"
            } else {
                binding.darkModeOnOff.text = "Off"
            }
        }
    }

    //endregion

    //endregion
}