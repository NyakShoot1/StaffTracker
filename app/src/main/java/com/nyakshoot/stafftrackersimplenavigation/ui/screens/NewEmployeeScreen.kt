package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.models.NewEmployee
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.component.EmployeeDropMenu
import com.nyakshoot.stafftrackersimplenavigation.ui.component.datePicker

@Composable
fun NewEmployeeScreen(employeeViewModel: EmployeeViewModel) {
    val context = LocalContext.current

    var fullName by remember { mutableStateOf(TextFieldValue("")) }
    var post by remember { mutableStateOf(TextFieldValue("")) }
    var department by remember { mutableStateOf(TextFieldValue("")) }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp)
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                ),
            value = fullName,
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = {
                Text(
                    text = "ФИО",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp
                )
            },
            onValueChange = {
                fullName = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = Color.Black, // Цвет черты при фокусе
                unfocusedIndicatorColor = Color.Black, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(1.dp))
        val birthday = datePicker(context)
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp)
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                ),
            value = department,
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = {
                Text(
                    text = "Отдел",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp
                )
            },
            onValueChange = {
                department = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = Color.Black, // Цвет черты при фокусе
                unfocusedIndicatorColor = Color.Black, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(10.dp)
                .border(
                    border = BorderStroke(width = 2.dp, color = MaterialTheme.colors.secondary),
                    shape = RoundedCornerShape(10.dp)
                ),
            value = post,
            textStyle = TextStyle(fontSize = 20.sp),
            placeholder = {
                Text(
                    text = "Должность",
                    color = MaterialTheme.colors.secondary,
                    fontSize = 20.sp
                )
            },
            onValueChange = {
                post = it
            },
            colors = TextFieldDefaults.textFieldColors(
                textColor = Color.White,
                focusedIndicatorColor = Color.Black, // Цвет черты при фокусе
                unfocusedIndicatorColor = Color.Black, // Цвет черты без фокуса
                backgroundColor = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Button(
            modifier = Modifier
                .height(60.dp)
                .width(130.dp),
            shape = RoundedCornerShape(10.dp),
            onClick = {
                val employee = NewEmployee(fullName.text, birthday, post.text, department.text)
                employeeViewModel.newEmployee(employee)
                Toast
                    .makeText(context, "Сотрудник успешно добавлен!", Toast.LENGTH_LONG)
                    .show()
            },
            elevation = ButtonDefaults.elevation(
                defaultElevation = 10.dp,
                pressedElevation = 15.dp,
                disabledElevation = 0.dp
            ),
        ) {
            Text(
                text = "Добавить",
                fontSize = 20.sp,
                color = Color.White
            )
        }
    }
}

