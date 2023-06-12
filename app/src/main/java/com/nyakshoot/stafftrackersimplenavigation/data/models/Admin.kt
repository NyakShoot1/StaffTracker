package com.nyakshoot.stafftrackersimplenavigation.data.models

data class Admin(
    val login: String,
    val password: String
)

data class AdminResponse(
    var message: String,
    var error: String
)