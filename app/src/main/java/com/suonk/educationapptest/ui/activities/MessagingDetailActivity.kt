package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.bumptech.glide.Glide
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityMessagingBinding
import com.suonk.educationapptest.databinding.ActivityMessagingDetailBinding
import com.suonk.educationapptest.model.Student
import com.suonk.educationapptest.utils.FunctionsUtils

class MessagingDetailActivity : AppCompatActivity() {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityMessagingDetailBinding

    // Firebase
    private lateinit var auth: FirebaseAuth

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessagingDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initializeUI()
    }

    //region =========================================== Override ===========================================

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        binding.backOnPressed.setOnClickListener {
            onBackPressed()
        }
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
        getUserFromListOfStudentsOnline()
    }

    private fun getUserFromListOfStudentsOnline() {
        val userCollectionReference = Firebase.firestore.collection("Users")
        userCollectionReference.get().addOnSuccessListener { Students ->
            for (student in Students) {
                if (intent.getStringExtra("studentOnline") == student.data["email"]) {
                    binding.itemMessageName.text = student.data["firstName"].toString()

                    Glide.with(this)
                        .load(student.data["image_profile_url"])
                        .centerCrop()
                        .into(binding.itemMessageImage)

                    break
                }
            }
        }
    }

    //endregion
}