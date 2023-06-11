package com.nyakshoot.stafftrackersimplenavigation.ui.component

import android.app.DatePickerDialog
import android.content.Context
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun datePicker(context: Context): String {

    val year: Int
    val month: Int
    val day: Int

    val calendar = Calendar.getInstance()
    year = calendar.get(Calendar.YEAR)
    month = calendar.get(Calendar.MONTH)
    day = calendar.get(Calendar.DAY_OF_MONTH)
    calendar.time = Date()

    val date = remember { mutableStateOf("") }
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            date.value = "$month.$dayOfMonth.$year"
            date.value = String.format("%02d.%02d.%d", month, dayOfMonth + 1, year)
        }, year, month, day
    )

    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp)
            .padding(10.dp)
            .border(
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                shape = RoundedCornerShape(10.dp)
            )
            .clickable {
                datePickerDialog.show()
            },
        value = date.value,
        textStyle = TextStyle(fontSize = 20.sp),
        placeholder = {
            Text(
                text = "Дата рождения",
                color = MaterialTheme.colors.secondary,
                fontSize = 20.sp
            )
        },
        onValueChange = {
            date.value = it
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.White,
            focusedIndicatorColor = Color.Black, // Цвет черты при фокусе
            unfocusedIndicatorColor = Color.Black, // Цвет черты без фокуса
            backgroundColor = Color.Black
        )
    )
    Button(
        onClick = {
            datePickerDialog.show()
        }
    )
    {
        Text(
            text = "Выбрать дату рождения",
            fontSize = 20.sp,
            color = Color.White
        )
    }
    return date.value
}