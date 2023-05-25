package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class EmployeeViewModel (private val apiHelper: ApiHelper) : ViewModel() {
    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    private val emp = Employee(0, "", "", "", "", "", "", true)
    private val _employee = MutableStateFlow(emp)
    val employee: StateFlow<Employee> = _employee

    fun getEmployees(){
        viewModelScope.launch {
            try {
                val employees = apiHelper.getEmployees()
                _employees.tryEmit(employees)
            } catch (e: Exception){
                Log.e("getEmployees", "Error fetching data", e)
            }
        }
    }

    fun getTest() {
        viewModelScope.launch {
            try {
                val emp = apiHelper.test()
                _employee.tryEmit(emp)
            } catch (e: Exception) {
                Log.e("EmployeeViewModel", "Error fetching data", e)
            }
        }
    }
}