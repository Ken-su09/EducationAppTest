package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivitySplashScreenBinding

class SplashScreenActivity : AppCompatActivity() {

    lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
        delayedToWelcomeActivity()
    }

    private fun initializeUI() {
        val animation = AnimationUtils.loadAnimation(this, R.anim.suddenly_appear)
        binding.appLogo.startAnimation(animation)
    }

    private fun delayedToWelcomeActivity() {
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this@SplashScreenActivity, WelcomeActivity::class.java))
            finish()
        }, 4000)
    }
}