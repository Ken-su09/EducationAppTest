package com.suonk.educationapptest.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initializeUI()
    }

    private fun initializeUI() {

        initializeActionBar()
        initializeButtons()

        binding.signUpEmail.addTextChangedListener(object : TextWatcher {
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
        setSupportActionBar(binding.signUpToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
    }

    private fun initializeButtons() {
        binding.signUpLogInButton.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
        }
    }

    private fun checkEmail() {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val userEmail = binding.signUpEmail.text.toString()

        if (userEmail.trim().matches(emailPattern.toRegex())) {
            binding.signUpEmailValidation.visibility = View.VISIBLE
            binding.signUpEmailValidation.setImageDrawable(
                AppCompatResources.getDrawable(
                    this,
                    R.drawable.ic_check_email
                )
            )
        } else {
            if (userEmail.isEmpty()) {
                binding.signUpEmailValidation.visibility = View.INVISIBLE
            } else {
                binding.signUpEmailValidation.visibility = View.VISIBLE
                binding.signUpEmailValidation.setImageDrawable(
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