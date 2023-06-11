package com.nyakshoot.stafftrackersimplenavigation.data.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nyakshoot.stafftrackersimplenavigation.data.api.ApiHelper
import com.nyakshoot.stafftrackersimplenavigation.data.models.Employee
import com.nyakshoot.stafftrackersimplenavigation.data.models.NewEmployee
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.RequestBody
import org.json.JSONObject


class EmployeeViewModel (private val apiHelper: ApiHelper) : ViewModel() {
    //Список уволеных сотрудников
    private val _employeesFired = MutableStateFlow<List<Employee>>(emptyList())
    val employeesFired: StateFlow<List<Employee>> = _employeesFired

    //Список действующих сотрудников
    private val _employeesActive = MutableStateFlow<List<Employee>>(emptyList())
    val employeesActive: StateFlow<List<Employee>> = _employeesActive

    //Список всех пользователей
    private val _employees = MutableStateFlow<List<Employee>>(emptyList())
    val employees: StateFlow<List<Employee>> = _employees

    //Сотрудник
    private val emp = Employee(0, "", "", "", "", "", "", true)
    private val _employee = MutableStateFlow(emp)
    val employee: StateFlow<Employee> = _employee

    fun newEmployee(employee: NewEmployee){
        viewModelScope.launch {
            try {
                apiHelper.newEmployee(employee)
            } catch (e: Exception){
                Log.e("newEmployee", "Error fetching data", e)
            }
        }
    }
    fun getActiveEmployees(){
        viewModelScope.launch {
            try {
                val employees = apiHelper.getActiveEmployees()
                _employeesActive.tryEmit(employees)
            } catch (e: Exception){
                Log.e("getActiveEmployees", "Error fetching data", e)
            }
        }
    }

    fun getFiredEmployees(){
        viewModelScope.launch {
            try {
                val employees = apiHelper.getFiredEmployees()
                _employeesFired.tryEmit(employees)
            } catch (e: Exception){
                Log.e("getFiredEmployees", "Error fetching data", e)
            }
        }
    }

    fun firedEmployee(employeeId: String){
        viewModelScope.launch {
            try {
                apiHelper.firedEmployee(employeeId)
            } catch (e: Exception){
                Log.e("firedEmployee", "Error fetching data", e)
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