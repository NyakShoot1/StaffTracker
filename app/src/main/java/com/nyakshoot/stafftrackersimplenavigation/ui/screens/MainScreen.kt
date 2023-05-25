package com.nyakshoot.stafftrackersimplenavigation.ui.screens

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.DocumentViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.component.MyTopAppBar
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.MyBottomNavigation
import com.nyakshoot.stafftrackersimplenavigation.ui.navigation.NavGraph

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen(employeeViewModel: EmployeeViewModel, documentViewModel: DocumentViewModel) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier,
        topBar = {
            MyTopAppBar(navController = navController)
        },
        bottomBar = {
            MyBottomNavigation(navController = navController)
        }
    ) {
        NavGraph(navHostController = navController, employeeViewModel, documentViewModel)
    }
}