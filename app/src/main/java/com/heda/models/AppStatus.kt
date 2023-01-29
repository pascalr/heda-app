package com.heda.models

data class AppStatus (
    val csrf: String,
    val logged_in: String,
    val user_id: Int?
)