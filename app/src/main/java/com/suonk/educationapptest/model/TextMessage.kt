package com.suonk.educationapptest.model

data class TextMessage(
    val text: String,
    override val time: String,
    override val senderEmail: String,
    override val type: String = MessageType.TEXT
) : Message {

    constructor() : this("", "0", "")
}
