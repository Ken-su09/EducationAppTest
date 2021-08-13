package com.suonk.educationapptest.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    private fun initializeUI() {

        initializeActionBar()
    }

    private fun initializeActionBar() {
        setSupportActionBar(binding.welcomeToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }
}