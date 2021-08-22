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
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivitySignUpBinding
import java.util.regex.Pattern

class SignUpActivity : AppCompatActivity() {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    private lateinit var binding: ActivitySignUpBinding

    //Firebase
    private lateinit var auth: FirebaseAuth

    private val toastLength = Toast.LENGTH_SHORT

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        initializeUI()
    }

    //region ========================================== Functions ===========================================

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        initializeButtons()
        animationPasswordIconClick()

        binding.signUpEmail.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                checkEmailConstantly()
            }
        })
    }

    private fun initializeButtons() {
        binding.signUpLogInButton.setOnClickListener {
            startActivity(Intent(this@SignUpActivity, LoginActivity::class.java))
            overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right)
        }

        binding.signUpButton.setOnClickListener {
            if (checkIfFieldsAreEmpty()) {
                Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (!checkEmailValidationSignUp()) {
                    Toast.makeText(this, "The email is not valid", Toast.LENGTH_SHORT).show()
                }
                if (checkPassword()) {
                    Toast.makeText(
                        this,
                        "Your password must contain more than 5 characters",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                }
            }
        }
    }

    //endregion

    //region ========================================== Animations ==========================================

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

    //endregion

    //region =========================================== Firebase ===========================================

    //endregion

    //region ========================================== CheckField ==========================================

    private fun checkIfFieldsAreEmpty(): Boolean {
        return binding.signUpUsername.text!!.isEmpty() ||
                binding.signUpEmail.text!!.isEmpty() ||
                binding.signUpPassword.text!!.isEmpty()

    }

    private fun checkEmailValidationSignUp(): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val userEmail = binding.signUpEmail.text.toString()

        return userEmail.trim().matches(emailPattern.toRegex())
    }

    private fun checkEmailConstantly() {
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
    }

    private fun checkPassword(): Boolean {
        return binding.signUpPassword.text!!.length < 6
    }

    //endregion

    //endregion
}