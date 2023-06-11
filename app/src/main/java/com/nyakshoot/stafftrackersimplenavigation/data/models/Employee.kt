package com.nyakshoot.stafftrackersimplenavigation.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Employee(
    val employee_id: Int = 0,
    @SerializedName("full_name") val full_name: String = "",
    val birthday: String = "",
    val post: String = "",
    val department: String = "",
    val hire_date: String = "",
    val fire_date: String = "",
    val status: Boolean = true
) : Parcelable{
    companion object Keys {
        const val EMPLOYEE = "employee"
    }
}