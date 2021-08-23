package com.suonk.educationapptest.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.suonk.educationapptest.R
import com.suonk.educationapptest.databinding.ActivityMessagingDetailBinding
import com.suonk.educationapptest.databinding.ActivityNewMessageBinding
import com.suonk.educationapptest.model.Student
import com.suonk.educationapptest.ui.adapters.NewMessageStudentAdapter
import com.suonk.educationapptest.ui.adapters.OnlineStudentsAdapter
import com.suonk.educationapptest.utils.FunctionsUtils

class NewMessageActivity : AppCompatActivity(), View.OnClickListener {

    //region ========================================== Val or Var ==========================================

    // View
    private lateinit var binding: ActivityNewMessageBinding

    // Firebase
    private lateinit var auth: FirebaseAuth

    // Model
    private var listOfStudentsOnline = mutableListOf<Student>()

    //endregion

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initFirebase()
        initializeUI()
    }

    //region =========================================== Override ===========================================

    override fun onClick(v: View?) {
        if (v?.tag != null) {
            if (v.id == R.id.item_new_message_layout) {
                Log.i("onClick", "${v.tag}")
                val intent = Intent(this@NewMessageActivity, MessagingDetailActivity::class.java)
                intent.putExtra("studentOnline", v.tag as String)
                startActivity(intent)
            }
        }
    }

    //endregion

    //region ========================================= InitializeUI =========================================

    private fun initializeUI() {
        binding.backOnPressed.setOnClickListener {
            onBackPressed()
        }
    }

    private fun initializeRecyclerStudentsOnline(listOfStuds: MutableList<Student>) {
        binding.newMessageRecyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.newMessageRecyclerView.adapter =
            NewMessageStudentAdapter(listOfStuds, this)
    }

    //endregion

    //region =========================================== Firebase ===========================================

    private fun initFirebase() {
        auth = FirebaseAuth.getInstance()
        getListOfStudentsOnline()
    }

    private fun getListOfStudentsOnline() {
        val userCollectionReference = Firebase.firestore.collection("Users")
        userCollectionReference.get().addOnSuccessListener { Students ->
            for (studentOnLine in Students) {
                val ifOnline = studentOnLine.data["online"] as Boolean
                if (auth.currentUser!!.email != studentOnLine.data["email"] && ifOnline) {
                    Log.i("getListOfStudentsOnline", "${studentOnLine.data["firstName"]}")
                    val studentOnline = Student(
                        studentOnLine.data["firstName"].toString(),
                        studentOnLine.data["lastName"].toString(),
                        studentOnLine.data["email"].toString(),
                        studentOnLine.data["birth_year"] as Long,
                        studentOnLine.data["image_profile_url"].toString(),
                        studentOnLine.data["phone_number"].toString(),
                        studentOnLine.data["year_school"] as Long,
                        studentOnLine.data["online"] as Boolean,
                        1
                    )
                    listOfStudentsOnline.add(studentOnline)
                }
            }
            initializeRecyclerStudentsOnline(listOfStudentsOnline)
        }
    }

    //endregion
}