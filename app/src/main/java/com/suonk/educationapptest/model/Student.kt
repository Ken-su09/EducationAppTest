package com.suonk.educationapptest.model

import com.google.firebase.firestore.IgnoreExtraProperties

@IgnoreExtraProperties
data class Student(
    var firstName: String,
    var lastName: String,
    var email: String,
    var birth_year: Long,
    var image_profile_url: String,
    var phone_number: String,
    var year_school: Long,
    var online: Boolean,
    var id: Int
) {
}
