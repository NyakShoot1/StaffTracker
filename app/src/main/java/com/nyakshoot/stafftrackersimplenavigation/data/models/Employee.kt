package com.nyakshoot.stafftrackersimplenavigation.data.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val employee_id: Int,
    val full_name: String,
    val birthday: String,
    val post: String,
    val department: String,
    val hire_date: String,
    val fire_date: String,
    val status: Boolean
) : Parcelable{
    companion object Keys {
        const val EMPLOYEE = "employee"
    }
}
