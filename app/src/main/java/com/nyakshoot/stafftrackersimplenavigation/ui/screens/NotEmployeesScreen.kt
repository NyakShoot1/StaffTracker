package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.component.EmployeeCard
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.Screen

@Composable
fun NotEmployeesScreen(employeeViewModel: EmployeeViewModel, navHostController: NavHostController) {

    val gson = Gson()

    val state by employeeViewModel.employeesFired.collectAsState()

    val list = remember {
        mutableStateOf(employeeViewModel.employeesFired.value)
    }

    LaunchedEffect(Unit) {
        employeeViewModel.getFiredEmployees()
        list.value = state
    }
    Box(
        modifier = Modifier
            .background(MaterialTheme.colors.background)
        //.clip(shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp))
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxHeight(0.91f)
                .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            items(state) { employee ->
                EmployeeCard(
                    employee
                ) {
                    val employeeJson = gson.toJson(employee)
                    navHostController.navigate(
                        Screen.Employee
                            .route.replace("{employee}", employeeJson)
                    )
                }
            }
        }
    }
}