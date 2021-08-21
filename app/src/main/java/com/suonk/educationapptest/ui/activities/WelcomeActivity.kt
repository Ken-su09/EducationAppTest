package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.auth.FirebaseAuth
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    private lateinit var binding: ActivityWelcomeBinding

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        if (auth.currentUser != null) {
            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
        } else {
            initializeUI()
        }
    }

    //region ========================================== Functions ===========================================

    //region ========================================== Override ============================================

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.toolbar_login -> {
                startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.welcome_toolbar, menu);
        return true;
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        initializeToolbarAndNavigation()
    }

    private fun initializeToolbarAndNavigation() {
        // Toolbar
        setSupportActionBar(binding.toolbar)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    //endregion

    //endregion
}