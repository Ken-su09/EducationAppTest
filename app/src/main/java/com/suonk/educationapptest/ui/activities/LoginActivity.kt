package com.suonk.educationapptest.ui.activities

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.View
import androidx.appcompat.content.res.AppCompatResources
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
        initializeButtons()
        animationPasswordIconClick()

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

    private fun animationPasswordIconClick() {
        binding.loginPasswordGoToVisible.setOnClickListener {
            val frameAnimation = binding.loginPasswordGoToVisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.loginPasswordGoToVisible.visibility = View.GONE
                binding.loginPasswordGoToInvisible.visibility = View.VISIBLE
                binding.loginPassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
            }, 525)
        }

        binding.loginPasswordGoToInvisible.setOnClickListener {
            val frameAnimation = binding.loginPasswordGoToInvisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.loginPasswordGoToVisible.visibility = View.VISIBLE
                binding.loginPasswordGoToInvisible.visibility = View.GONE
                binding.loginPassword.transformationMethod = PasswordTransformationMethod.getInstance()
            }, 525)
        }
    }
}