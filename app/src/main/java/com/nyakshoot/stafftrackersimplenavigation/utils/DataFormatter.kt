package com.nyakshoot.stafftrackersimplenavigation.utils

import java.text.SimpleDateFormat
import java.util.*

fun formatDate(inputDate: String): String {
    val inputFormat = SimpleDateFormat("E, dd MMM yyyy HH:mm:ss z", Locale.US)
    val outputFormat = SimpleDateFormat("dd.MM.yyyy", Locale.US)

    val date = inputFormat.parse(inputDate)
    return outputFormat.format(date!!)
}