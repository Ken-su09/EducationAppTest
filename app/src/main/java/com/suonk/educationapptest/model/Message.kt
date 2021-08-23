package com.suonk.educationapptest.model

object MessageType {
    const val TEXT = "TEXT"
    const val IMAGE = "IMAGE"
}

interface Message {
    val time: String
    val senderEmail: String
    val type: String
}