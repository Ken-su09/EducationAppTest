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
import com.suonk.educationapptest.databinding.ActivityStudentCardBinding

class StudentCardActivity : AppCompatActivity() {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityStudentCardBinding

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStudentCardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    //region ========================================== Functions ===========================================

    //region ========================================== Override ===========================================

    //endregion

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        initializeDrawerToolbarAndNavigation()
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