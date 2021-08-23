package com.suonk.educationapptest.model

data class ChatChannel(val userIds: MutableList<String>) {
    constructor() : this(mutableListOf())
}
