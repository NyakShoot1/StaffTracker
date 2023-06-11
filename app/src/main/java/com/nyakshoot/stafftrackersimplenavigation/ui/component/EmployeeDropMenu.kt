package com.nyakshoot.stafftrackersimplenavigation.ui.component

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.gson.Gson
import com.nyakshoot.stafftrackersimplenavigation.R
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.Screen

@Composable
fun EmployeeDropMenu(
    navController: NavController,
    employeeViewModel: EmployeeViewModel
) {
    val context = LocalContext.current

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route

    var employee = Employee()
    val jsonObj = backStackEntry?.arguments?.getString(Employee.EMPLOYEE)?.let { json ->
        employee = Gson().fromJson(json, Employee::class.java)
    }

    if (currentRoute == Screen.Employee.route) {
        var expanded by remember { mutableStateOf(false) }
        Box {
            IconButton(
                modifier = Modifier.size(30.dp),
                onClick = { expanded = true }) {
                Icon(
                    Icons.Default.MoreVert,
                    contentDescription = "Показать меню"
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .background(MaterialTheme.colors.onSurface)
                    .clip(
                        RoundedCornerShape(20.dp)
                    ),
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .clickable(onClick = {
                            navController.navigate(Screen.NewDocument.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.outline_note_add_24),
                        contentDescription = "add_document",
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        "Добавить документ",
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }
                Divider(color = Color.LightGray)
                Row(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxSize()
                        .clickable(onClick = {
                            navController.navigate(Screen.NewPhoto.route)
                        }),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Start
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(id = R.drawable.outline_add_photo_alternate_24),
                        contentDescription = "add_document",
                        tint = MaterialTheme.colors.primary
                    )
                    Text(
                        "Добавить фото",
                        style = MaterialTheme.typography.h4,
                        color = Color.White,
                        fontSize = 18.sp,
                    )
                }
                if (employee.status) {
                    Divider(color = Color.LightGray)
                    Row(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxSize()
                            .clickable(onClick = {
                                employeeViewModel.firedEmployee(employee.employee_id.toString())
                                Toast
                                    .makeText(
                                        context,
                                        "Сотрудник успешно уволен!",
                                        Toast.LENGTH_LONG
                                    )
                                    .show()
                            }),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Icon(
                            modifier = Modifier.padding(end = 10.dp),
                            painter = painterResource(id = R.drawable.outline_person_remove_24),
                            contentDescription = "add_document",
                            tint = MaterialTheme.colors.primary
                        )
                        Text(
                            "Уволить",
                            style = MaterialTheme.typography.h4,
                            color = Color.White,
                            fontSize = 18.sp,
                        )
                    }
                }
            }
        }
    }
}
