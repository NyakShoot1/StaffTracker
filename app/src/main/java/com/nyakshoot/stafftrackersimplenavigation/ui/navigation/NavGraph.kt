package com.nyakshoot.stafftrackersimplenavigation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.google.gson.Gson
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.DocumentViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.EmployeeViewModel
import com.nyakshoot.stafftrackersimplenavigation.data.viewmodel.PhotoViewModel
import com.nyakshoot.stafftrackersimplenavigation.ui.screens.*

@Composable
fun NavGraph(
    navHostController: NavHostController,
    employeeViewModel: EmployeeViewModel,
    documentViewModel: DocumentViewModel,
    photoViewModel: PhotoViewModel
) {
    NavHost(navController = navHostController, startDestination = "employees"){

        composable(Screen.Employees.route){
            EmployeesScreen(employeeViewModel, navHostController)
        }
        composable(Screen.NotEmployees.route){
            NotEmployeesScreen(employeeViewModel, navHostController)
        }
        composable(Screen.NewEmployee.route){
            NewEmployeeScreen(employeeViewModel)
        }
        composable(Screen.Employee.route,
            arguments = listOf(
                navArgument("employee"){ type = NavType.StringType }
            )
        ){
            it.arguments?.getString(Employee.EMPLOYEE)?.let {json ->
                val employee = Gson().fromJson(json, Employee::class.java)
                EmployeeScreen(employee, documentViewModel, photoViewModel)
            }
        }
        composable(Screen.NewDocument.route){
           NewDocumentScreen()
        }
        composable(Screen.NewPhoto.route){
            NewPhotoScreen()
        }
    }
}