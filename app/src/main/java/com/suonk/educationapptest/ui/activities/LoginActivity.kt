package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.widget.addTextChangedListener
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityLoginBinding
import java.util.regex.Pattern

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
        initializeButtons()

        binding.loginEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkEmail()
            }
        })
    }

    private fun initializeActionBar() {
        setSupportActionBar(binding.loginToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun initializeButtons() {
        binding.logInButton
        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    private fun checkEmail() {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val userEmail = binding.loginEmail.text.toString()

        if (userEmail.trim().matches(emailPattern.toRegex())) {
            binding.loginEmailValidation.visibility = View.VISIBLE
            binding.loginEmailValidation.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_check_email
                )
            )
        } else {
            if (userEmail.isEmpty()) {
                binding.loginEmailValidation.visibility = View.INVISIBLE
            } else {
                binding.loginEmailValidation.visibility = View.VISIBLE
                binding.loginEmailValidation.setImageDrawable(
                    AppCompatResources.getDrawable(
                        this,
                        R.drawable.ic_check_email_cross
                    )
                )
            }
        }
//        if (userEmail.isEmpty()) {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
//        } else {
//        }
    }
}