package com.nyakshoot.stafftrackersimplenavigation.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

fun formatDate(inputDate: String): String {
    try {
        val inputFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.US)
        val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)
        val date = inputFormat.parse(inputDate)
        return outputFormat.format(date!!)
    } catch (e: Exception) {
        return "ERROR"
    }
}