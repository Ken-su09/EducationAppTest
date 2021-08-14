package com.suonk.educationapptest.ui.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
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
        animationPasswordIconClick()

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

    private fun animationPasswordIconClick() {
        binding.signUpPasswordGoToVisible.setOnClickListener {
            val frameAnimation = binding.signUpPasswordGoToVisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.signUpPasswordGoToVisible.visibility = View.GONE
                binding.signUpPasswordGoToInvisible.visibility = View.VISIBLE
                binding.signUpPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }, 525)
        }

        binding.signUpPasswordGoToInvisible.setOnClickListener {
            val frameAnimation = binding.signUpPasswordGoToInvisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.signUpPasswordGoToVisible.visibility = View.VISIBLE
                binding.signUpPasswordGoToInvisible.visibility = View.GONE
                binding.signUpPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }, 525)
        }
    }
}