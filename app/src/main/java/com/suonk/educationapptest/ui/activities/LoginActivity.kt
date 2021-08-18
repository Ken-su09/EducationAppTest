package com.suonk.educationapptest.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.content.res.AppCompatResources
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityLoginBinding
import java.util.regex.Pattern

class LoginActivity : AppCompatActivity() {

    //region =========================================== Example ============================================

    //endregion

    //region ========================================== Val or Var ==========================================

    private lateinit var binding: ActivityLoginBinding

    //Firebase
    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference

    private val toastLength = Toast.LENGTH_SHORT

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        initializeUI()
    }

    //region ========================================== Functions ===========================================

    //region ========================================== Initialize ==========================================

    private fun initializeUI() {
        initializeButtons()
        geUserInfoIntent()
        animationPasswordIconClick()
        rememberFillFields()

        binding.loginEmail.addTextChangedListener(object : TextWatcher {
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
        binding.logInButton.setOnClickListener {
            if (checkIfFieldsAreEmpty()) {
                Toast.makeText(this, "Fields should not be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (!checkEmailValidationSignUp()) {
                    Toast.makeText(this, "The email is not valid", Toast.LENGTH_SHORT).show()
                } else {
                    if (binding.loginCheckboxRemember.isChecked) {
                        sharedPrefRememberIsChecked()
                    } else {
                        val sharedPrefCheckBox = getSharedPreferences("rememberCheckbox", Context.MODE_PRIVATE)
                        val editorCheckBox: SharedPreferences.Editor = sharedPrefCheckBox.edit()
                        editorCheckBox.putBoolean("rememberCheckbox", binding.loginCheckboxRemember.isChecked)
                        editorCheckBox.apply()
                    }

                    userAuthentication()
                }
            }
        }

        binding.signUpButton.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        }
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun userAuthentication() {
        val email = binding.loginEmail.text.toString()
        val password = binding.loginPassword.text.toString()

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this) { task ->
            if (task.isSuccessful) {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Fail", toastLength).show()
            }
        }
    }

    //endregion

    //region ================================= Intent And SharedPreferences =================================

    private fun sharedPrefRememberIsChecked() {
        val sharedPrefCheckBox = getSharedPreferences("rememberCheckbox", Context.MODE_PRIVATE)
        val editorCheckBox: SharedPreferences.Editor = sharedPrefCheckBox.edit()
        editorCheckBox.putBoolean("rememberCheckbox", binding.loginCheckboxRemember.isChecked)
        editorCheckBox.apply()

        val sharedPrefEmail = getSharedPreferences("rememberEmail", Context.MODE_PRIVATE)
        val editorEmail: SharedPreferences.Editor = sharedPrefEmail.edit()
        editorEmail.putString("rememberEmail", binding.loginEmail.text.toString())
        editorEmail.apply()

        val sharedPrefPassword = getSharedPreferences("rememberPassword", Context.MODE_PRIVATE)
        val editorPassword: SharedPreferences.Editor = sharedPrefPassword.edit()
        editorPassword.putString("rememberPassword", binding.loginPassword.text.toString())
        editorPassword.apply()
    }

    private fun rememberFillFields() {
        val sharedPrefCheckBox = getSharedPreferences("rememberCheckbox", Context.MODE_PRIVATE)
        val sharedPrefEmail = getSharedPreferences("rememberEmail", Context.MODE_PRIVATE)
        val sharedPrefPassword = getSharedPreferences("rememberPassword", Context.MODE_PRIVATE)

        if (sharedPrefCheckBox.getBoolean("rememberCheckbox", false)) {
            binding.loginCheckboxRemember.isChecked = true
            binding.loginEmail.setText(sharedPrefEmail.getString("rememberEmail", "")!!)
            Log.i("rememberEmail", sharedPrefEmail.getString("rememberEmail", "")!!)
            binding.loginPassword.setText(sharedPrefPassword.getString("rememberPassword", ""))
        }
    }

    private fun geUserInfoIntent() {
        if (intent != null) {
            binding.loginEmail.setText(intent.getStringExtra("user_email"))
            binding.loginPassword.setText(intent.getStringExtra("user_password"))
        }
    }

    //endregion

    //region ========================================== CheckField ==========================================

    private fun checkIfFieldsAreEmpty(): Boolean {
        return binding.loginEmail.text!!.isEmpty() ||
                binding.loginPassword.text!!.isEmpty()

    }

    private fun checkEmailValidationSignUp(): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        val userEmail = binding.loginEmail.text.toString()

        return userEmail.trim().matches(emailPattern.toRegex())
    }

    private fun checkEmailConstantly() {
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
    }

    //endregion

    private fun animationPasswordIconClick() {
        binding.loginPasswordGoToVisible.setOnClickListener {
            val frameAnimation = binding.loginPasswordGoToVisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.loginPasswordGoToVisible.visibility = View.GONE
                binding.loginPasswordGoToInvisible.visibility = View.VISIBLE
                binding.loginPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
            }, 525)
        }

        binding.loginPasswordGoToInvisible.setOnClickListener {
            val frameAnimation = binding.loginPasswordGoToInvisible.drawable as AnimationDrawable
            frameAnimation.start()
            Handler(Looper.getMainLooper()).postDelayed({
                frameAnimation.stop()
                binding.loginPasswordGoToVisible.visibility = View.VISIBLE
                binding.loginPasswordGoToInvisible.visibility = View.GONE
                binding.loginPassword.transformationMethod =
                    PasswordTransformationMethod.getInstance()
            }, 525)
        }
    }

    //endregion
}