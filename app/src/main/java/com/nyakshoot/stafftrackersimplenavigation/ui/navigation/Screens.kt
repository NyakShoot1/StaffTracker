package com.nyakshoot.stafftrackersimplenavigation.ui.navigation

import com.nyakshoot.stafftrackersimplenavigation.R

sealed class Screen(val title: String, val iconId: Int, val route: String){
    object Main: Screen("", -1, "main_screen")
    object Employees: Screen("Сотрудники", R.drawable.baseline_group_24, "employees")
    object NotEmployees: Screen("Уволенные сотрудники", R.drawable.baseline_group_off_24, "not_employees")
    object NewEmployee: Screen("Добавление сотрудника", R.drawable.baseline_person_add_alt_1_24, "new_employee")
    object NewDocument: Screen("Добавление документа", -1, "new_document")
    object NewPhoto: Screen("Добавление фото", -1, "new_photo")
    object Employee: Screen("Сотрудник", -1, "employee/{employee}")
    object Login: Screen("Логин", -1, "login")
}
