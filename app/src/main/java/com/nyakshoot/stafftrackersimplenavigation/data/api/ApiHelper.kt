package com.nyakshoot.stafftrackersimplenavigation.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getEmployees() = apiService.getEmployees()

    suspend fun getEmployeeDocuments(employeeId : String) = apiService.getEmployeeDocuments(employeeId)

    suspend fun loadDocument(documentUrl: String) = apiService.loadDocument(documentUrl)

    suspend fun test() = apiService.getTestApi()
}